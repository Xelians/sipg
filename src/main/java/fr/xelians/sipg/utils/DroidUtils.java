package fr.xelians.sipg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.nationalarchives.droid.container.AbstractContainerIdentifier;
import uk.gov.nationalarchives.droid.container.AbstractIdentifierEngine;
import uk.gov.nationalarchives.droid.container.ContainerFileIdentificationRequestFactory;
import uk.gov.nationalarchives.droid.container.ContainerSignatureFileReader;
import uk.gov.nationalarchives.droid.container.ole2.Ole2Identifier;
import uk.gov.nationalarchives.droid.container.zip.ZipIdentifier;
import uk.gov.nationalarchives.droid.core.BinarySignatureIdentifier;
import uk.gov.nationalarchives.droid.core.IdentificationRequestByteReaderAdapter;
import uk.gov.nationalarchives.droid.core.SignatureParseException;
import uk.gov.nationalarchives.droid.core.interfaces.*;
import uk.gov.nationalarchives.droid.core.interfaces.archive.ArchiveFormatResolver;
import uk.gov.nationalarchives.droid.core.interfaces.archive.ArchiveFormatResolverImpl;
import uk.gov.nationalarchives.droid.core.interfaces.archive.ContainerIdentifierFactory;
import uk.gov.nationalarchives.droid.core.interfaces.archive.ContainerIdentifierFactoryImpl;
import uk.gov.nationalarchives.droid.core.interfaces.resource.FileSystemIdentificationRequest;
import uk.gov.nationalarchives.droid.core.interfaces.resource.RequestMetaData;
import uk.gov.nationalarchives.droid.core.interfaces.signature.SignatureFileException;
import uk.gov.nationalarchives.droid.core.signature.ByteReader;
import uk.gov.nationalarchives.droid.core.signature.FileFormat;
import uk.gov.nationalarchives.droid.core.signature.FileFormatHit;
import uk.gov.nationalarchives.droid.core.signature.droid6.InternalSignature;

/*
 * @author Emmanuel Deviller
 */
public final class DroidUtils {

  /** Le nom de la ressource du fichier de signatures DROID embarqué dans sipg. */
  public static final String DEFAULT_SIGNATURE_FILE = "droid_signaturefile.xml";

  /**
   * Le nom de la ressource du fichier de signatures de conteneur DROID embarqué dans sipg. Il date
   * du même jour que {@link #DEFAULT_SIGNATURE_FILE}, dont il référence les formats.
   */
  public static final String CONTAINER_SIGNATURE_FILE = "droid_container_signaturefile.xml";

  private static final Logger LOGGER = LoggerFactory.getLogger(DroidUtils.class);

  private static final long MAX_BYTES_TO_SCAN = -1L; // Scan all bytes

  private static final String SIGNATURE_PATH_MUST_NOT_BE_NULL = "signaturePath must not be null";
  private static final String PATH_MUST_NOT_BE_NULL = "path must not be null";

  // Plain text and json have no binary signature in Droid, so they are identified by extension.
  private static final List<IdentificationResult> PLAIN_TEXT_RESULT =
      List.of(
          createResult(
              "x-fmt/111", "Plain Text File", "txt", "text/plain", IdentificationMethod.EXTENSION));
  private static final List<IdentificationResult> JSON_RESULT =
      List.of(
          createResult(
              "fmt/817",
              "JSON Data Interchange Format",
              "json",
              "application/json",
              IdentificationMethod.EXTENSION));

  private DroidUtils() {}

  private static IdentificationResult createResult(
      String puid, String name, String version, String mimeType, IdentificationMethod method) {
    IdentificationResultImpl result = new IdentificationResultImpl();
    result.setPuid(puid);
    result.setName(name);
    result.setVersion(version);
    result.setMimeType(mimeType);
    result.setMethod(method);
    return result;
  }

  public static BinarySignatureIdentifier initDroidSignatures(Path signaturePath) {
    Validate.notNull(signaturePath, SIGNATURE_PATH_MUST_NOT_BE_NULL);

    try {
      BinarySignatureIdentifier bsi = new BinarySignatureIdentifier();
      bsi.setSignatureFile(signaturePath.toString());
      bsi.init();
      bsi.getSigFile().setMaxBytesToScan(MAX_BYTES_TO_SCAN);
      return bsi;
    } catch (SignatureParseException ex) {
      throw new SipException("Unable to init Droid signatures identifier", ex);
    }
  }

  public static boolean isSupportedFormat(@Nullable String puid) {
    return StringUtils.isNotBlank(puid)
        && DroidSignaturesHolder.INSTANCE.getSigFile().getFileFormat(puid) != null;
  }

