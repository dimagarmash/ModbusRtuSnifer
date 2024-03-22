package Mbus;

import java.util.List;

public class Decoder extends Functions implements interfaceDecoder  {
    
    @Override
    public InfoOfPackage DecodePackage(byte[] CurrentBytes, byte[] PreviousBytes)
    {

        return new InfoOfPackage();
    }
    @Override
    public String GetPackageType(byte[] CurrentBytes, byte[] PreviousBytes)
    {

        return null;
    }

    @Override
    public String GetAddress(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public Function GetFunction(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public String GetStartRegisterAdr(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public String GetEndRegisterAdr(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public List<MbusData> GetRegistersData(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public String GetCRC(byte[] CurrentBytes) {
        return null;
    }

    @Override
    public boolean CheckCRC(byte[] CurrentBytes) {
        return false;
    }
}
