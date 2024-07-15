package Mbus;



import java.util.Arrays;

import Settings.Settings;
import com.fazecast.jSerialComm.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mbus  extends Thread implements interfaceMbus, RestrictedMbusInterface {


    private static final Logger logger= LogManager.getLogger(Mbus.class);

    WriterToControllerInterface writer;
    PackagesInfo packagesInfo=new PackagesInfo();
    InfoOfPackage PreviousInfo=new InfoOfPackage();
    SerialPort serialPort;
    PortListener portListener;



    @Override
    public void run() {

        //OpenPort();
    }

    @Override
    public void stopandcloseport() {

    }


    public synchronized void MbusInitialize(WriterToControllerInterface writer1){
        logger.log(Level.DEBUG,"Mbus Initialize");
        writer=writer1;
       try {
           serialPort =serialInitialze(Settings.getPort(),Settings.getBaudRate(),Settings.getDataBits(),Settings.getStopBits(),Settings.getParity());
           if (serialPort!=null){
               portListener=new PortListener();
               portListener.ListenerInitialize(writer,serialPort,this.packagesInfo,PreviousInfo);
               serialPort.addDataListener(portListener);
           }
       }catch (Exception e){
           e.printStackTrace();
           logger.log(Level.DEBUG,"Something going wrong when MbusInitialize going");
           logger.log(Level.WARN,e);
       }


    }


    public  SerialPort serialInitialze(String Port,int BaudRate,int Databits,int StopBits,int Parity)
    {
        logger.log(Level.DEBUG,"SerialInitialize started");
        SerialPort result =null;
        SerialPort [] serialPorts=  SerialPort.getCommPorts();
        try {
            for (SerialPort s : serialPorts){
                logger.log(Level.DEBUG,"Avaliable ports : "+s.getSystemPortName());
                if (s.getSystemPortName().equals(Port)){

                    s.setBaudRate(BaudRate);
                    s.setParity(Parity);
                    s.setNumStopBits(StopBits);
                    s.setNumDataBits(Databits);

                    logger.log(Level.DEBUG,"___________________________________________________");
                    //logger.log(Level.DEBUG,"Something going wrong when SerialInitialize going");
                    logger.log(Level.DEBUG,"SystemPortName()  : "+s.getSystemPortName());
                    logger.log(Level.DEBUG,"getNumDataBits()  : "+s.getNumDataBits());
                    logger.log(Level.DEBUG,"getBaudRate()  : "+s.getBaudRate());
                    logger.log(Level.DEBUG,"getNumStopBits()  : "+s.getNumStopBits());
                    logger.log(Level.DEBUG,"getParity()  : "+s.getParity());

                    logger.log(Level.DEBUG,"___________________________________________________");
                    result=s;
                    return result;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.DEBUG,"Something going wrong when SerialInitialize going");
            logger.log(Level.WARN,e);
        }

        return result;
    }
    @Override
    public boolean OpenPort(){
     boolean result=false;
     try {

         serialPort =serialInitialze(Settings.getPort(),Settings.getBaudRate(),Settings.getDataBits(),Settings.getStopBits(),Settings.getParity());
         if (serialPort!=null){
             portListener=new PortListener();
             portListener.ListenerInitialize(writer,serialPort,this.packagesInfo,PreviousInfo);
             serialPort.addDataListener(portListener);
             serialPort.openPort();
             if (serialPort.isOpen()){
                 writer.WriteMessage("Port successfully  opened with parameters : \r\n"+Settings.mytoString());
             }else {
                 writer.WriteMessage("When port open something going wrong \r\n");
             }
         }
     }catch (Exception e){
         e.printStackTrace();
         logger.log(Level.DEBUG,"Something going wrong when OpenPort going");
         logger.log(Level.WARN,e);
     }
     return result;
    }
    @Override
    public boolean ClosePort(){
        boolean result=false;
        try {
            serialPort.closePort();
            if (!serialPort.isOpen()){
                writer.WriteMessage("Port successfully closed");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.DEBUG,"Something going wrong when ClosePort going");
            logger.log(Level.WARN,e);
        }
        return result;
    }



    private static class PortListener implements SerialPortDataListener {

        SerialPort serialPort;

        WriterToControllerInterface writer ;
        PackagesInfo packagesInfo;
        InfoOfPackage PreviousInfo;
        void ListenerInitialize( WriterToControllerInterface writer1, SerialPort serialPort,PackagesInfo Info,InfoOfPackage previousInfo){
            writer=writer1;
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
                logger.log(Level.DEBUG,"##########################################################");
                logger.log(Level.DEBUG,PreviousInfo);
                logger.log(Level.DEBUG,"##########################################################");
                packagesInfo.setCurrentInfo(decoder.DecodePackage(newData,PreviousInfo));
                writer.WriteInfoPackage(packagesInfo.getCurrentInfo());
                logger.log(Level.DEBUG,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                logger.log(Level.DEBUG,packagesInfo.getCurrentInfo());
                logger.log(Level.DEBUG,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.DEBUG,"Something going wrong when SerialEvent going");
                logger.log(Level.WARN,e);
            }
            PreviousInfo=packagesInfo.getCurrentInfo();
            //Settings s =new Settings();

        }
    }

}





