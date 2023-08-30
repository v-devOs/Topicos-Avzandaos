module com.example.helloworldwithgradle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helloworldwithgradle to javafx.fxml;
    exports com.example.helloworldwithgradle;
}