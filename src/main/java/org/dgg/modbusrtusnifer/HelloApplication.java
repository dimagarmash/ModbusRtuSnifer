package org.dgg.modbusrtusnifer;

import Mbus.InfoOfPackage;
import Mbus.WriterToControllerInterface;
import Mbus.WriterToController;
import Mbus.Mbus;
import Mbus.PackagesInfo;
import Settings.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("MODBUS-RTU SNIFFER");
        stage.setScene(scene);
        stage.show();
        controller=fxmlLoader.getController();
        Initialize();
        mbus.start();


    }
    public void Initialize(){
        controller.Initialize();
        WriterToControllerInterface writer =new WriterToController();
        writer.Initialize(controller);
        mbus.MbusInitialize(writer);
    }

    HelloController controller;
    static Mbus mbus=new Mbus();
   static PackagesInfo packagesInfo=new PackagesInfo();
    public static void main(String[] args) {


        Settings.InitializeSettings();

        launch();

    }
}