package Settings;

import Const.Const;

import java.io.*;
import java.util.Properties;


public  class Settings extends Const {
    private static String Port;
    private static int BaudRate;



    private static int Parity;
    private static int StopBits;
    private static int DataBits;
    private static ProgrammMode PMode;

    public static String getPort() {
        return Port;
    }

    public static int getBaudRate() {
        return BaudRate;
    }

    public static int getParity() {
        return Parity;
    }

    public static int getStopBits() {
        return StopBits;
    }

    public static int getDataBits() {
        return DataBits;
    }

    public static ProgrammMode getPMode() {
        return PMode;
    }

    public static void setPort(String port) {

        Port = port;
        WriteSettingsToFile(PathToSettingsFile);

    }

    public static void setBaudRate(int baudRate) {
        BaudRate = baudRate;
        WriteSettingsToFile(PathToSettingsFile);
    }

    public static void setParity(int parity) {
        Parity = parity;
        WriteSettingsToFile(PathToSettingsFile);
    }

    public static void setStopBits(int stopBits) {
        StopBits = stopBits;
        WriteSettingsToFile(PathToSettingsFile);
    }

    public static void setDataBits(int dataBits) {
        DataBits = dataBits;
        WriteSettingsToFile(PathToSettingsFile);
    }

    public static void setPMode(Const.ProgrammMode PMode) {
        Settings.PMode = PMode;
        WriteSettingsToFile(PathToSettingsFile);
    }
    public Settings(String port, int baudRate, int parity, int stopBits, int dataBits, Const.ProgrammMode mode) {
        Port=port;
        BaudRate=baudRate;
        Parity=parity;
        StopBits=stopBits;
        DataBits=dataBits;
        PMode=mode;
    }

    public Settings() {
    }

    public static boolean SetSettings(String port, int baudRate, int parity, int stopBits, int dataBits, Const.ProgrammMode mode){
        boolean result=false;
        Port=port;
        BaudRate=baudRate;
        Parity=parity;
        StopBits=stopBits;
        DataBits=dataBits;
        PMode=mode;
        result=WriteSettingsToFile(Const.PathToSettingsFile);
        return result;
    }

    public static boolean InitializeSettings(){
        boolean result =false;
        result=ReadSettingsFromFile(Const.PathToSettingsFile);
        if (!result)
            result=SetSettings(DefaultPort,DefaultBaudRate,DefaultParity,DefaultStopBits,DefaultDataBits,DefaultPMode);
        return result;
    }


    private static boolean ReadSettingsFromFile(String path ) {
        boolean result=false;

        try {

            String path1=new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
            System.out.println("path1:"+path1);
            path1=path1+PathToSettingsFile;
            System.out.println("path1:"+path1);

            FileInputStream fis = new FileInputStream(path1);
            System.out.println("fis:"+fis);
            Properties properties= new Properties();
            System.out.println(PathToSettingsFile);
            properties.load(fis);

           Port= properties.getProperty("Port") ;
           BaudRate= Integer.parseInt(properties.getProperty("BaudRate"));
           Parity= Integer.parseInt(properties.getProperty("Parity"));
           StopBits = Integer.parseInt(properties.getProperty("StopBits"));
           DataBits = Integer.parseInt(properties.getProperty("DataBits"));
           PMode= ProgrammMode.valueOf(properties.getProperty("PMode"));


            fis.close();
            result=true;

        }catch (NullPointerException e){
            System.out.println("not find file");
            e.printStackTrace();
            result=false;


        }catch (FileNotFoundException fex)
        {

            try {
                CreateSettingsFile(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        catch (IOException io) {
            System.out.println("IO");
            io.printStackTrace();
            result=false;

        }


        return result;
    }
    private static boolean CreateSettingsFile(String path)throws Exception
    {
          String path1 =new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
          path1+=path;
          File file =new File(path1);
        boolean result=false;
        System.out.println("CreateSettingsFile");
        System.out.println(file.getAbsolutePath());
        if(file.createNewFile()){
            result=true;
            System.out.println(" File Created in Project root directory");
        }else{
            result=false;
            System.out.println(" already exists in the project root directory");
        }

        return result;
    }
    private static boolean WriteSettingsToFile(String path){
        boolean result=false;
        try {

            String path1=new File(Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
            System.out.println("path1:"+path1);
            path1=path1+PathToSettingsFile;
            System.out.println("path1:"+path1);
            FileInputStream fis = new FileInputStream(path1);
            System.out.println("fis:"+fis);
            Properties properties= new Properties();
            System.out.println(PathToSettingsFile);
            properties.load(fis);

            fis.close();

            FileOutputStream fos=new FileOutputStream(path1);




            properties.setProperty("Port",Port) ;
            properties.setProperty("BaudRate", String.valueOf(BaudRate)) ;
            properties.setProperty("Parity", String.valueOf(Parity)) ;
            properties.setProperty("StopBits", String.valueOf(StopBits)) ;
            properties.setProperty("DataBits", String.valueOf(DataBits)) ;
            properties.setProperty("PMode", String.valueOf(PMode)) ;

            properties.store(fos,null);
            fos.close();
            result=true;

        }  catch (IOException io) {
            io.printStackTrace();
            result=false;

        }catch (NullPointerException n){

            n.printStackTrace();
            result=false;
        }catch (Exception e){
            e.printStackTrace();
            result=false;
        }

        return result;
    }
}
