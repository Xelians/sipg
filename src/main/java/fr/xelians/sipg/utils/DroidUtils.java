package fr.xelians.sipg.utils;

import static fr.xelians.sipg.utils.SipUtils.NOT_NULL;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import uk.gov.nationalarchives.droid.core.BinarySignatureIdentifier;
import uk.gov.nationalarchives.droid.core.IdentificationRequestByteReaderAdapter;
import uk.gov.nationalarchives.droid.core.SignatureParseException;
import uk.gov.nationalarchives.droid.core.interfaces.*;
import uk.gov.nationalarchives.droid.core.interfaces.resource.FileSystemIdentificationRequest;
import uk.gov.nationalarchives.droid.core.interfaces.resource.RequestMetaData;
import uk.gov.nationalarchives.droid.core.signature.ByteReader;
import uk.gov.nationalarchives.droid.core.signature.FileFormat;
import uk.gov.nationalarchives.droid.core.signature.FileFormatHit;
import uk.gov.nationalarchives.droid.core.signature.droid6.InternalSignature;

/**
 * La classe DroidUtils offre des méthodes statiques utilitaires. Note. Il est fortement déconseillé
 * d'utiliser cette classe en dehors de la librairie.
 *
 * @author Emmanuel Deviller
 */
public final class DroidUtils {

  private static final String[] EXTENSIONS = {
    "PDF", "JPG", "JPEG", "TIF", "TIFF", "PNG", "XML", "TXT", "CSV", "DOC", "DOCX", "XLS", "XLSX",
    "ZIP"
  };

  private static final Map<String, List<InternalSignature>> SIGNATURES_BY_ID = new HashMap<>();

  private DroidUtils() {}

  /**
   * Initialise les identifiants binaires des signatures Droid à partir de la ressource par défaut.
   *
   * @return les identifiants binaires des signatures
   */
  private static BinarySignatureIdentifier initDroidSignatures() {
    Path signatureTmpPath = null;
    try (InputStream is = SipUtils.resourceAsStream("droid_signaturefile.xml")) {
      if (is != null) {
        signatureTmpPath = Files.createTempFile("droid", ".xml");
        Files.copy(is, signatureTmpPath, StandardCopyOption.REPLACE_EXISTING);
        BinarySignatureIdentifier bsi = initDroidSignatures(signatureTmpPath);
        List.of(EXTENSIONS)
            .forEach(
                ext -> {
                  List<InternalSignature> isigs = buildInternalSignatures(bsi, ext);
                  if (!isigs.isEmpty()) {
                    SIGNATURES_BY_ID.put(ext, isigs);
                  }
                });
        return bsi;
      }
      throw new SipException("Unable to found Droid droid_signaturefile.xml in resource");
    } catch (IOException ex) {
      throw new SipException("Unable to init Droid signatures identifier", ex);
    } finally {
      if (signatureTmpPath != null) {
        try {
          Files.deleteIfExists(signatureTmpPath);
        } catch (IOException e) {
          // Ignore
        }
      }
    }
  }

  /**
   * Initialise les identifiants binaires des signatures Droid à partir d'un fichier de signature.
   * Note. Le fichier de signatures doit se trouver sur le système de fichier par défaut.
   *
   * @param signaturePath le path du fichier de signature
   * @return les identifiants binaires des signatures
   */
  public static BinarySignatureIdentifier initDroidSignatures(Path signaturePath) {
    Validate.notNull(signaturePath, NOT_NULL, "signaturePath");

    try {
      BinarySignatureIdentifier bsi = new BinarySignatureIdentifier();
      bsi.setSignatureFile(signaturePath.toString());
      bsi.init();
      return bsi;
    } catch (SignatureParseException ex) {
      throw new SipException("Unable to init Droid signatures identifier", ex);
    }
  }

  public static boolean isSupportedFormat(String puid) {
    return DroidSignaturesHolder.INSTANCE.getSigFile().getFileFormat(puid) != null;
  }

  public static boolean isSupportedExtension(String ext) {
    return ext != null && SIGNATURES_BY_ID.containsKey(ext.toUpperCase());
  }

  /**
   * Identifie le format du fichier spécifié par le path en utilisant les identifiants binaire des
   * signatures par défaut. Note. Les paths vers un système de fichier virtuel (ie. zip) ne sont pas
   * supportés.
   *
   * @param path le path du fichier à identifier
   * @param extension l'extension du fichier à identifier
   * @return la liste de résultats de l'identification.
   */
  public static List<IdentificationResult> matchBinarySignatures(Path path, String extension) {
    Validate.notNull(path, NOT_NULL, "path");
    return matchBinarySignatures(path, extension, DroidSignaturesHolder.INSTANCE);
  }

