package Mbus;

import java.util.Arrays;
import java.util.List;

public class InfoOfPackage {
    byte[] PackageBytes;
    String PackageType;
    int Address;
    Function function;

    int StartRegisterAddress;
    int EndRegisterAddress;

    int DataBytesCount;
    List<MbusData> RegisterData;
    Error error;
    int CRC;
    int CalculatedCRC;
    boolean CRCIsOk;

    @Override
    public String toString() {
        return "InfoOfPackage{" +
                "PackageBytes=" + Arrays.toString(PackageBytes) +
                ", PackageType='" + PackageType + '\'' +
                ", Address=" + Address +
                ", function=" + function +
                ", StartRegisterAddress=" + StartRegisterAddress +
                ", EndRegisterAddress=" + EndRegisterAddress +
                ", DataBytesCount=" + DataBytesCount +
                ", RegisterData=" + RegisterData +
                ", error=" + error +
                ", CRC=" + CRC +
                ", CalculatedCRC=" + CalculatedCRC +
                ", CRCIsOk=" + CRCIsOk +
                '}';
    }

    public InfoOfPackage(byte[] packageBytes, String packageType, int address, Function function, int startRegisterAddress, int endRegisterAddress, List<MbusData> registerData, int CRC, boolean CRCIsOk, int dataBytesCount, Error e, int calculatedCrc) {
        PackageType = packageType;
        Address = address;
        this.function = function;
        StartRegisterAddress = startRegisterAddress;
        EndRegisterAddress = endRegisterAddress;
        RegisterData = registerData;
        this.CRC = CRC;
        this.CRCIsOk = CRCIsOk;
        PackageBytes=packageBytes;
        DataBytesCount=dataBytesCount;
        error=e;
        CalculatedCRC=calculatedCrc;
    }


}
