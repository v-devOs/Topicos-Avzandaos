module com.example.todolistapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.todolistapp to javafx.fxml;
    opens com.example.todolistapp.models;
    exports com.example.todolistapp;
}