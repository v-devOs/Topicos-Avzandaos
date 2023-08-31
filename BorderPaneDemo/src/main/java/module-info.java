module com.example.borderpanedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.borderpanedemo to javafx.fxml;
    exports com.example.borderpanedemo;
}