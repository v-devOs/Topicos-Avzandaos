package com.example.employeesreports;

import com.example.employeesreports.reports.C01E01_HelloWorld;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static final String DEST1 = "results/chapter01/hello_world.pdf";
    @FXML
    Button btnReport1, btnReport2, btnReport3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnReport1.setOnAction(hanlderButtons);
        btnReport2.setOnAction(hanlderButtons);
        btnReport3.setOnAction(hanlderButtons);
    }

    EventHandler<ActionEvent> hanlderButtons = ( event ) -> {
        if( event.getSource() == btnReport1 ) generateReport1();
        else if( event.getSource() == btnReport2 ){}
        else if( event.getSource() == btnReport3) {}
    };

    private void generateReport1(){

        try{
            File file = new File(DEST1);
            file.getParentFile().mkdirs();
            new C01E01_HelloWorld().createPdf(DEST1);
            showMessage("Hello world report -> " + DEST1);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setContentText(message);
        alert.show();
    }
}