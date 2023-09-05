module com.example.bmiaplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bmiaplication to javafx.fxml;
    exports com.example.bmiaplication;
}