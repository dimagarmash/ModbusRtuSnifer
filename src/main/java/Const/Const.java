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
        MASTER;
        public static boolean IsExistValue(String value) {
            boolean result = false;
            for (ProgrammMode mode : values()) {
                if (mode.toString().equals(value)) {
                    result = true;
                    return result;
                }
            }
            return result;
        }
    }
    public static enum BaudRates{
        R9600(9600),
        R14400(14400),
        R19200(19200),
        R38400(38400),
        R57600(57600),
        R115200(115200),
        R128000(128000);
        private int numVal;

        BaudRates(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
        public static boolean IsExistValue(int value) {
            boolean result = false;
            for (BaudRates baudRates : values()) {
                if (baudRates.getNumVal()==value) {
                    result = true;
                    return result;
                }
            }
            return result;
        }

    }
    public static enum Paritу
    {
        None(0),
        Odd(1),
        Even(2),
        Mark (3),
        Space(4);
        private int numVal;

        Paritу(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
        public static boolean IsExistValue(int value) {
            boolean result = false;
            for (Paritу paritу : values()) {
                if (paritу.getNumVal()==value) {
                    result = true;
                    return result;
                }
            }
            return result;
        }
    }
    public static enum StopBits{

        BITS_1 (1),
        BITS_1p5  (2),
        BITS_2  (3);
        private int numVal;

        StopBits(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
        public static boolean IsExistValue(int value) {
            boolean result = false;
            for (StopBits stopBits : values()) {
                if (stopBits.getNumVal()==value) {
                    result = true;
                    return result;
                }
            }
            return result;
        }

    }
    public static enum DataBits{

        BITS_4 (4),
        BITS_5(5),
        BITS_6(6),
        BITS_7(7),
        BITS_8(8);
        private int numVal;

        DataBits(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }

        public static boolean IsExistValue(int value) {
            boolean result = false;
            for (DataBits dataBits : values()) {
                if (dataBits.getNumVal()==value) {
                    result = true;
                    return result;
                }
            }
            return result;
        }
    }


    public static final String PathToSettingsFile= "\\Settings.properties";

    public static final String DefaultPort="COM1";

    public static final int DefaultBaudRate=115200;
    public static final int DefaultParity=Paritу.None.getNumVal();
    public static final int DefaultStopBits=StopBits.BITS_1.getNumVal();
    public static final int DefaultDataBits=DataBits.BITS_8.getNumVal();
    public static final Const.ProgrammMode DefaultPMode=ProgrammMode.SNIFF;
}
