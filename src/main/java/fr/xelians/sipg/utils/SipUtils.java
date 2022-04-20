/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.xelians.sipg.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.xml.sax.InputSource;
import uk.gov.nationalarchives.droid.core.BinarySignatureIdentifier;
import uk.gov.nationalarchives.droid.core.SignatureParseException;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationRequest;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;
import uk.gov.nationalarchives.droid.core.interfaces.RequestIdentifier;
import uk.gov.nationalarchives.droid.core.interfaces.resource.FileSystemIdentificationRequest;
import uk.gov.nationalarchives.droid.core.interfaces.resource.RequestMetaData;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * La classe SipUtils offre des méthodes statiques utilitaires. Note. Il est fortement déconseillé d'utiliser cette
 * classe en dehors de la librairie.
 *
 * @author Emmanuel Deviller
 */
public final class SipUtils {

    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter YYYYMMDD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final String NOT_NULL = "the %s argument must be not null";

    private SipUtils() {
    }

    /**
     * Allow lazy initialization of Droid Signatures
     */
    private static class DroidSignaturesHolder {
        private static final BinarySignatureIdentifier INSTANCE = initDroidSignatures();
    }

    /**
     * Initialise les identifiants binaires des signatures Droid à partir de la ressource par défaut.
     *
     * @return les identifiants binaire des signatures
     */
    private static BinarySignatureIdentifier initDroidSignatures() {
        Path signaturePath = null;
        try (InputStream is = ClassLoader.getSystemResourceAsStream("droid_signaturefile.xml")) {
            if (is != null) {
                signaturePath = Files.createTempFile("droid", ".xml");
                Files.copy(is, signaturePath, StandardCopyOption.REPLACE_EXISTING);
                return initDroidSignatures(signaturePath) ;
            }
            throw new SipgException("Unable to found Droid droid_signaturefile.xml in resource");
        } catch (IOException ex) {
            throw new SipgException("Unable to init Droid signatures identifier", ex);
        } finally {
            if (signaturePath != null) {
                try {
                    Files.deleteIfExists(signaturePath);
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Initialise les identifiants binaires des signatures Droid à partir d'un fichier de signature.
     * Note. le fichier de signatures doit se trouver sur le système de fichier par défaut.
     *
     * @param signaturePath le path du fichier de signature
     * @return les identifiants binaire des signatures
     */
    public static BinarySignatureIdentifier initDroidSignatures(Path signaturePath) {
        Validate.notNull(signaturePath, NOT_NULL, "signaturePath");

        try {
            BinarySignatureIdentifier bsi = new BinarySignatureIdentifier();
            bsi.setSignatureFile(signaturePath.toString());
            bsi.init();
            return bsi;
        } catch (SignatureParseException ex) {
            throw new SipgException("Unable to init Droid signatures identifier", ex);
        }
    }

    /**
     * Crée une URI à partir du path.
     *
     * @param zipPath le path du zip
     * @return l 'URI
     */
    public static URI createZipURI(Path zipPath) {
        Validate.notNull(zipPath, NOT_NULL, "zipPath");

        try {
            return new URI("jar:file", zipPath.toAbsolutePath().toUri().getPath(), null);
        } catch (URISyntaxException ex) {
            throw new SipgException("Unable to create Zip Archive", ex);
        }
    }

    /**
     * Crée un système de fichier de type Zip à partir du path.
     *
     * @param zipPath le path du zip
     * @return le système de fichier de type Zip
     */
    public static FileSystem newZipFileSystem(Path zipPath) {
        Validate.notNull(zipPath, NOT_NULL, "zipPath");

        URI zipURI = SipUtils.createZipURI(zipPath);
        Map<String, String> zipMap = Map.of("create", "true");

        try {
            return FileSystems.newFileSystem(zipURI, zipMap);
        } catch (IOException ex) {
            throw new SipgException("Unable to create  ZipFilesystem " + zipPath, ex);
        }
    }

    /**
     * Formate le flux de données XML avec l'indentation spécifiée. Si le flux d'entrée est le résultat d'un marshalling
     * JAXB, il nécessaire de positionner l'option Marshaller.JAXB_FORMATTED_OUTPUT à false pour que cette méthode
     * fonctionne correctement.
     *
     * @param is          le flux d'entrée
     * @param os          le flux de sortie
     * @param indentation la valeur de l'indentation
     */
    public static void formatXml(InputStream is, OutputStream os, int indentation) {
        Validate.notNull(is, NOT_NULL, "is");
        Validate.notNull(os, NOT_NULL, "os");

        try {
            Transformer transformer = SAXTransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(Math.abs(indentation)));
            transformer.transform(new SAXSource(new InputSource(is)), new StreamResult(os));
        } catch (IllegalArgumentException | TransformerException ex) {
            throw new SipgException("Unable to create transformer", ex);
        }
    }

    /**
     * Formate l'arbre DOM avec l'indentation spécifiée. Si l'arbre DOM en entrée est le résultat d'un marshalling JAXB,
     * il est nécessaire de positionner l'option Marshaller.JAXB_FORMATTED_OUTPUT à false pour que cette méthode
     * fonctionne correctement.
     *
     * @param domResult   l'arbre DOM
     * @param os          le flux de sortie
     * @param indentation la valeur de l'indentation
     */
    public static void formatXml(DOMResult domResult, OutputStream os, int indentation) {
        Validate.notNull(domResult, NOT_NULL, "domResult");
        Validate.notNull(os, NOT_NULL, "os");

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(Math.abs(indentation)));
            transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(os));
        } catch (IllegalArgumentException | TransformerException ex) {
            throw new SipgException("Unable to create transformer", ex);
        }
    }

