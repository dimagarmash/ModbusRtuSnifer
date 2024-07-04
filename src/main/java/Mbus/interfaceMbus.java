package Mbus;

import Settings.Settings;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.Arrays;

public interface interfaceMbus {

    public void run() ;

    public void stopandcloseport();

    public void MbusInitialize(WriterToControllerInterface writer);


    public  SerialPort serialInitialze(String Port, int BaudRate, int Databits, int StopBits, int Parity);
    public boolean OpenPort();
    public boolean ClosePort();




}
