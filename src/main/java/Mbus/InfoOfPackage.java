package Mbus;

import java.util.List;

public class InfoOfPackage {
    String PackageType;
    String Address;
    Function function;

    String StartRegisterAddress;
    String EndRegisterAddress;
    List<MbusData> RegisterData;
    String CRC;
    boolean CRCIsOk;

    public InfoOfPackage(String packageType, String address, Function function, String startRegisterAddress, String endRegisterAddress, List<MbusData> registerData, String CRC, boolean CRCIsOk) {
        PackageType = packageType;
        Address = address;
        this.function = function;
        StartRegisterAddress = startRegisterAddress;
        EndRegisterAddress = endRegisterAddress;
        RegisterData = registerData;
        this.CRC = CRC;
        this.CRCIsOk = CRCIsOk;
    }
}
