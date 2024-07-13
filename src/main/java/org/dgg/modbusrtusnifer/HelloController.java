package org.dgg.modbusrtusnifer;


import Const.Const;
import Mbus.*;
import Settings.Settings;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class HelloController {


    @FXML
    private JFXComboBox<?> BaudRateComboBox;

    @FXML
    private JFXComboBox<?> DataBitsComboBox;

    @FXML
    private JFXComboBox<?> ParityComboBox;

    @FXML
    private JFXComboBox<?> PortComboBox;

    @FXML
    private JFXComboBox<?> ProgrammModeComboBox;

    @FXML
    private JFXComboBox<?> StopBitsComboBox;

    @FXML
    private JFXTextArea TextArray;

    @FXML
    void BaudRateSelectionChange(ActionEvent event) {

    }

    @FXML
    void DataBitsSelectionChange(ActionEvent event) {

    }

    @FXML
    void ParytiSelectionChange(ActionEvent event) {

    }

    @FXML
    void PortSelectionChange(ActionEvent event) {

    }

    @FXML
    void ProgrammModeSelectionChange(ActionEvent event) {

    }

    @FXML
    void StopBitsSelectionChage(ActionEvent event) {

    }
    void Initialize(){

        ObservableList ports= PortComboBox.getItems();
        ObservableList baudRates=BaudRateComboBox.getItems();
        ObservableList dataBits=DataBitsComboBox.getItems();
        ObservableList parity=ParityComboBox.getItems();
        ObservableList pmods=ProgrammModeComboBox.getItems();
        ObservableList stopBits=StopBitsComboBox.getItems();

        for (var s: SerialPortChecker.GetAvaliablePorts()){
            ports.add(s);
        }
        for (var v: Const.BaudRates.values()){
            baudRates.add(v.getNumVal());
        }
        for (var v: Const.DataBits.values()){
            dataBits.add(v.getNumVal());
        }
        for (var v: Const.Paritу.values()){
            parity.add(v.getNumVal());
        }
        for (var v: Const.StopBits.values()){
            stopBits.add(v.getNumVal());
        }
        pmods.add(Const.ProgrammMode.SNIFF);

        BaudRateComboBox.getSelectionModel().select(Settings.getBaudRate());
        DataBitsComboBox.getSelectionModel().select(Settings.getDataBits());
        ParityComboBox.getSelectionModel().select(Settings.getParity());
        StopBitsComboBox.getSelectionModel().select(Settings.getStopBits());
        ProgrammModeComboBox.getSelectionModel().select(0);

        PortComboBox.getSelectionModel().select("Settings.getPort()");

    }
    void CheckCombo(){
       
    }

}
