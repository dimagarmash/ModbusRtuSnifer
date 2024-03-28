package Mbus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    byte[]CurrentBytes;
    interfaceDecoder decoder=new Decoder();
    @Test
    void decodePackage() {


    }

    @Test
    void getPackageType() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void getFunction() {
    }

    @Test
    void getStartRegisterAdr() {
    }

    @Test
    void getEndRegisterAdr() {
    }

    @Test
    void getRegistersData() {
    }

    @Test
    void getCountOfDataBytes() {
    }

    @ParameterizedTest
    @CsvSource({"'0x01 0x03 0x00 0x00 0x00 0x01 0x84 0x0A',NoError","'0x0a 0x81 0x02 0xb0 0x53','IllegalDataAddress'"})
    void checkErr(String bytes ,String Expected) {
        String[] parts = bytes.split(" ");
        Error error=new Error();
        CurrentBytes = new byte[parts.length];
        for (int i = 0; i < parts.length; i++)
            CurrentBytes[i] = (byte) (Integer.decode(parts[i])&0xff);

        try {
            error=decoder.CheckErr(CurrentBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(Expected,error.RestrictedDescription);
    }

    @Test
    void getCRC() {
    }

    @Test
    void calculateCRC() {
    }

    @Test
    void checkCRC() {
    }
}