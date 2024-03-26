package Const;

public class Const {
    public static final int minLengthOfPackage=5;
    public static final int maxDevAdr=255;
    public static final int minDevAdr=0;

    public static final int maxRegisterAdr=65535;
    public static final int AddressPositionInPackage=0;
    public static final int FunctionNumberPositionInPackage=1;

    public static final int FirstAddressRegisterPositionInPackage=2;
    public static final int CountRegistersPositionInPackage=4;
    public static int ErrorNumberPositionInPackage=2;
    public static final int CountDataBytesInResponsePositionInPackage=2;
    public static enum PackageType{
        ERROR,
        UNKNOWN,
        REQUEST,
        RESPONSE,
        NOT_RECOGNIZED
    }
}
