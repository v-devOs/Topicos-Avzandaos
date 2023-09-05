package com.example.bmiaplication;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

public class BMIAplication extends Application {
    TextField txtWeight = new TextField();
    TextField txtHeight = new TextField();
    Button btnCalcular = new Button("Calculate");
    Button btnBorrar = new Button("Exit");
    Text lblResults = new Text("Here goes the results...");

    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(initGUI(stage), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    private StackPane initGUI(Stage stage){

        // Imagen
        Image image = new Image(getClass().getResource("/images/bmi.png").toString());
        ImageView imageBMI = new ImageView();
        imageBMI.setImage(image);
        imageBMI.setFitWidth(200);
        imageBMI.setFitHeight(100);
        imageBMI.setPreserveRatio(true);

        StackPane root = new StackPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-fill: purple");

        Label title = new Label("BIM Calculator");
        title.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-text-fill: #dc0b82; -fx-font-weight: bold;" );
        gridPane.add(title, 0, 0, 2, 1);

        Label lblWeight = new Label("Weight");
        Label lblHeight = new Label("Height");

        String styleLabels = "-fx-font-family: Verdana; -fx-font-size: 12px; -fx-text-fill: #dc0b82;";
        lblHeight.setStyle(styleLabels);
        lblWeight.setStyle(styleLabels);

        lblResults.setStyle("-fx-font-size: 15px;");


        btnBorrar.setOnAction(handlerButtons);
        btnCalcular.setOnAction(handlerButtons);

        HBox hboxBtns = new HBox(2);
        hboxBtns.setAlignment(Pos.CENTER);

        hboxBtns.getChildren().addAll( btnCalcular, btnBorrar );

        btnCalcular.setMinWidth(150);
        btnBorrar.setMinWidth(150);

        gridPane.add(lblWeight, 0, 1);
        gridPane.add(txtWeight, 1, 1);
        gridPane.add(lblHeight, 0, 2);
        gridPane.add(txtHeight, 1, 2);
        gridPane.add(lblResults, 0, 3, 2, 1);
        gridPane.add( hboxBtns, 0, 4 , 2 , 1 );
        gridPane.add( imageBMI, 2, 0, 1, 4);


        gridPane.setHgap(15);
        gridPane.setVgap(15);


        root.getChildren().add(gridPane);
        root.setAlignment(Pos.CENTER);


        return root;
    }

    private void closeWindows(Stage stage){
        stage.close();
    }

    private double calculateBMI(Double weight, Double height){

        final DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(weight/(height*height)));
    }

    private String getBMICategory( double bmi){

        String category = "UNDEFINED";

        if( bmi < 18.5) category = "UNDERWEIGHT";
        else if( bmi <= 24.9) category = "NORMAL";
        else if( bmi <= 29.9) category = "OVERWEIGHT";
        else if( bmi <= 39.9) category = "OBESE";
        else category = "MORBIDLY OBESE";

        return category;
    }

    EventHandler<ActionEvent> handlerButtons = ( event -> {

        if( event.getSource() == btnCalcular ){
            double weigth = Double.parseDouble(txtWeight.getText());
            double heigth = Double.parseDouble(txtHeight.getText());

            double bmi = calculateBMI( weigth, heigth );
            String category = getBMICategory(bmi);

            String results = "You BMI is " + bmi + ", with the category " + category;

            lblResults.setText(results);
        }
        else{
            confirmExit();
        }
    });

    private void confirmExit(){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Exit");

        Optional<ButtonType> response = confirmation.showAndWait();

        if( response.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}