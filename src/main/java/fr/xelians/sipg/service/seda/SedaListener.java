package fr.xelians.sipg.service.seda;

import jakarta.xml.bind.Marshaller;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SedaListener extends Marshaller.Listener {

  private static final Logger LOGGER = LoggerFactory.getLogger(SedaListener.class);

  @Override
  public void beforeMarshal(Object source) {
    if (source != null) {
      LOGGER.info("Before: {}", ToStringBuilder.reflectionToString(source));
    }
  }

  @Override
  public void afterMarshal(Object source) {
    if (source != null) {
      LOGGER.info("After: {}", source);
    }
  }
}
