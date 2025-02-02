package fr.xelians.sipg.service.seda;

import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import java.nio.file.Path;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

public interface SedaAdapter {

  void write(ArchiveTransfer archive, Validator validator, Path zipPath, SedaConfig config);

  void write(
      ArchiveDeliveryRequestReply archive, Validator validator, Path zipPath, SedaConfig config);

  void validate(ArchiveTransfer archive, Validator validator, SedaConfig config);

  void validate(Source source, SedaConfig config);
}
