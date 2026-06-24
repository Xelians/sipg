package fr.xelians.sipg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.nationalarchives.droid.core.BinarySignatureIdentifier;
import uk.gov.nationalarchives.droid.core.IdentificationRequestByteReaderAdapter;
import uk.gov.nationalarchives.droid.core.SignatureParseException;
import uk.gov.nationalarchives.droid.core.interfaces.*;
import uk.gov.nationalarchives.droid.core.interfaces.resource.FileSystemIdentificationRequest;
import uk.gov.nationalarchives.droid.core.interfaces.resource.RequestMetaData;
import uk.gov.nationalarchives.droid.core.signature.ByteReader;
import uk.gov.nationalarchives.droid.core.signature.FileFormatHit;
import uk.gov.nationalarchives.droid.core.signature.droid6.InternalSignature;

/*
 * @author Emmanuel Deviller
 */
public final class DroidUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(DroidUtils.class);

  private static final Map<String, List<InternalSignature>> SIGNATURES = new HashMap<>();
  private static final long MAX_BYTES_TO_SCAN = -1L; // Scan all bytes

  private static final String SIGNATURE_PATH_MUST_NOT_BE_NULL = "signaturePath must not be null";
  private static final String PATH_MUST_NOT_BE_NULL = "path must not be null";

  // Plain text and json have no binary signature in Droid, so they are identified by extension.
  private static final List<IdentificationResult> PLAIN_TEXT_RESULT =
      List.of(createResult("x-fmt/111", "Plain Text File", "txt", "text/plain"));
  private static final List<IdentificationResult> JSON_RESULT =
      List.of(createResult("fmt/817", "JSON Data Interchange Format", "json", "application/json"));

  private DroidUtils() {}

  private static IdentificationResult createResult(
      String puid, String name, String version, String mimeType) {
    IdentificationResultImpl result = new IdentificationResultImpl();
    result.setPuid(puid);
    result.setName(name);
    result.setVersion(version);
    result.setMimeType(mimeType);
    result.setMethod(IdentificationMethod.EXTENSION);
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
    return StringUtils.isNotBlank(ext) && SIGNATURES.containsKey(ext.toUpperCase());
  }

  public static List<IdentificationResult> matchBinarySignatures(
      Path path, @Nullable String extension, boolean matchExtension) {
    Validate.notNull(path, PATH_MUST_NOT_BE_NULL);
    return matchBinarySignatures(path, extension, DroidSignaturesHolder.INSTANCE, matchExtension);
  }

  public static List<IdentificationResult> matchBinarySignatures(
      Path path,
      @Nullable String extension,
      BinarySignatureIdentifier bsi,
      boolean matchExtension) {

    RequestIdentifier identifier = new RequestIdentifier(path.toUri());
    identifier.setParentId(1L);

    try (FileSystemIdentificationRequest request =
        new FileSystemIdentificationRequest(createMetadata(path), identifier)) {
      request.setExtension(extension);
      request.open(path);

      // Optimized path
      if (StringUtils.isNotBlank(extension)) {
        List<InternalSignature> intSigs = SIGNATURES.get(extension.toUpperCase());
        if (intSigs != null) {
          List<IdentificationResult> results = matchBinarySignatures(request, intSigs).getResults();
          if (!results.isEmpty()) {
            return results;
          }
        }
      }

      // Full but slow path
      List<IdentificationResult> results = bsi.matchBinarySignatures(request).getResults();
      if (!results.isEmpty()) {
        return results;
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

  /** Allow lazy initialization of Droid Signatures */
  private static class DroidSignaturesHolder {

    private static final BinarySignatureIdentifier INSTANCE = initDroidSignatures();

    /**
     * Initialise les identifiants binaires des signatures Droid à partir de la ressource par
     * défaut.
     *
     * @return les identifiants binaires des signatures
     */
    private static BinarySignatureIdentifier initDroidSignatures() {
      Path signaturePath = null;
      try (InputStream is = SipUtils.resourceAsStream("droid_signaturefile.xml")) {
        signaturePath = Files.createTempFile("droid", ".xml");
        Files.copy(is, signaturePath, StandardCopyOption.REPLACE_EXISTING);

        BinarySignatureIdentifier bsi = DroidUtils.initDroidSignatures(signaturePath);
        initInternalSignatures(bsi);
        return bsi;

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

    private static void initInternalSignatures(BinarySignatureIdentifier bsi) {
      for (InternalSignature signature : bsi.getSigFile().getSignatures()) {
        for (int i = 0; i < signature.getNumFileFormats(); i++) {
          for (String extension : signature.getFileFormat(i).getExtensions()) {
            String ext = extension.toUpperCase();
            SIGNATURES.computeIfAbsent(ext, k -> new ArrayList<>()).add(signature);
          }
        }
      }
    }
  }
}