    /**
     * Identifie le format du fichier spécifié par le path en utilisant les identifiants binaire des signatures par
     * défaut. Note. Les paths vers un système de fichier virtuel (ie. zip) ne sont pas supportés.
     *
     * @param path le path du fichier à identifier
     * @return la liste de résultats de l'identification.
     */
    public static List<IdentificationResult> matchBinarySignatures(Path path) {
        Validate.notNull(path, NOT_NULL, "path");
        return matchBinarySignatures(path, DroidSignaturesHolder.INSTANCE);
    }

    /**
     * Identifie le fichier spécifié par le path en utilisant les identifiants binaire des signatures spécifié. Note.
     * Les paths accédant à un système de fichier virtuel (ie. zip) ne sont pas supportés.
     *
     * @param path le path du fichier à identifier
     * @param bsi  les identifiants binaire des signatures
     * @return la liste de résultats de l'identification.
     */
    public static List<IdentificationResult> matchBinarySignatures(Path path, BinarySignatureIdentifier bsi) {
        Validate.notNull(path, NOT_NULL, "path");
        Validate.notNull(bsi, NOT_NULL, "bsi");

        try {
            RequestMetaData metaData = new RequestMetaData(Files.size(path), Files.getLastModifiedTime(path).toMillis(), path.getFileName().toString());
            RequestIdentifier identifier = new RequestIdentifier(path.toUri());
            identifier.setParentId(1L);

            IdentificationRequest<Path> request = new FileSystemIdentificationRequest(metaData, identifier);
            request.open(path);
            return bsi.matchBinarySignatures(request).getResults();
        } catch (IOException ex) {
            throw new SipgException("Unable to matchBinarySignatures for " + path, ex);
        }
    }

    /**
     * Fournit la ressource système spécifiée par le path.
     *
     * @param name le path
     * @return la ressource système
     */
    public static InputStream resourceAsStream(String name) {
        return ClassLoader.getSystemResourceAsStream(name);
    }

    /**
     * Retourne la taille du pool de thread. Si n est égal ou inférieur à zéro, la taille correspond au nombre de
     * processeurs disponibles.
     *
     * @param n le nombre de thread souhaité
     * @return la taille du pool de thread
     */
    public static int getPoolSize(int n) {
        return n <= 0 ? Runtime.getRuntime().availableProcessors() : n;
    }

