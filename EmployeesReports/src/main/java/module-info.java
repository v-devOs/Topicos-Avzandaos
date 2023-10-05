module com.example.employeesreports {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.employeesreports to javafx.fxml;
    opens com.example.employeesreports.models;
    exports com.example.employeesreports;
}