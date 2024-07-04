package Mbus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dgg.modbusrtusnifer.HelloController;

public class WriterToController implements WriterToControllerInterface {
    private static final Logger logger= LogManager.getLogger(WriterToController.class);
    HelloController controller;

    @Override
    public void Initialize(HelloController controller1)
    {
        controller=controller1;
    }
    @Override
    public void WriteInfoPackage(InfoOfPackage infoOfPackage){
        //реализовать  запись в text field
    }
}
