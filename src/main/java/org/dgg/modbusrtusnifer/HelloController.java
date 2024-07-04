package org.dgg.modbusrtusnifer;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
    void onAction(ActionEvent event) {
        System.out.println("onAction");
    }

}
