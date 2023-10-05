module com.example.employeesreports {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeesreports to javafx.fxml;
    exports com.example.employeesreports;
}