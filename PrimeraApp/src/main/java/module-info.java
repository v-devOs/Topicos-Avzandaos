module com.example.primeraapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.primeraapp to javafx.fxml;
    exports com.example.primeraapp;
}