package Mbus;

import org.dgg.modbusrtusnifer.HelloController;

public interface WriterToControllerInterface {
    void Initialize(HelloController controller1);

    void WriteInfoPackage(InfoOfPackage infoOfPackage);
}
