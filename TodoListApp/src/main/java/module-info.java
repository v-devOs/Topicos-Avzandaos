module com.example.todolistapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires kernel;
    requires layout;

    requires org.slf4j;
    requires org.apache.logging.log4j;

    requires java.desktop;
    requires io;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.todolistapp to javafx.fxml;
    opens com.example.todolistapp.models;
    exports com.example.todolistapp;
}