  public static boolean isSupportedExtension(@Nullable String ext) {
    return StringUtils.isNotBlank(ext)
        && DroidSignaturesHolder.SIGNATURES.containsKey(ext.toUpperCase());
  }

  public static List<IdentificationResult> matchBinarySignatures(
      Path path, @Nullable String extension, boolean matchExtension) {
    Validate.notNull(path, PATH_MUST_NOT_BE_NULL);
    return matchBinarySignatures(
        path,
        extension,
        DroidSignaturesHolder.INSTANCE,
        DroidSignaturesHolder.SIGNATURES,
        matchExtension);
  }

  public static List<IdentificationResult> matchBinarySignatures(
      Path path,
      @Nullable String extension,
      BinarySignatureIdentifier bsi,
      boolean matchExtension) {
    // The extension index is built from the default signatures, so it does not apply to bsi
    return matchBinarySignatures(path, extension, bsi, Map.of(), matchExtension);
  }

  private static List<IdentificationResult> matchBinarySignatures(
      Path path,
      @Nullable String extension,
      BinarySignatureIdentifier bsi,
      Map<String, List<InternalSignature>> signatures,
      boolean matchExtension) {

    RequestIdentifier identifier = new RequestIdentifier(path.toUri());
    identifier.setParentId(1L);

    try (FileSystemIdentificationRequest request =
        new FileSystemIdentificationRequest(createMetadata(path), identifier)) {
      request.setExtension(extension);
      request.open(path);

      // Optimized path
      if (StringUtils.isNotBlank(extension)) {
        List<InternalSignature> intSigs = signatures.get(extension.toUpperCase());
        if (intSigs != null) {
          List<IdentificationResult> results = matchBinarySignatures(request, intSigs).getResults();
          if (!results.isEmpty()) {
            return matchContainerSignatures(request, results, bsi);
          }
        }
      }

      // Full but slow path
      List<IdentificationResult> results = bsi.matchBinarySignatures(request).getResults();
      if (!results.isEmpty()) {
        return matchContainerSignatures(request, results, bsi);
      }

      if (matchExtension) {
        return bsi.matchExtensions(request, true).getResults();
      }

      // Droid has no binary signature for plain text or json, so fall back to the extension.
      if ("jsn".equalsIgnoreCase(extension) || "json".equalsIgnoreCase(extension)) {
        return JSON_RESULT;
      }
      if ("txt".equalsIgnoreCase(extension) || "text".equalsIgnoreCase(extension)) {
        return PLAIN_TEXT_RESULT;
      }

      return Collections.emptyList();

    } catch (IOException ex) {
      throw new SipException(String.format("Unable to match binary signatures for %s", path), ex);
    }
  }

  // A container (zip, ole2) only gets its specific format (docx, xlsx, odt...) from the container
  // signatures, which DROID runs on the generic formats found by the binary signatures.
  private static List<IdentificationResult> matchContainerSignatures(
      IdentificationRequest<?> request,
      List<IdentificationResult> results,
      BinarySignatureIdentifier bsi) {

    String containerType = ContainerSignaturesHolder.RESOLVER.forPuid(results.getFirst().getPuid());
    if (containerType == null) {
      return results;
    }

    List<IdentificationResult> containerResults;
    try {
      containerResults =
          ContainerSignaturesHolder.FACTORY
              .getIdentifier(containerType)
              .submit(request)
              .getResults();
    } catch (IOException | RuntimeException ex) {
      // A damaged container keeps its generic format rather than failing the identification
      LOGGER.warn("Unable to match container signatures: {}", ex.getMessage());
      return results;
    }
    if (containerResults.isEmpty()) {
      return results;
    }

    // A container match only holds the puid: name, version and mime type come from the signatures
    List<IdentificationResult> enriched = new ArrayList<>();
    for (IdentificationResult result : containerResults) {
      FileFormat format = bsi.getSigFile().getFileFormat(result.getPuid());
      enriched.add(
          format == null
              ? result
              : createResult(
                  format.getPUID(),
                  format.getName(),
                  format.getVersion(),
                  format.getMimeType(),
                  IdentificationMethod.CONTAINER));
    }
    return enriched;
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
    ByteReader<?> byteReader = new IdentificationRequestByteReaderAdapter<>(request);
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
      final ByteReader<?> targetFile, List<InternalSignature> intSigs) {

    final List<InternalSignature> matchingSigs = getMatchingSignatures(targetFile, intSigs);
    for (final InternalSignature internalSig : matchingSigs) {
      targetFile.setPositiveIdent();
      final int numFileFormats = internalSig.getNumFileFormats();
      for (int fileFormatIndex = 0; fileFormatIndex < numFileFormats; fileFormatIndex++) {
        final FileFormatHit fileHit =
            new FileFormatHit(
                internalSig.getFileFormat(fileFormatIndex),
                FileFormatHit.HIT_TYPE_POSITIVE_GENERIC_OR_SPECIFIC,
                true,
                "");
        targetFile.addHit(fileHit);
      }
    }
  }

