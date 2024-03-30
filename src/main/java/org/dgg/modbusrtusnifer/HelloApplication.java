package org.dgg.modbusrtusnifer;

import Mbus.InfoOfPackage;
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
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        controller=fxmlLoader.getController();
        Initialize();
        mbus.start();

    }
    public void Initialize(){
        packagesInfo.Initialize(controller);
        mbus.MbusInitialize(packagesInfo,new InfoOfPackage());
    }

    HelloController controller;
    static Mbus mbus=new Mbus();
   static PackagesInfo packagesInfo=new PackagesInfo();
    public static void main(String[] args) {


        Settings.InitializeSettings();

        launch();

    }
}