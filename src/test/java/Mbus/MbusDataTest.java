package Mbus;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MbusDataTest {

    MbusData mbusData;
    @ParameterizedTest
    @CsvFileSource(resources = "/testDataUint.csv",delimiter = '|',numLinesToSkip = 1)
    void getUnsignedValue(byte Hi ,byte Lo ,int Expected) {

        mbusData=new MbusData(Hi,Lo);
        assertEquals(Expected,mbusData.GetUnsignedValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataInt.csv",delimiter = '|',numLinesToSkip = 1)
    void getSignedValue(byte Hi ,byte Lo ,int Expected) {
        mbusData=new MbusData(Hi,Lo);
        assertEquals(Expected,mbusData.GetSignedValue());
    }

    @ParameterizedTest
    @CsvSource ({"-1,-1,'[true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true]'",
            "-1,0,'[false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true]'",
            "0,-1,'[true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false]'"})
    void getBoleansMassive(byte Hi ,byte Lo,String boolArr) {
        System.out.println(" getBoleansMassive");
        mbusData=new MbusData(Hi,Lo);
        String[] parts = boolArr.replace("[","").replace("]","").split(",");

        boolean[] array = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Boolean.parseBoolean(parts[i]);

        boolean[] actual=mbusData.GetBoleansMassive();
        System.out.println("Expected" +Arrays.toString( array));
        System.out.println("Actual" +Arrays.toString(actual));

        for (int i=0;i<array.length;i++){
            assertEquals(array[i],actual[i]);
        }
    }


    @ParameterizedTest
    @CsvSource ({"-1,-1,'[true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true]'",
            "-1,0,'[false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true]'",
            "0,-1,'[true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false]'"})
    void getBoleansList(byte Hi ,byte Lo,String boolArr) {
        System.out.println("getBoleansList");
        mbusData=new MbusData(Hi,Lo);

        List<Boolean> arrayb =new ArrayList<Boolean>();
        arrayb= mbusData.GetBoleansList();

        String[] parts = boolArr.replace("[","").replace("]","").split(",");

        boolean[] array = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Boolean.parseBoolean(parts[i]);


        boolean[]booleans=new boolean[arrayb.size()];
        int index = 0;
        for (Boolean b:arrayb){
            booleans[index++]=b;
        }
        System.out.println("Expected" +Arrays.toString(array));
        System.out.println("Actual" +Arrays.toString(booleans));

        for (int i=0;i<array.length;i++){
            assertEquals(array[i],booleans[i]);
        }

    }

    @ParameterizedTest
    @CsvSource ({"-1,-1,'[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]'",
            "-1,0,'[0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1]'",
            "0,-1,'[1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]'"})
    void getBoleansArrayLikeInts(byte Hi ,byte Lo,String intArr) {
        System.out.println(" getBoleansArrayLikeInts");
        mbusData=new MbusData(Hi,Lo);

        String[] parts = intArr.replace("[","").replace("]","").split(",");

        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Integer.parseInt(parts[i]);
        System.out.println("Expected " +Arrays.toString(array));
        System.out.println("Actual " +Arrays.toString(mbusData.GetBoleansArrayLikeInts()));
        assertArrayEquals(array,mbusData.GetBoleansArrayLikeInts());
    }

    @Test
    void getBoleansListLikeInts() {
    }
}