  private static List<InternalSignature> getMatchingSignatures(
      ByteReader<?> targetFile, List<InternalSignature> intSigs) {

    List<InternalSignature> matchingSigs = new ArrayList<>();
    if (targetFile.getNumBytes() > 0) {
      for (final InternalSignature internalSig : intSigs) {
        // Maybe we could optimize the match with a specific MAX_BYTES_TO_SCAN by signature type
        if (internalSig.matches(targetFile, MAX_BYTES_TO_SCAN)) {
          matchingSigs.add(internalSig);
          // Don't check for another signature
          break;
        }
      }
    }
    return matchingSigs;
  }

  // Droid reads its signature files from the file system, so the resource is copied to a temp file
  private static <T> T withResourceFile(String resource, Function<Path, T> reader) {
    Path signaturePath = null;
    try (InputStream is = SipUtils.resourceAsStream(resource)) {
      signaturePath = Files.createTempFile("droid", ".xml");
      Files.copy(is, signaturePath, StandardCopyOption.REPLACE_EXISTING);

      return reader.apply(signaturePath);

    } catch (IOException ex) {
      throw new SipException("Unable to init Droid signatures identifier", ex);
    } finally {
      if (signaturePath != null) {
        try {
          Files.deleteIfExists(signaturePath);
        } catch (IOException e) {
          LOGGER.error(e.getMessage());
        }
      }
    }
  }

  /** Allow lazy initialization of Droid Signatures */
  private static class DroidSignaturesHolder {

    private static final BinarySignatureIdentifier INSTANCE = initDroidSignatures();

    // Built with INSTANCE in the holder initialization, so it is never read before being filled
    private static final Map<String, List<InternalSignature>> SIGNATURES =
        indexByExtension(INSTANCE);

    /**
     * Initialise les identifiants binaires des signatures Droid à partir de la ressource par
     * défaut.
     *
     * @return les identifiants binaires des signatures
     */
    private static BinarySignatureIdentifier initDroidSignatures() {
      return withResourceFile(DEFAULT_SIGNATURE_FILE, DroidUtils::initDroidSignatures);
    }

    private static Map<String, List<InternalSignature>> indexByExtension(
        BinarySignatureIdentifier bsi) {
      Map<String, List<InternalSignature>> signatures = new HashMap<>();
      for (InternalSignature signature : bsi.getSigFile().getSignatures()) {
        for (int i = 0; i < signature.getNumFileFormats(); i++) {
          for (String extension : signature.getFileFormat(i).getExtensions()) {
            String ext = extension.toUpperCase();
            signatures.computeIfAbsent(ext, k -> new ArrayList<>()).add(signature);
          }
        }
      }
      return signatures;
    }
  }

  /** Allow lazy initialization of Droid container signatures */
  private static class ContainerSignaturesHolder {

    // Filled by the container identifiers init: trigger puid -> container type -> identifier
    private static final ArchiveFormatResolver RESOLVER = new ArchiveFormatResolverImpl();
    private static final ContainerIdentifierFactory FACTORY = new ContainerIdentifierFactoryImpl();

    static {
      withResourceFile(CONTAINER_SIGNATURE_FILE, ContainerSignaturesHolder::initIdentifiers);
    }

    private static Void initIdentifiers(Path signaturePath) {
      ContainerSignatureFileReader reader = new ContainerSignatureFileReader(signaturePath);
      ContainerFileIdentificationRequestFactory requestFactory =
          new ContainerFileIdentificationRequestFactory();
      try {
        for (AbstractContainerIdentifier identifier :
            List.of(new ZipIdentifier(), new Ole2Identifier())) {
          ((AbstractIdentifierEngine) identifier.getIdentifierEngine())
              .setRequestFactory(requestFactory);
          identifier.setSignatureReader(reader);
          identifier.setContainerIdentifierFactory(FACTORY);
          identifier.setContainerFormatResolver(RESOLVER);
          identifier.init();
        }
        return null;
      } catch (SignatureFileException ex) {
        throw new SipException("Unable to init Droid container signatures identifier", ex);
      }
    }
  }
}
