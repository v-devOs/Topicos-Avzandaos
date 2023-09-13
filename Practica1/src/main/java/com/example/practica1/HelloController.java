package com.example.practica1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.text.DecimalFormat;

public class HelloController {
    private double cntTotal, tsaInteres, prdio;
    @FXML
    private TextField cantidadTotal;
    @FXML
    private TextField tasaInteres;
    @FXML
    private TextField periodo;
    @FXML
    private Label interesFinal;
    @FXML
    private Label tipoInteres;

    @FXML
    protected void calcularInteresSimple() {
        calcular("Simple", false);
    }
    @FXML
    protected void calcularInteresCompuesto(){
        calcular("Compuesto", true);
    }
    @FXML
    protected void borrar(){
        cantidadTotal.setText("");
        periodo.setText("");
        tasaInteres.setText("");
    }

    private void calcular(String message, boolean usarCompuesto){

        if( !validateTextField() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error en los campos de entrada");
            alert.show();
        }
        else{

            setValuesInVariables();
            final DecimalFormat df = new DecimalFormat("0.00");

            tipoInteres.setText("Resultados del interes " + message);

            if( usarCompuesto ){
                double intCompuesto = cntTotal * Math.pow(( 1 + tsaInteres / 100), prdio );
                interesFinal.setText(df.format( intCompuesto- cntTotal));
            }
            else interesFinal.setText(String.valueOf(( cntTotal*tsaInteres*prdio) / 100));
        }
    }

    private boolean validateTextField(){
        return !cantidadTotal.getText().isEmpty() || !periodo.getText().isEmpty() || !tasaInteres.getText().isEmpty();
    }

    private void setValuesInVariables(){
        cntTotal = Integer.parseInt(cantidadTotal.getText());
        tsaInteres = Integer.parseInt(tasaInteres.getText());
        prdio = Integer.parseInt(periodo.getText());
    }
}