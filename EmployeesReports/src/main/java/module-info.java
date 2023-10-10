module com.example.employeesreports {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.javafx;
    requires kernel;
    requires layout;
    requires org.slf4j;
    requires org.apache.logging.log4j;


    opens com.example.employeesreports to javafx.fxml;
    opens com.example.employeesreports.models;
    exports com.example.employeesreports;
}