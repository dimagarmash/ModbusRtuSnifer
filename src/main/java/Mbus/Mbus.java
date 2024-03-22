package Mbus;



import java.util.Arrays;
import com.fazecast.jSerialComm.*;

public class Mbus  {





 public static final String Com="COM10";
    public static void serialInitialze()
    {
       SerialPort [] serialPorts=  SerialPort.getCommPorts();

        for (SerialPort s : serialPorts){
            System.out.println("Avaliable ports : "+s.getSystemPortName());
            if (s.getSystemPortName().equals(Com)){
                s.openPort();
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

                    }
                });
            }
        }
    }

    private static class PortReader {


    }

}





