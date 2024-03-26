package Mbus;

import java.util.List;

public interface interfaceDecoder {
    InfoOfPackage DecodePackage(byte[] CurrentBytes, byte[] PreviousBytes) throws Exception;

    String GetPackageType(byte[] CurrentBytes, Function currentFunc,Function previousFunc,Error error,String previousType,boolean CRCIsOk) throws Exception;
    int GetAddress(byte[] CurrentBytes) throws Exception;

    Function GetFunction(byte[] CurrentBytes) throws Exception;

    int GetStartRegisterAdr(byte[] CurrentBytes) throws Exception;



    int GetEndRegisterAdr(byte[] CurrentBytes,Function function,int StartRegisterAddress) throws Exception;

    List<MbusData> GetRegistersData(byte[] CurrentBytes,int StartRegisterAdr,int EndRegisterAdr)throws Exception;

    int GetCountOfDataBytes(byte[]CurrentBytes,Function function,String TypePackage)throws Exception;

    Error CheckErr(byte[]CurrentBytes)throws Exception;

    int GetCRC(byte[] CurrentBytes)throws Exception;
    int CalculateCRC(byte []CurrentBytes)throws Exception;

    boolean CheckCRC(byte[] CurrentBytes)throws Exception;

}