    /**
     * Convertit la date en XML au format "yyyy-MM-dd".
     *
     * @param date la date
     * @return la date formatée
     */
    public static XMLGregorianCalendar toXmlDate(LocalDate date) {
        Validate.notNull(date, NOT_NULL, "date");

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date.format(YYYYMMDD));
        } catch (DatatypeConfigurationException ex) {
            throw new SipgException(String.format("Unable to convert Date %s to XMLGregorianCalendar", date.toString()), ex);
        }
    }

    /**
     * Convertit la date en XML au format "yyyy-MM-dd'T'HH:mm:ss".
     *
     * @param date la date
     * @return la date formatée
     */
    public static XMLGregorianCalendar toXmlDateTime(LocalDateTime date) {
        Validate.notNull(date, NOT_NULL, "date");

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date.format(YYYYMMDD_HMS));
        } catch (DatatypeConfigurationException ex) {
            throw new SipgException(String.format("Unable to convert Date %s to XMLGregorianCalendar", date.toString()), ex);
        }
    }

    /**
     * Convertit la durée la chaîne de caractères en type Duration.
     *
     * @param duration la durée de type chaîne de caractères
     * @return la durée de type Duration
     */
    public static Duration toDuration(String duration) {
        Validate.notNull(duration, NOT_NULL, "duration");

        try {
            return DatatypeFactory.newInstance().newDuration(duration);
        } catch (DatatypeConfigurationException ex) {
            throw new SipgException(String.format("Unable to convert String %s to Duration", duration), ex);
        }
    }

    /**
     * Calcule le condensat en hexadécimal du fichier indiqué par le path selon l'algorithm spécifié.
     *
     * @param binaryPath le path
     * @param algorithm  l'algorithme
     * @return le condensat
     * @throws IOException génère une exception d'entrée-sortie
     */
    public static String digestHex(Path binaryPath, String algorithm) throws IOException {
        Validate.notNull(binaryPath, NOT_NULL, "binaryPath");
        Validate.notNull(algorithm, NOT_NULL, "algorithm");

        DigestUtils du = new DigestUtils(algorithm);
        try (InputStream is = Files.newInputStream(binaryPath)) {
            return du.digestAsHex(is);
        }
    }

    /**
     * Calcule le condensat du fichier indiqué par le path selon l'algorithm spécifié.
     *
     * @param binaryPath le path
     * @param algorithm  l'algorithme
     * @return le condensat
     * @throws IOException génère une exception d'entrée-sortie
     */
    public static byte[] digest(Path binaryPath, String algorithm) throws IOException {
        Validate.notNull(binaryPath, NOT_NULL, "binaryPath");
        Validate.notNull(algorithm, NOT_NULL, "algorithm");

        DigestUtils du = new DigestUtils(algorithm);
        try (InputStream is = Files.newInputStream(binaryPath)) {
            return du.digest(is);
        }
    }

    /**
     * Retourne true si l'objet est nul sinon la valeur par défaut.
     *
     * @param <T> le type de l'objet
     * @param obj l'objet à tester
     * @param def la valeur par défaut
     * @return true si l'objet est nul sinon la valeur par défaut
     */
    public static <T> T getIfNull(T obj, T def) {
        return obj == null ? def : obj;
    }

    /**
     * Retourne true si l'objet est vide sinon la valeur par défaut.
     *
     * @param obj l'objet à tester
     * @param def a valeur par défaut
     * @return Retourne true si l'objet est vide sinon la valeur par défaut
     */
    public static String getIfBlank(String obj, String def) {
        return StringUtils.isBlank(obj) ? def : obj;
    }

    /**
     * Applique le consommateur si l'objet n'est pas nul.
     *
     * @param <T>  le type de l'objet
     * @param obj  l'objet à tester
     * @param func le consommateur
     */
    public static <T> void acceptIfNotNull(T obj, Consumer<T> func) {
        if (obj != null) {
            func.accept(obj);
        }
    }

    /**
     * Applique le consommateur si l'objet n'est pas vide.
     *
     * @param obj  l'objet à tester
     * @param func le consommateur
     */
    public static void acceptIfNotBlank(String obj, Consumer<String> func) {
        if (StringUtils.isNotBlank(obj)) {
            func.accept(obj);
        }
    }
}
