package Mbus;

import Const.Const;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Decoder extends Functions implements interfaceDecoder  {

    private static final Logger logger= LogManager.getLogger(Decoder.class);
    @Override
    public InfoOfPackage DecodePackage(byte[] CurrentBytes, InfoOfPackage PreviousInfoOfPackage)throws Exception
    {
        logger.log(Level.DEBUG,"DecodePackage Started");

        String PackageType=String.valueOf(Const.PackageType.NOT_RECOGNIZED);
        int Address=-1;
        Function function=new Function();
        int StartRegisterAddress=-1;
        int EndRegisterAddress=-1;
        List<MbusData> data=new ArrayList<MbusData>();
        int CRC=-1;
        boolean CRCIsOk=false;
        Error error=null;
        int BytesCount=-1;
        int CalculatedCRC=-2;

        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage){

                CRC=GetCRC(CurrentBytes);
                CalculatedCRC=CalculateCRC(CurrentBytes);
                CRCIsOk=CheckCRC(CRC,CalculatedCRC);

                if (CRCIsOk) {
                    error=CheckErr(CurrentBytes);
                    Address=GetAddress(CurrentBytes);
                    function=GetFunction(CurrentBytes);
                    PackageType= GetPackageType(CurrentBytes,function,PreviousInfoOfPackage.function,error,PreviousInfoOfPackage.PackageType,CRCIsOk);
                    if (error.RestrictedDescription==NoError.RestrictedDescription) {
                        StartRegisterAddress = GetStartRegisterAdr(CurrentBytes);
                        EndRegisterAddress = GetEndRegisterAdr(CurrentBytes, function, StartRegisterAddress);
                        BytesCount=GetCountOfDataBytes(CurrentBytes,function,PackageType);
                        data=GetRegistersData(CurrentBytes,function,PackageType,BytesCount);
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when DecoderPackage running");
        }

        logger.log(Level.INFO,
                "PackageType : "+PackageType+"\r\n"+
                "Address : "+Address+"\r\n"+
                "function : "+function.toString()+"\r\n"+
                "StartRegisterAddress : "+StartRegisterAddress+"\r\n"+
                "EndRegisterAddress : "+EndRegisterAddress+"\r\n"+
                "CRC : "+CRC+"\r\n"+
                "CRCIsOk : "+CRCIsOk+"\r\n"+
                "error : "+error.toString()+"\r\n"+
                "BytesCount : "+BytesCount+"\r\n"+
                "CalculatedCRC : "+CalculatedCRC+"\r\n");

        return new InfoOfPackage(CurrentBytes,PackageType,Address,function,StartRegisterAddress,EndRegisterAddress,data, CRC,CRCIsOk,BytesCount, error, CalculatedCRC);
    }
    @Override
    public String GetPackageType(byte[] CurrentBytes, Function currentFunc,Function previousFunc,Error error,String previousType,boolean CRCIsOk)throws Exception
    {
        String result=null;
        logger.log(Level.DEBUG,"GetPackageType Started");
        try {


            if (CurrentBytes.length>=Const.minLengthOfPackage)
            {
                if (CRCIsOk) {
                    if (error.RestrictedDescription == NoError.RestrictedDescription) {
                        if (currentFunc != null) {
                            if (previousFunc!=null){
                                if (currentFunc==previousFunc){
                                    if (currentFunc.number < 5) {
                                        result=GetPackageTypeFor1To4Func(CurrentBytes,currentFunc,previousType);
                                    } else {
                                        result=GetPackageTypeFor5To16Func(CurrentBytes,currentFunc,previousType);
                                    }
                                }
                                else {
                                    result = String.valueOf(Const.PackageType.REQUEST);
                                }

                            }else {
                                if (currentFunc.number < 5) {
                                    result=GetPackageTypeFor1To4Func(CurrentBytes,currentFunc,previousType);
                                } else {
                                    result=GetPackageTypeFor5To16Func(CurrentBytes,currentFunc,previousType);
                                }
                            }


                        } else {
                            result = String.valueOf(Const.PackageType.NOT_RECOGNIZED);
                        }

                    } else {
                        result = String.valueOf(Const.PackageType.ERROR);
                    }
                }
                else {
                    result= String.valueOf(Const.PackageType.NOT_RECOGNIZED);
                }


            }
            else {
                result= String.valueOf(Const.PackageType.NOT_RECOGNIZED);
            }
        }
            catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetPackageType running");
        }
        logger.log(Level.DEBUG,"GetPackageType return : "+result);
        return result;
    }

    private  String GetPackageTypeFor1To4Func(byte[] CurrentBytes,Function currentFunc,String previousType){
        String result= String.valueOf(Const.PackageType.NOT_RECOGNIZED);
        logger.log(Level.DEBUG,"GetPackageTypeFor1To4Func Started");
        try {
            MbusData data = new MbusData((byte) 0, CurrentBytes[Const.CountDataBytesInResponsePositionInPackage]);
            int expbytescount = data.GetUnsignedValue();
            int actbutescount = CurrentBytes.length - (currentFunc.StartDataPosInResponse + 2) ;
            if (expbytescount == actbutescount) {
                if (expbytescount == 3 && (currentFunc.number == 1 || currentFunc.number == 2)) {
                    if (previousType!=null) {

                        if (previousType.equals( String.valueOf(Const.PackageType.REQUEST)) || previousType.equals( String.valueOf(Const.PackageType.UNKNOWN))) {
                            result = String.valueOf(Const.PackageType.RESPONSE);
                        } else if (previousType.equals( String.valueOf(Const.PackageType.ERROR))) {
                            result = String.valueOf(Const.PackageType.REQUEST);
                        } else if (previousType.equals(String.valueOf(Const.PackageType.RESPONSE))) {
                            result = String.valueOf(Const.PackageType.REQUEST);
                        }else if (previousType.equals(String.valueOf(Const.PackageType.NOT_RECOGNIZED))) {
                            result = String.valueOf(Const.PackageType.UNKNOWN);
                        } else {
                            result = String.valueOf(Const.PackageType.NOT_RECOGNIZED);
                        }

                    } else {
                        result = String.valueOf(Const.PackageType.UNKNOWN);
                    }
                } else {
                    result = String.valueOf(Const.PackageType.RESPONSE);
                }

            } else {
                result = String.valueOf(Const.PackageType.REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetPackageTypeFor1To4Func running");
        }
        logger.log(Level.DEBUG,"GetPackageTypeFor1To4Func return : "+result);
        return result;
    }
    private  String GetPackageTypeFor5To16Func(byte[] CurrentBytes,Function currentFunc,String previousType){
        String result=String.valueOf(Const.PackageType.NOT_RECOGNIZED);
        logger.log(Level.DEBUG,"GetPackageTypeFor5To16Func Started");
        try {
            if (currentFunc==ForceMultipleCoils||currentFunc==PresetMultipleRegisters){
                if (CurrentBytes.length==currentFunc.MinResponseLength){
                    result=String.valueOf(Const.PackageType.RESPONSE);
                } else if (CurrentBytes.length>=currentFunc.MinRequestByteLength) {
                    result=String.valueOf(Const.PackageType.REQUEST);
                }
            }else {

                if (previousType!=null) {
                    if (previousType.equals(String.valueOf(Const.PackageType.REQUEST)) || previousType.equals(String.valueOf(Const.PackageType.UNKNOWN))) {
                        result = String.valueOf(Const.PackageType.RESPONSE);
                    } else if (previousType.equals(String.valueOf(Const.PackageType.ERROR))) {
                        result = String.valueOf(Const.PackageType.REQUEST);
                    } else if (previousType.equals(String.valueOf(Const.PackageType.RESPONSE))) {
                        result = String.valueOf(Const.PackageType.REQUEST);
                    } else if (previousType.equals(String.valueOf(Const.PackageType.NOT_RECOGNIZED))) {
                        result = String.valueOf(Const.PackageType.UNKNOWN);
                    } else {
                        result = String.valueOf(Const.PackageType.NOT_RECOGNIZED);
                    }
                }else {
                    result = String.valueOf(Const.PackageType.UNKNOWN);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetPackageTypeFor5To16Func running");
        }
        logger.log(Level.DEBUG,"GetPackageTypeFor5To16Func return : "+result);



        return result;
    }
    @Override
    public int GetAddress(byte[] CurrentBytes)throws Exception {
        int result=-1;
        logger.log(Level.DEBUG,"GetAddress Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {
                MbusData data = new MbusData((byte) 0,CurrentBytes[Const.AddressPositionInPackage] );
                result=  data.GetUnsignedValue();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetAddress running");
        }
        logger.log(Level.DEBUG,"GetAddress return : "+result);

        return result;
    }


    @Override
    public Function GetFunction(byte[] CurrentBytes) throws Exception{
        Function result=null;
        logger.log(Level.DEBUG,"GetFunction Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {

                for (Function f :FUNCTION_LIST){
                    if (f.number==CurrentBytes[Const.FunctionNumberPositionInPackage]||f.numberWhenErr==CurrentBytes[Const.FunctionNumberPositionInPackage]){
                        result=f;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetFunction running");
        }
        logger.log(Level.DEBUG,"GetFunction return : "+result);

        return result;
    }

    @Override
    public int GetStartRegisterAdr(byte[] CurrentBytes) throws Exception{
        int result=-1;
        logger.log(Level.DEBUG,"GetStartRegisterAdr Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {
                MbusData data = new MbusData(CurrentBytes[Const.FirstAddressRegisterPositionInPackage],CurrentBytes[Const.FirstAddressRegisterPositionInPackage+1] );
                result=  data.GetUnsignedValue();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when GetStartRegisterAdr running");
        }
        logger.log(Level.DEBUG,"GetStartRegisterAdr return : "+result);

        return result;
    }

    @Override
    public int GetEndRegisterAdr(byte[] CurrentBytes, Function function,int StartRegisterAddress) throws Exception{
        int result=-1;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {
                if (function==ForceSingleCoil||function==PresetSingleRegister){
                    result=StartRegisterAddress;
                }else {
                    MbusData data = new MbusData(CurrentBytes[Const.CountRegistersPositionInPackage],CurrentBytes[Const.CountRegistersPositionInPackage+1] );
                    result=  data.GetUnsignedValue();
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }

    @Override
    public List<MbusData> GetRegistersData(byte[] CurrentBytes,Function function,String TypePackage,int DataBytesCount ) throws Exception{
        List<MbusData> result=new ArrayList<>();
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes!=null&&function!=null&&TypePackage!=null&& DataBytesCount>0) {
                if (TypePackage.equals(String.valueOf(Const.PackageType.REQUEST))) {
                    if (function.StartDataPosInRequest != -1) {
                        if (function.StartDataPosInRequest + DataBytesCount < CurrentBytes.length) {
                            for (int i = function.StartDataPosInRequest; i < function.StartDataPosInRequest + DataBytesCount; i += 2) {
                                result.add(new MbusData(CurrentBytes[i], CurrentBytes[i + 1]));
                            }
                        }
                    }
                } else if (TypePackage.equals(String.valueOf(Const.PackageType.RESPONSE))) {
                    if (function == ReadCoilStatus || function == ReadInputStatus) {
                        if (function.StartDataPosInResponse + DataBytesCount < CurrentBytes.length) {
                            for (int i = function.StartDataPosInResponse; i < function.StartDataPosInResponse + DataBytesCount; i++) {
                                result.add(new MbusData((byte) 0, CurrentBytes[i]));
                            }
                        }
                    } else {
                        if (function.StartDataPosInResponse + DataBytesCount < CurrentBytes.length) {
                            for (int i = function.StartDataPosInResponse; i < function.StartDataPosInResponse + DataBytesCount; i += 2) {
                                result.add(new MbusData(CurrentBytes[i], CurrentBytes[i + 1]));
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }

    @Override
    public int GetCountOfDataBytes(byte[] CurrentBytes,Function function,String TypePackage) throws Exception{
        int result=-1;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (TypePackage.equals(String.valueOf(Const.PackageType.REQUEST))){
                if (function.StartDataPosInRequest!=-1){
                    if (function.isFixedRequestLength){
                        result=2;
                    }
                    else {
                        MbusData mbusData=new MbusData((byte) 0,(byte) CurrentBytes[function.StartDataPosInRequest-1]);
                        result= mbusData.GetUnsignedValue();

                    }
                }
            } else if (TypePackage.equals(String.valueOf(Const.PackageType.RESPONSE))) {

                if (function.isFixedResponseLength){
                    result=2;
                }
                else {
                    MbusData mbusData=new MbusData((byte) 0,(byte) CurrentBytes[function.StartDataPosInResponse-1]);
                    result= mbusData.GetUnsignedValue();

                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }

    @Override
    public Error CheckErr(byte[] CurrentBytes) throws Exception{
        Error result=new Error();
        boolean flag=false;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {

                for (Function f :FUNCTION_LIST){
                    if (f.numberWhenErr==CurrentBytes[Const.FunctionNumberPositionInPackage]){

                        for (Error e:ERROR_LIST){
                            if (e.Number==CurrentBytes[Const.ErrorNumberPositionInPackage]){
                                result=e;
                                return result;
                            }
                        }

                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }

    @Override
    public int GetCRC(byte[] CurrentBytes)throws Exception {
        int result=-1;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {

                MbusData data = new MbusData(CurrentBytes[CurrentBytes.length-2],CurrentBytes[CurrentBytes.length-1] );
                result=  data.GetUnsignedValue();


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }

    @Override
    public int CalculateCRC(byte[] CurrentBytes) throws Exception {
        int crc16 =-1;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {

                crc16 = CRC.INIT_CRC();
                for (int d = 0; d < CurrentBytes.length-2 ; d++)
                {
                    crc16 = CRC.UpdateCRC_Modbus(CurrentBytes[d], crc16);
                }
                int a=(crc16&0xff);
                int b=(crc16&0xff00)>>8;
                MbusData data=new MbusData((byte) a,(byte) b);
                crc16=data.GetUnsignedValue();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+crc16);

        return crc16;
    }

    @Override
    public boolean CheckCRC(byte[] CurrentBytes) throws Exception{
        boolean result=false;
        int crc=GetCRC(CurrentBytes);
        int crc16;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CurrentBytes.length>=Const.minLengthOfPackage) {

                crc16 =CalculateCRC(CurrentBytes);

                if (crc16==crc)
                {
                    result=true;
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);

        return result;
    }
    @Override
    public boolean CheckCRC(int CRC,int CalculatedCRC) throws Exception{
        boolean result=false;
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" Started");
        try {
            if (CRC==CalculatedCRC)
            {
                result=true;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.log(Level.WARN,e);
            logger.log(Level.WARN,"Something going wrong when "+Thread.currentThread().getStackTrace()[1].getMethodName()+" running");
        }
        logger.log(Level.DEBUG,Thread.currentThread().getStackTrace()[1].getMethodName()+" return : "+result);





        return result;
    }


}
