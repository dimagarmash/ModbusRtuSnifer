package Mbus;

import org.dgg.modbusrtusnifer.HelloController;

public interface WriterToControllerInterface {
    void Initialize(ControllerInterface controller1);

    void WriteInfoPackage(InfoOfPackage infoOfPackage);
    void WriteMessage(String text);
}
