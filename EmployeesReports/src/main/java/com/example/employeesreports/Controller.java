package com.example.employeesreports;

import com.example.employeesreports.reports.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static final String DEST1 = "results/chapter01/hello_world.pdf";
    public static final String DEST2 = "results/chapter01/rick_astley.pdf";
    public static final String DEST3 = "results/chapter01/quick_brown_fox.pdf";
    public static final String DEST4 = "results/chapter01/united_states.pdf";
    public static final String DEST5 = "results/chapter01/employees.pdf";
    public Button btnEmployeesReport;
    @FXML
    Button btnReport1, btnReport2, btnReport3, btnReport4;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnReport1.setOnAction(hanlderButtons);
        btnReport2.setOnAction(hanlderButtons);
        btnReport3.setOnAction(hanlderButtons);
        btnReport4.setOnAction(hanlderButtons);
        btnEmployeesReport.setOnAction(hanlderButtons);
    }

    EventHandler<ActionEvent> hanlderButtons = ( event ) -> {
        if( event.getSource() == btnReport1 ) generateReport1();
        else if( event.getSource() == btnReport2 ) generateReport2();
        else if( event.getSource() == btnReport3) generateReport3();
        else if( event.getSource() == btnReport4 ) generateReport4();
        else if( event.getSource() == btnEmployeesReport) generateEmployeesReport();
    };

    private void generateReport1(){

        try{
            File file = new File(DEST1);
            file.getParentFile().mkdirs();
            new C01E01_HelloWorld().createPdf(DEST1);
            showMessage("Hello world report -> " + DEST1);
            openFile(DEST1);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    private void generateReport2(){

        try{
            File file = new File(DEST2);
            file.getParentFile().mkdirs();
            new C01E02_RickAstley().createPdf(DEST2);
            showMessage("Hello world report -> " + DEST2);
            openFile(DEST2);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    private void generateReport3(){

        try{
            File file = new File(DEST3);
            file.getParentFile().mkdirs();
            new C01E03_QuickBrownFox().createPdf(DEST3);
            showMessage("Hello world report -> " + DEST3);
            openFile(DEST3);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    private void generateReport4(){

        try{
            File file = new File(DEST4);
            file.getParentFile().mkdirs();
            new C01E04_UnitedStates().createPdf(DEST4);
            showMessage("Hello world report -> " + DEST4);
            openFile(DEST4);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void generateEmployeesReport(){
        try {
            File file = new File(DEST5);
            file.getParentFile().mkdirs();
            new EmployeesReport().createPdf(DEST5);
            openFile(DEST5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openFile(String filename)
    {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }


    private void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setContentText(message);
        alert.show();
    }
}