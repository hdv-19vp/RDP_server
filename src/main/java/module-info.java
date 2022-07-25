module group02.rdpserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires system.hook;


    opens group02.rdpserver to javafx.fxml;
    exports group02.rdpserver;
    exports group02.rdpserver.controller;
    opens group02.rdpserver.controller to javafx.fxml;
}