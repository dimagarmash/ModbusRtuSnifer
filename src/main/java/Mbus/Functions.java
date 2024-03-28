package Mbus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Functions {
    public static final Function ReadCoilStatus = new Function("01",(byte) 1,"81",(byte) 0x81,"Read Coil Status",8,false,6,true,-1,3);
    public static final Function ReadInputStatus = new Function("02",(byte) 2,"82",(byte) 0x82,"Read Input Status",8,false,6,true,-1,3);
    public static final Function ReadHoldingRegisters = new Function("03",(byte)3,"83",(byte) 0x83,"Read Holding Registers",8,false,7,true,-1,3);
    public static final Function ReadInputRegisters = new Function("04",(byte)4,"84",(byte) 0x84,"Read Input Registers",8,false,7,true,-1,3);

    public static final Function ForceSingleCoil = new Function("05",(byte)5,"85",(byte) 0x85,"Force Single Coil",8,true,8,true,4,4);
    public static final Function PresetSingleRegister = new Function("06",(byte)6,"86",(byte) 0x86,"Preset Single Register",8,true,8,true,4,4);
    public static final Function ForceMultipleCoils = new Function("0f",(byte) 0xF,"8f",(byte) 0x8f,"Force Multiple Coils",9,true,8,false,7,4);
    public static final Function PresetMultipleRegisters = new Function("10",(byte)0x10,"90",(byte) 0x90,"Preset Multiple Registers",9,true,8,false,7,4);

    public static final List<Function> FUNCTION_LIST =new ArrayList<Function>(Arrays.asList(
            ReadCoilStatus,
            ReadInputStatus,
            ReadHoldingRegisters,
            ReadInputRegisters,
            ForceSingleCoil,
            PresetSingleRegister,
            ForceMultipleCoils,
            PresetMultipleRegisters
    ));

    public static final Error NoError =new Error((byte) 0x00,
            "NoError",
            "NoError");
    public static final Error IllegalFunction =new Error((byte) 0x01,
            "IllegalFunction",
            "The function code received in the query is not an allowable action for the server.  This may be because the function code is only applicable to newer devices, and was not implemented in the unit selected.  It could also indicate that the server is in the wrong state to process a request of this type, for example because it is unconfigured and is being asked to return register values. If a Poll Program Complete command was issued, this code indicates that no program function preceded it.");
    public static final Error IllegalDataAddress =new Error((byte) 0x02,
            "IllegalDataAddress",
            "The data address received in the query is not an allowable address for the server. More specifically, the combination of reference number and transfer length is invalid. For a controller with 100 registers, a request with offset 96 and length 4 would succeed, a request with offset 96 and length 5 will generate exception 02.");
    public static final Error IllegalDataValue =new Error((byte) 0x03,
            "IllegalDataValue",
            "A value contained in the query data field is not an allowable value for the server.  This indicates a fault in the structure of remainder of a complex request, such as that the implied length is incorrect. It specifically does NOT mean that a data item submitted for storage in a register has a value outside the expectation of the application program, since the MODBUS protocol is unaware of the significance of any particular value of any particular register.");
    public static final Error SlaveDeviceFailure =new Error((byte) 0x04,
            "SlaveDeviceFailure",
            "An unrecoverable error occurred while the server was attempting to perform the requested action.");
    public static final Error 	Acknowledge =new Error((byte) 0x05,
            "Acknowledge",
            "Specialized use in conjunction with programming commands.\n" +
                    "The server has accepted the request and is processing it, but a long duration of time will be required to do so.  This response is returned to prevent a timeout error from occurring in the client. The client can next issue a Poll Program Complete message to determine if processing is completed.");
    public static final Error SlaveDeviceBusy =new Error((byte) 0x06,
            "SlaveDeviceBusy",
            "Specialized use in conjunction with programming commands.\n" +
                    "The server is engaged in processing a long-duration program command.  The client should retransmit the message later when the server is free..");
    public static final Error NegativeAcknowledge =new Error((byte) 0x07,
            "NegativeAcknowledge",
            "The server cannot perform the program function received in the query. This code is returned for an unsuccessful programming request using function code 13 or 14 decimal. The client should request diagnostic or error information from the server.");
    public static final Error MemoryParityError =new Error((byte) 0x08,
            "MemoryParityError",
            "Specialized use in conjunction with function codes 20 and 21 and reference type 6, to indicate that the extended file area failed to pass a consistency check.\n" +
                    "The server attempted to read extended memory or record file, but detected a parity error in memory. The client can retry the request, but service may be required on the server device.");
    public static final Error GatewayPathUnavailable =new Error((byte) 0x10,
            "GatewayPathUnavailable",
            "Specialized use in conjunction with gateways, indicates that the gateway was unable to allocate an internal communication path from the input port to the output port for processing the request. Usually means the gateway is misconfigured or overloaded.");
    public static final Error GatewayTargetDeviceFailedtoRespond =new Error((byte) 0x11,
            "GatewayTargetDeviceFailedtoRespond",
            "Specialized use in conjunction with gateways, indicates that no response was obtained from the target device. Usually means that the device is not present on the network.");
    public static final List<Error> ERROR_LIST=new ArrayList<Error>(Arrays.asList(
            NoError,
            IllegalFunction,
            IllegalDataAddress,
            IllegalDataValue,
            SlaveDeviceFailure,
            Acknowledge,
            SlaveDeviceBusy,
            NegativeAcknowledge,
            MemoryParityError,
            GatewayPathUnavailable,
            GatewayTargetDeviceFailedtoRespond));


}