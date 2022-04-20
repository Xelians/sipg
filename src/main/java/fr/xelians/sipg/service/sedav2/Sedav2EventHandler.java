package fr.xelians.sipg.service.sedav2;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import java.net.URL;

public class Sedav2EventHandler implements ValidationEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sedav2EventHandler.class);

    public boolean handleEvent(ValidationEvent event) {

        if (event == null) {
            throw new IllegalArgumentException();
        }

        switch (event.getSeverity()) {
            case ValidationEvent.WARNING:
                LOGGER.info("Warning {} {}", event.getMessage(), getLocation(event));
                return true;
            case ValidationEvent.ERROR:
                LOGGER.info("Error {} {}", event.getMessage(), getLocation(event));
                return false;
            case ValidationEvent.FATAL_ERROR:
                LOGGER.info("FatalError {} {}", event.getMessage(), getLocation(event));
                return false ;
            default:
                assert false : "UnrecognizedSeverity";
        }
        return false ;
    }

    private String getLocation(ValidationEvent event) {
        StringBuilder msg = new StringBuilder();

        ValidationEventLocator locator = event.getLocator();

        if (locator != null) {
            URL url = locator.getURL();
            Object obj = locator.getObject();
            Node node = locator.getNode();
            int line = locator.getLineNumber();

            if (url != null || line != -1) {
                msg.append("line ").append(line);
                if (url != null) msg.append(" of ").append(url);
            } else if (obj != null) {
                msg.append(" obj: ").append(obj);
            } else if (node != null) {
                msg.append(" node: ").append(node);
            }
        }

        return msg.toString();
    }
}
