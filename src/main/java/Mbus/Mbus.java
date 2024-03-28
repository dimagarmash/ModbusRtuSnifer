package Mbus;



import java.util.Arrays;
import com.fazecast.jSerialComm.*;

public class Mbus  {





 public static final String Com="COM6";
    static InfoOfPackage currentInfo=new InfoOfPackage();
    static InfoOfPackage previousInfo=new InfoOfPackage();
    public static void serialInitialze()
    {
       SerialPort [] serialPorts=  SerialPort.getCommPorts();

        for (SerialPort s : serialPorts){
            System.out.println("Avaliable ports : "+s.getSystemPortName());
            if (s.getSystemPortName().equals(Com)){

                s.setBaudRate(115200);
                s.setParity(0);
                s.setNumStopBits(1);
                s.setNumDataBits(8);

                System.out.println("___________________________________________________");
                System.out.println("SystemPortName()  : "+s.getSystemPortName());
                System.out.println("getNumDataBits()  : "+s.getNumDataBits());
                System.out.println("getBaudRate()  : "+s.getBaudRate());
                System.out.println("getNumStopBits()  : "+s.getNumStopBits());
                System.out.println("getParity()  : "+s.getParity());
                System.out.println("openPort()  : "+s.openPort());
                System.out.println("___________________________________________________");





                s.addDataListener(new SerialPortDataListener() {
                    @Override
                    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
                    @Override
                    public void serialEvent(SerialPortEvent event)
                    {
                        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                            return;
                        byte[] newData = new byte[s.bytesAvailable()];

                        int numRead = s.readBytes(newData, newData.length);

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
                            System.out.println(previousInfo);
                            System.out.println("##########################################################");
                            currentInfo=decoder.DecodePackage(newData,previousInfo);
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println(currentInfo);
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                        } catch (Exception e) {
                           e.printStackTrace();
                        }
                        previousInfo=currentInfo;
                    }
                });
            }
        }
    }

    private static class PortReader {


    }

}





