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
    String newline="\r\n";
    String newlinet="\r\n\t";
    @Override
    public String toString() {
        String bytes =null;
        String fnumber=null;
        String fdescription=null;
        if (function!=null)
        {
            fdescription=function.Description;
            fnumber=function.Number;
        }
        if (PackageBytes!=null){
            bytes=Arrays.toString(PackageBytes);
        }
        return "InfoOfPackage" +"\r\n\t{"+
                "PackageBytes=" + bytes +newlinet+
                ",PackageType='" + PackageType + '\'' +newlinet+
                ",Address=" + Address +newlinet+
                ",function=" + fnumber +" "+fdescription+newlinet+
                ",StartRegisterAddress=" + StartRegisterAddress +newlinet+
                ",EndRegisterAddress=" + EndRegisterAddress +newlinet+
                ",DataBytesCount=" + DataBytesCount +newlinet+
                ",RegisterData=" + "\t"+RegisterData + newlinet+
                ",error=" + error +newlinet+
                ",CRC=" + CRC +newlinet+
                ",CalculatedCRC=" + CalculatedCRC +newlinet+
                ",CRCIsOk=" + CRCIsOk +newlinet+
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

    public InfoOfPackage() {
        PackageType = null;
        Address = -1;
        this.function = null;
        StartRegisterAddress = -1;
        EndRegisterAddress = -1;
        RegisterData = null;
        this.CRC = -1;
        this.CRCIsOk = false;
        PackageBytes=null;
        DataBytesCount=-1;
        error=null;
        CalculatedCRC=-1;
    }
}
