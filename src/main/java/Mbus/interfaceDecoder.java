package Mbus;

import java.util.List;

public interface interfaceDecoder {
    InfoOfPackage DecodePackage(byte[] CurrentBytes, byte[] PreviousBytes);

    String GetPackageType(byte[] CurrentBytes, byte[] PreviousBytes);
    String GetAddress(byte[] CurrentBytes);

    Function GetFunction(byte[] CurrentBytes);

    String GetStartRegisterAdr(byte[] CurrentBytes);
    String GetEndRegisterAdr(byte[] CurrentBytes);

    List<MbusData> GetRegistersData(byte[] CurrentBytes);

    String GetCRC(byte[] CurrentBytes);

    boolean CheckCRC(byte[] CurrentBytes);

}