  /**
   * Identifie le format du fichier spécifié par le path en utilisant les identifiants binaire des
   * signatures par défaut. Note. Les paths vers un système de fichier virtuel (ie. zip) ne sont pas
   * supportés.
   *
   * @param path le path du fichier à identifier
   * @param extension l'extension du fichier à identifier
   * @param bsi les identifiants binaires des signatures
   * @return la liste de résultats de l'identification.
   */
  public static List<IdentificationResult> matchBinarySignatures(
      Path path, String extension, BinarySignatureIdentifier bsi) {

    RequestIdentifier identifier = new RequestIdentifier(path.toUri());
    identifier.setParentId(1L);

    try (IdentificationRequest<Path> request =
        new FileSystemIdentificationRequest(createMetadata(path), identifier)) {
      request.open(path);

      if (!StringUtils.isBlank(extension)) {
        List<InternalSignature> intSigs = SIGNATURES_BY_ID.get(extension.toUpperCase());
        if (intSigs != null) {
          List<IdentificationResult> results = matchBinarySignatures(request, intSigs).getResults();
          if (!results.isEmpty()) {
            return results;
          }
        }
      }
      return bsi.matchBinarySignatures(request).getResults();
    } catch (IOException ex) {
      throw new SipException("Unable to matchBinarySignatures for " + path, ex);
    }
  }

  private static RequestMetaData createMetadata(Path path) throws IOException {
    return new RequestMetaData(
        Files.size(path),
        Files.getLastModifiedTime(path).toMillis(),
        path.getFileName().toString());
  }

  private static IdentificationResultCollection matchBinarySignatures(
      IdentificationRequest<?> request, List<InternalSignature> intSigs) {

    IdentificationResultCollection results = new IdentificationResultCollection(request);
    results.setRequestMetaData(request.getRequestMetaData());
    ByteReader byteReader = new IdentificationRequestByteReaderAdapter(request);
    runFileIdentification(byteReader, intSigs);
    final int numHits = byteReader.getNumHits();
    for (int i = 0; i < numHits; i++) {
      FileFormatHit hit = byteReader.getHit(i);
      IdentificationResultImpl result = new IdentificationResultImpl();
      result.setMimeType(hit.getMimeType());
      result.setName(hit.getFileFormatName());
      result.setVersion(hit.getFileFormatVersion());
      result.setPuid(hit.getFileFormatPUID());
      result.setMethod(IdentificationMethod.BINARY_SIGNATURE);
      results.addResult(result);
    }
    results.setFileLength(request.size());
    results.setRequestMetaData(request.getRequestMetaData());
    return results;
  }

  private static void runFileIdentification(
      final ByteReader targetFile, List<InternalSignature> intSigs) {

    final List<InternalSignature> matchingSigs = getMatchingSignatures(targetFile, intSigs);
    for (final InternalSignature internalSig : matchingSigs) {
      targetFile.setPositiveIdent();
      final int numFileFormats = internalSig.getNumFileFormats();
      for (int fileFormatIndex = 0; fileFormatIndex < numFileFormats; fileFormatIndex++) {
        final FileFormatHit fileHit =
            new FileFormatHit(
                internalSig.getFileFormat(fileFormatIndex),
                FileFormatHit.HIT_TYPE_POSITIVE_GENERIC_OR_SPECIFIC,
                internalSig.isSpecific(),
                "");
        targetFile.addHit(fileHit);
      }
    }
  }

  private static List<InternalSignature> getMatchingSignatures(
      ByteReader targetFile, List<InternalSignature> intSigs) {

    List<InternalSignature> matchingSigs = new ArrayList<>();
    if (targetFile.getNumBytes() > 0) {
      for (final InternalSignature internalSig : intSigs) {
        if (internalSig.matches(targetFile, -1)) {
          matchingSigs.add(internalSig);
        }
      }
    }
    return matchingSigs;
  }

  private static List<InternalSignature> buildInternalSignatures(
      BinarySignatureIdentifier bsi, String extension) {

    Map<Integer, InternalSignature> intSignsMap = new HashMap<>();
    List<InternalSignature> signatures = bsi.getSigFile().getSignatures();
    for (InternalSignature isig : signatures) {
      for (int i = 0; i < isig.getNumFileFormats(); i++) {
        FileFormat format = isig.getFileFormat(i);
        for (String ext : format.getExtensions()) {
          if (extension.equalsIgnoreCase(ext)) {
            intSignsMap.put(isig.getID(), isig);
          }
        }
      }
    }
    return new ArrayList<>(intSignsMap.values());
  }

  /** Allow lazy initialization of Droid Signatures */
  private static class DroidSignaturesHolder {

    private static final BinarySignatureIdentifier INSTANCE = initDroidSignatures();
  }
}
