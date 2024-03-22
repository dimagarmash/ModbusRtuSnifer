package Mbus;

class Functions {
    private static final Function ReadCoilStatus = new Function("01",(byte) 1,"Read Coil Status",8,false,6);
    private static final Function ReadInputStatus = new Function("02",(byte) 2,"Read Input Status",8,false,6);
    private static final Function ReadHoldingRegisters = new Function("03",(byte)3,"Read Holding Registers",8,false,7);
    private static final Function ReadInputRegisters = new Function("04",(byte)4,"Read Input Registers",8,false,7);

    private static final Function ForceSingleCoil = new Function("05",(byte)5,"Force Single Coil",8,true,8);
    private static final Function PresetSingleRegister = new Function("06",(byte)6,"Preset Single Register",8,true,8);
    private static final Function ForceMultipleCoils = new Function("0f",(byte) 0xF,"Force Multiple Coils",8,false,8);
    private static final Function PresetMultipleRegisters = new Function("10",(byte)0x10,"Preset Multiple Registers",8,false,8);
}