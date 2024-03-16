package Mbus;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.msg.request.ReadHoldingRegistersRequest;
import com.intelligt.modbus.jlibmodbus.msg.response.ReadHoldingRegistersResponse;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.*;
import com.intelligt.modbus.jlibmodbus.utils.DataUtils;
import com.intelligt.modbus.jlibmodbus.utils.FrameEvent;
import com.intelligt.modbus.jlibmodbus.utils.FrameEventListener;
import jssc.SerialPortList;


public class Mbus {


    final static private int slaveId = 1;

    public static void mbusimpl() {
        try {
            System.out.println("MbusImplStart");
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
            SerialParameters serialParameters = new SerialParameters();
            serialParameters.setDevice("COM10");
            // these parameters are set by default
            serialParameters.setBaudRate(SerialPort.BaudRate.getBaudRate(115200));
            serialParameters.setDataBits(8);
            serialParameters.setParity(SerialPort.Parity.getParity(0));
            serialParameters.setStopBits(0);

            SerialUtils.trySelectConnector();
            /*
             Use a virtual serial port SerialPortLoopback
             */
            SerialUtils.setSerialPortFactory(new SerialPortFactoryLoopback(false));
            ModbusSlave slave = ModbusSlaveFactory.createModbusSlaveRTU(serialParameters);

            // SerialUtils.setSerialPortFactory(new SerialPortFactoryLoopback(true));
           // ModbusMaster master = ModbusMasterFactory.createModbusMasterRTU(serialParameters);

            //master.setResponseTimeout(1000);
            slave.setServerAddress(slaveId);
            slave.setBroadcastEnabled(true);
            slave.setReadTimeout(10000);

            FrameEventListener listener = new FrameEventListener() {
                @Override
                public void frameSentEvent(FrameEvent event) {
                    System.out.println("frame sent " + DataUtils.toAscii(event.getBytes()));
                }

                @Override
                public void frameReceivedEvent(FrameEvent event) {
                    System.out.println("frame recv " + DataUtils.toAscii(event.getBytes()));
                }
            };

            slave.addListener(listener);
           // master.addListener(listener);

            ModbusHoldingRegisters holdingRegisters = new ModbusHoldingRegisters(1000);

            for (int i = 0; i < holdingRegisters.getQuantity(); i++) {
                //fill
                holdingRegisters.set(i, i + 1);
            }

            //place the number PI at offset 0
            holdingRegisters.setFloat64At(0, Math.PI);

            slave.getDataHolder().setHoldingRegisters(holdingRegisters);

            slave.listen();



            //master.disconnect();
            Thread.sleep(10000);
            slave.shutdown();
            System.out.println("slave shutdown");
        } catch (ModbusProtocolException e) {
            e.printStackTrace();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        }
         catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



