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
    public static enum ProgrammMode{
        SNIFF,
        SLAVE,
        MASTER
    }
    public static final String PathToSettingsFile= "\\Settings.properties";

    public static final String DefaultPort="COM1";
    public static final int DefaultBaudRate=115200;
    public static final int DefaultParity=0;
    public static final int DefaultStopBits=1;
    public static final int DefaultDataBits=8;
    public static final Const.ProgrammMode DefaultPMode=ProgrammMode.SNIFF;
}
