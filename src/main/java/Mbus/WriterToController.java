package Mbus;

import javafx.application.Platform;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dgg.modbusrtusnifer.HelloController;

public class WriterToController implements WriterToControllerInterface {
    private static final Logger logger= LogManager.getLogger(WriterToController.class);
    ControllerInterface controller;
    String textDelimitter="\r\n_________________________________________________________________\r\n";

    @Override
    public void Initialize(ControllerInterface controller1)
    {
        controller=controller1;
    }
    @Override
    public void WriteInfoPackage(InfoOfPackage infoOfPackage){
        //реализовать  запись в text field
        String text=textDelimitter+infoOfPackage.toString()+textDelimitter;
        logger.log(Level.INFO,"InfoOfPackage : "+text);
        Platform.runLater(() -> {
            try {
                controller.SetText(text);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARN, "Warning Server controller.SetText exception", e);
                logger.log(Level.INFO, "Request:" + text);
            }
        });
    }

    @Override
    public void WriteMessage(String text) {
        logger.log(Level.INFO,"Message : "+text);
        text=textDelimitter+text+textDelimitter;
        String finalText = text;
        Platform.runLater(() -> {
            try {
                controller.SetText(finalText);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARN, "Warning Server controller.SetText exception", e);
                logger.log(Level.INFO, "Request:" + finalText);
            }
        });
    }
}
