package com.example.bmiaplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BMIAplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(initGUI(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    private StackPane initGUI(){

        StackPane root = new StackPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-fill: purple");

        Label title = new Label("BIM Calculator");
        title.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-text-fill: #dc0b82; -fx-font-weight: bold;" );
        gridPane.add(title, 0, 0, 2, 1);

        Label lblWeight = new Label("Weight");
        TextField txtWeight = new TextField();
        Label lblHeight = new Label("Height");
        TextField txtHeight = new TextField();

        String styleLabels = "-fx-font-family: Verdana; -fx-font-size: 12px; -fx-text-fill: #dc0b82;";
        lblHeight.setStyle(styleLabels);
        lblWeight.setStyle(styleLabels);

        Text lblResults = new Text("Here goes the results...");
        lblResults.setStyle("-fx-font-size: 15px;");

        Button btnCalcular = new Button("Calcular");
        Button btnBorrar = new Button("Borrar");


        btnBorrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteData(txtWeight, txtHeight, lblResults);
            }
        });

        btnCalcular.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double IBM = calculateBMI(txtWeight.getText(), txtHeight.getText());
                lblResults.setText(String.valueOf(IBM));
            }
        });

        gridPane.add(lblWeight, 0, 1);
        gridPane.add(txtWeight, 1, 1);
        gridPane.add(lblHeight, 0, 2);
        gridPane.add(txtHeight, 1, 2);
        gridPane.add(lblResults, 0, 3, 2, 1);
        gridPane.add(btnCalcular, 0, 4);
        gridPane.add(btnBorrar, 1, 4);


        gridPane.setHgap(15);
        gridPane.setVgap(15);


        root.getChildren().add(gridPane);
        root.setAlignment(Pos.CENTER);


        return root;
    }

    private void deleteData(TextField txtWeight, TextField txtHeight, Text result){
        txtWeight.setText("");
        txtHeight.setText("");
        result.setText("Here goes the results...");
    }

    private double calculateBMI(String weight, String height){

        double heightDouble = Double.parseDouble(height);
        double iBM = Double.parseDouble(weight)/(heightDouble*heightDouble);

        return iBM;
    }

    public static void main(String[] args) {
        launch();
    }
}