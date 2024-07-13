package Mbus;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SerialPortChecker {
    private static final Logger logger= LogManager.getLogger(SerialPortChecker.class);

    public static List<String> GetAvaliablePorts(){
        List<String> result=new ArrayList<>();
        logger.log(Level.DEBUG,"GetAvailablePorts started");
        SerialPort [] serialPorts=  SerialPort.getCommPorts();
        try {
            for (SerialPort s : serialPorts){
                logger.log(Level.DEBUG,"Available ports : "+s.getSystemPortName());
                result.add(s.getSystemPortName());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.DEBUG,"Something going wrong when GetAvailablePorts going");
            logger.log(Level.WARN,e);
        }

        return result;
    }

}
