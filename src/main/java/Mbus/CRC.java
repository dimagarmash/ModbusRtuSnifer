package Mbus;

public class CRC {
    private static int CRC_X;
    public static int UpdateCRC_Modbus(byte Data,int crc16)
    {
        int temp;
        int flag;
        temp = Data & 0xff; // temp has the first byte
        temp &= 0x00ff; // mask the MSB
        crc16 = crc16 ^ temp; //crc16 XOR with temp
        for (int c = 0; c < 8; c++)
        {
            flag = crc16 & 0x01; // LSBit di crc16 is mantained
            crc16 = crc16 >> 1;// Lsbit di crc16 is lost
            if (flag != 0) {

                crc16 = crc16 ^ 0x0a001;
            }// crc16 XOR with 0x0a001
        }
        return crc16;
    }

    public static int UpdateCRC_BT(byte Data, int crc16)
    {
        int temp;
        int flag;
        temp = Data & 0xff; // temp has the first byte
        temp &= 0x00ff; // mask the MSB
        crc16 = crc16 ^ temp; //crc16 XOR with temp
        for (int c = 0; c < 8; c++)
        {
            flag = crc16 & 0x01; // LSBit di crc16 is mantained
            crc16 = crc16 >> 1; // Lsbit di crc16 is lost
            if (flag != 0)
                crc16 = crc16 ^ 0x08408; // crc16 XOR with 0x0a001
        }
        return crc16;
    }

    public static int UpdateCRC_X(byte Data, int crc16)
    {

        //            CRC_X = CRC_X & 0x00FFFF;
        //            CRC_X = CRC_X | 0x008000;

        int temp;
        int flag;
        temp = Data& 0xff; // temp has the first byte
        temp &= 0x00ff; // mask the MSB
        crc16 = crc16 ^ temp; //crc16 XOR with temp
        for (int c = 0; c < 8; c++)
        {
            flag = crc16 & 0x01; // LSBit di crc16 is mantained
            crc16 = crc16 >> 1; // Lsbit di crc16 is lost
            if (flag != 0)
                crc16 = crc16 ^ CRC_X; // crc16 XOR with 0x0a001
        }
        return crc16;
    }

    public static int INIT_CRC()
    {
        int crc16 = 0xffff;
        return crc16 ;
    }
    public static int GETXCRC()
    {
        return CRC_X;
    }
    public static void GetEncriptionalPolinominal(int left,int right)
    {
        CRC_X = left;
        CRC_X = CRC_X << 8;
        CRC_X = CRC_X + right;
        CRC_X = CRC_X & 0x00FFFF;
        CRC_X = CRC_X | 0x008000;
        return ;

    }

}