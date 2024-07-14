package org.dgg.modbusrtusnifer;


import Const.Const;
import Mbus.*;
import Settings.Settings;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelloController {

    private static final Logger logger= LogManager.getLogger(HelloController.class);
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

    ObservableList ports;
    ObservableList baudRates;
    ObservableList dataBits;
    ObservableList parity;
    ObservableList pmods;
    ObservableList stopBits;

    нужно добавить кнопку открытия порта
    нужно добавить  метод записи в textarray
            так же должен быть реализован останов прокрутки
            и очистка строк болькокогото колличества
    метод вынести в интерфейс  и имплементировать его в контроллере
    @FXML
    void BaudRateSelectionChange(ActionEvent event) {
        Settings.setBaudRate((int) BaudRateComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void DataBitsSelectionChange(ActionEvent event) {
        Settings.setDataBits((int) DataBitsComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void ParytiSelectionChange(ActionEvent event) {
        System.out.println("ParytiSelectionChange");
    }

    @FXML
    void PortClicked(MouseEvent event) {
        System.out.println(" PortClicked");
        String selected= (String) PortComboBox.getSelectionModel().getSelectedItem();
        ports.clear();
        FillPortCombo();
        SetPortComboSelectedItemBySettings();
    }


    @FXML
    void PortSelectionChange(ActionEvent event) {
        Settings.setPort((String) PortComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void ProgrammModeSelectionChange(ActionEvent event) {
        Settings.setPMode(Const.ProgrammMode.SNIFF);
    }

    @FXML
    void StopBitsSelectionChage(ActionEvent event) {
        Settings.setStopBits((int) StopBitsComboBox.getSelectionModel().getSelectedItem());
    }
    void Initialize(){
        logger.log(Level.DEBUG,"Controller initialize");
         ports= PortComboBox.getItems();
         baudRates=BaudRateComboBox.getItems();
         dataBits=DataBitsComboBox.getItems();
         parity=ParityComboBox.getItems();
         pmods=ProgrammModeComboBox.getItems();
         stopBits=StopBitsComboBox.getItems();

        FillPortCombo();
        FillBaudRateCombo();
        FillDataBitsCombo();
        FillParityCombo();
        FillStopBitsCombo();
        pmods.add(Const.ProgrammMode.SNIFF);

        SetComboBoxSelectedItemsBySettings();
    }

    private void FillStopBitsCombo() {
        logger.log(Level.DEBUG,"FillStopBitsCombo started");
        for (var v: Const.StopBits.values()){
            stopBits.add(v.getNumVal());
        }
        logger.log(Level.DEBUG,"stopBits ObservableList : "+stopBits.toString());
    }

    private void FillParityCombo() {
        logger.log(Level.DEBUG,"FillParityCombo started");
        for (var v: Const.Paritу.values()){
            parity.add(v.getNumVal());
        }
        logger.log(Level.DEBUG,"parity ObservableList : "+parity.toString());
    }

    private void FillDataBitsCombo() {
        logger.log(Level.DEBUG,"FillDataBitsCombo started");
        for (var v: Const.DataBits.values()){
            dataBits.add(v.getNumVal());
        }
        logger.log(Level.DEBUG,"dataBits ObservableList : "+dataBits.toString());
    }

    private void FillBaudRateCombo() {
       logger.log(Level.DEBUG,"FillBaudRateCombo started");
        for (var v: Const.BaudRates.values()){
            baudRates.add(v.getNumVal());
        }
        logger.log(Level.DEBUG,"baudRates ObservableList : "+baudRates.toString());
    }

    private void FillPortCombo() {
        logger.log(Level.DEBUG,"FillPortCombo started");
        for (var s: SerialPortChecker.GetAvaliablePorts()){
            ports.add(s);
        }
        logger.log(Level.DEBUG,"ports ObservableList : "+ports.toString());
    }

    private void SetComboBoxSelectedItemsBySettings() {
        SetPortComboSelectedItemBySettings();
        SetBaudComboBySettings();
        SetDataBitsComboBySettings();
        SetParityComboBySettings();
        SetStopBitsComboBySettings();
        SetPModeComboSelectedItemBySettings();

    }

    private void SetStopBitsComboBySettings() {
        logger.log(Level.DEBUG,"SetStopBitsComboBySettings() started");


        if(stopBits.contains(Settings.getStopBits())){

            StopBitsComboBox.getSelectionModel().select(stopBits.indexOf(Settings.getStopBits()));
        }else {
            Settings.setStopBits(Const.DefaultStopBits);
            StopBitsComboBox.getSelectionModel().select(Const.DefaultStopBits);
        }
        logger.log(Level.DEBUG,"StopBitsComboBox.getSelectionModel().getSelectedItem() : "+StopBitsComboBox.getSelectionModel().getSelectedItem());
    }

    private void SetParityComboBySettings() {
        logger.log(Level.DEBUG,"SetParityComboBySettings() started");
        if(parity.contains(Settings.getParity())){
            ParityComboBox.getSelectionModel().select(parity.indexOf(Settings.getParity()));
        }else {
            Settings.setParity(Const.DefaultParity);
            ParityComboBox.getSelectionModel().select(Const.DefaultParity);
        }
        logger.log(Level.DEBUG,"ParityComboBox.getSelectionModel().getSelectedItem() : "+ParityComboBox.getSelectionModel().getSelectedItem());
    }

    private void SetDataBitsComboBySettings() {
        logger.log(Level.DEBUG,"SetDataBitsComboBySettings() started");
        if(dataBits.contains(Settings.getDataBits())){
            DataBitsComboBox.getSelectionModel().select(dataBits.indexOf(Settings.getDataBits()));
        }else {
            Settings.setDataBits(Const.DefaultDataBits);
            DataBitsComboBox.getSelectionModel().select(Const.DefaultDataBits);
        }
        logger.log(Level.DEBUG,"DataBitsComboBox.getSelectionModel().getSelectedItem() : "+DataBitsComboBox.getSelectionModel().getSelectedItem());
    }

    private void SetBaudComboBySettings() {
        logger.log(Level.DEBUG,"SetBaudComboBySettings() started");
        if(baudRates.contains(Settings.getBaudRate())){
            BaudRateComboBox.getSelectionModel().select(baudRates.indexOf(Settings.getBaudRate()));
        }else {
            Settings.setBaudRate(Const.DefaultBaudRate);
            BaudRateComboBox.getSelectionModel().select(Const.DefaultBaudRate);
        }
        logger.log(Level.DEBUG,"BaudRateComboBox.getSelectionModel().getSelectedItem() : "+BaudRateComboBox.getSelectionModel().getSelectedItem());
    }

    private void SetPortComboSelectedItemBySettings() {
        logger.log(Level.DEBUG,"SetPortComboSelectedItemBySettings() started");
        if(ports.contains(Settings.getPort())){
            PortComboBox.getSelectionModel().select(ports.indexOf(Settings.getPort()));
        }else {
            Settings.setPort("");
        }
        logger.log(Level.DEBUG,"PortComboBox.getSelectionModel().getSelectedItem() : "+PortComboBox.getSelectionModel().getSelectedItem());
    }
    private void SetPModeComboSelectedItemBySettings() {
        logger.log(Level.DEBUG,"SetPModeComboSelectedItemBySettings() started");
        logger.log(Level.DEBUG,"pmods.contains(Settings.getPMode()) : "+pmods.contains(Settings.getPMode()));
        if(pmods.contains(Settings.getPMode())){

            ProgrammModeComboBox.getSelectionModel().select(pmods.indexOf(Settings.getPMode()));
        }else {

        }
        logger.log(Level.DEBUG,"ProgrammModeComboBox.getSelectionModel().getSelectedItem() : "+ProgrammModeComboBox.getSelectionModel().getSelectedItem());
    }


    void CheckCombo(){
       
    }

}
