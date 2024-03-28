package Mbus;

import java.util.ArrayList;
import java.util.List;

public class MbusData {
    byte Hi;
    byte Lo;
    public MbusData(byte hi, byte lo) {
        Hi = hi;
        Lo = lo;
    }

    @Override
    public String toString() {
        return "MbusData{" +
                "Hi=" + Integer.toHexString(Hi&0xff) +
                ", Lo=" + Integer.toHexString(Lo&0xff) +
                '}';
    }

    int GetUnsignedValue()
    {
        int tmp;
        tmp = (((Hi) & 0xff) << 8) + ((Lo) & 0xFF);//делаем сдвиг старшего влево добавляем к старшему младший и младший делаем беззнаковым
        return tmp;
    }
    int GetSignedValue(){
        int tmp;
        if (Hi>=0)
        {
            tmp = (((Hi) & 0xff) << 8) + ((Lo) & 0xFF);//делаем сдвиг старшего влево добавляем к старшему младший и младший делаем беззнаковым
        }
        else {
            tmp = (((~Hi) & 0xff) << 8) + ((~Lo) & 0xFF);
            tmp=~tmp;
        }

        return tmp;
    }
    boolean[] GetBoleansMassive(){
        boolean[] array =new boolean[16];
        short mask=0x1;
        byte tmp=0;
        for (int i=0;i<array.length;i++){
            if (i==8){
                mask=0x1;
            }
            if (i<=7){
                tmp= (byte) (Lo&mask);
            }
            else {
                tmp= (byte) (Hi&mask);
            }
           if (tmp>0){
               array[i]=true;
           }else {
               array[i]=false;
           }

        }
        return array;
    }
    List<Boolean> GetBoleansList(){
        List<Boolean> array =new ArrayList<Boolean>();
        short mask=0x1;
        byte tmp=0;
        for (int i=0;i<16;i++){
            if (i==8){
                mask=0x1;
            }
            if (i<=7){
                tmp= (byte) (Lo&mask);
            }
            else {
                tmp= (byte) (Hi&mask);
            }
            if (tmp>0){
                array.add(true);
            }else {
                array.add(false);
            }

        }
        return array;
    }
    int[]GetBoleansArrayLikeInts(){
        int[] array =new int[16];
        short mask=0x1;
        byte tmp=0;
        for (int i=0;i<array.length;i++){
            if (i==8){
                mask=0x1;
            }
            if (i<=7){
                tmp= (byte) (Lo&mask);
            }
            else {
                tmp= (byte) (Hi&mask);
            }
            array[i]=tmp;

        }
        return array;
    }
    List<Integer>GetBoleansListLikeInts(){
        List<Integer> array=new ArrayList<Integer>();
        short mask=0x1;
        byte tmp=0;
        for (int i=0;i<16;i++){
            if (i==8){
                mask=0x1;
            }
            if (i<=7){
                tmp= (byte) (Lo&mask);
            }
            else {
                tmp= (byte) (Hi&mask);
            }
            array.add((int) tmp);

        }
        return array;
    }




}
