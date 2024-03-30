package Mbus;

import java.util.EventListener;
import java.util.EventObject;

import org.dgg.modbusrtusnifer.HelloController;

public class PackagesInfo  {


    InfoOfPackage CurrentInfo=new InfoOfPackage();
    InfoOfPackage PreviousInfo=new InfoOfPackage();
    HelloController controller;

    public void Initialize(HelloController controller1)
    {
        controller=controller1;
    }



    public InfoOfPackage getCurrentInfo() {
        return CurrentInfo;
    }

    public InfoOfPackage getPreviousInfo() {
        return PreviousInfo;
    }

    public void setCurrentInfo(InfoOfPackage currentInfo) {
        CurrentInfo = currentInfo;
        CurrentInfoChanged();
    }

    public void setPreviousInfo(InfoOfPackage previousInfo) {
        PreviousInfo = previousInfo;
    }
    private void CurrentInfoChanged(){

        System.out.println("Current info changed");
    }


}
