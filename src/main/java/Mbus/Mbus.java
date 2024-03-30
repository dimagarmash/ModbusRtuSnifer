package Mbus;



import java.util.Arrays;

import Settings.Settings;
import com.fazecast.jSerialComm.*;

public class Mbus  extends Thread{






        PackagesInfo packagesInfo;
     InfoOfPackage PreviousInfo;

      SerialPort serialPort;
     PortListener portListener;



    @Override
    public void run() {

        OpenPort();
    }
    public synchronized void MbusInitialize(PackagesInfo packagesInfo,InfoOfPackage previousInfo){

            this.packagesInfo=packagesInfo;
            PreviousInfo=previousInfo;
       try {
           serialPort =serialInitialze(Settings.getPort(),Settings.getBaudRate(),Settings.getDataBits(),Settings.getStopBits(),Settings.getParity());
           if (serialPort!=null){
               portListener=new PortListener();
               portListener.ListenerInitialize(serialPort,this.packagesInfo,PreviousInfo);
               serialPort.addDataListener(portListener);
           }
       }catch (Exception e){
           e.printStackTrace();
       }


    }


    public static SerialPort serialInitialze(String Port,int BaudRate,int Databits,int StopBits,int Parity)
    {
        SerialPort result =null;
        SerialPort [] serialPorts=  SerialPort.getCommPorts();
        try {
            for (SerialPort s : serialPorts){
                System.out.println("Avaliable ports : "+s.getSystemPortName());
                if (s.getSystemPortName().equals(Port)){

                    s.setBaudRate(BaudRate);
                    s.setParity(Parity);
                    s.setNumStopBits(StopBits);
                    s.setNumDataBits(Databits);

                    System.out.println("___________________________________________________");
                    System.out.println("SystemPortName()  : "+s.getSystemPortName());
                    System.out.println("getNumDataBits()  : "+s.getNumDataBits());
                    System.out.println("getBaudRate()  : "+s.getBaudRate());
                    System.out.println("getNumStopBits()  : "+s.getNumStopBits());
                    System.out.println("getParity()  : "+s.getParity());

                    System.out.println("___________________________________________________");
                    result=s;
                    return result;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
    public boolean OpenPort(){
     boolean result=false;
     try {
         serialPort.openPort();
     }catch (Exception e){
         e.printStackTrace();
     }
     return result;
    }


    private static class PortListener implements SerialPortDataListener {

        SerialPort serialPort;

        PackagesInfo packagesInfo;
        InfoOfPackage PreviousInfo;
        void ListenerInitialize( SerialPort serialPort,PackagesInfo Info,InfoOfPackage previousInfo){
            this.serialPort=serialPort;
            packagesInfo=Info;
            PreviousInfo=previousInfo;
        }
        @Override
        public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
        @Override
        public void serialEvent(SerialPortEvent event)
        {
            if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                return;
            byte[] newData = new byte[serialPort.bytesAvailable()];

            int numRead = serialPort.readBytes(newData, newData.length);

            String data= Arrays.toString(newData);
            System.out.println("Read " + numRead + " bytes.");
            System.out.println("readed data: "+data);

            StringBuilder sb = new StringBuilder();
            for (byte b : newData)
            {
                sb.append(String.format("%02X ", b));
            }
            System.out.println("After StringBuilder");
            System.out.println(sb.toString());

            Decoder decoder =new Decoder();
            try {
                System.out.println("##########################################################");
                System.out.println(PreviousInfo);
                System.out.println("##########################################################");
                packagesInfo.setCurrentInfo(decoder.DecodePackage(newData,PreviousInfo));
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(packagesInfo.getCurrentInfo());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            } catch (Exception e) {
                e.printStackTrace();
            }
            PreviousInfo=packagesInfo.getCurrentInfo();
            //Settings s =new Settings();

        }
    }

}





