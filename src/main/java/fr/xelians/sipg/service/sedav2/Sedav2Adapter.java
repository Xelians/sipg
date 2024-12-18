package fr.xelians.sipg.service.sedav2;

import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import java.nio.file.Path;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

public interface Sedav2Adapter {

  void write(ArchiveTransfer archive, Validator validator, Path zipPath, Sedav2Config config);

  void write(
      ArchiveDeliveryRequestReply archive, Validator validator, Path zipPath, Sedav2Config config);

  void validate(ArchiveTransfer archive, Validator validator, Sedav2Config config);

  void validate(Source source, Sedav2Config config);
}
