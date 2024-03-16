module org.dgg.modbusrtusnifer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jlibmodbus;
    requires jssc;

    opens org.dgg.modbusrtusnifer to javafx.fxml;
    exports org.dgg.modbusrtusnifer;
}