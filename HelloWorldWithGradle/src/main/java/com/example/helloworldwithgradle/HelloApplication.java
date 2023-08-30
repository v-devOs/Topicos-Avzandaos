package com.example.helloworldwithgradle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        TextField input = new TextField();
        TextField inputLastName = new TextField();
        input.setPromptText("Please type your name...");
        inputLastName.setPromptText("Please type your lastname");
        input.setMaxWidth(200);
        inputLastName.setMaxWidth(200);

        Button btn = new Button("Hello");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String textName = input.getText().trim();
                String textLastName = inputLastName.getText().trim();
                String errorMessage = "Please type your: ";

                if( textName.isEmpty() || textLastName.isEmpty()) {

                    if(textName.isEmpty() && textLastName.isEmpty()) errorMessage += "name and lastname";
                    else if(textName.isEmpty()) errorMessage += "name";
                    else errorMessage += "last name";
                    showMessage(errorMessage, Alert.AlertType.ERROR);
                }
                else {
                    showMessage("Hello: " + textName + " " + textLastName, Alert.AlertType.INFORMATION);
                }

                input.requestFocus();
                input.setText("");
                inputLastName.setText("");
            }
        });

        VBox boxControls = new VBox(10);
        // boxControls.getChildren().addAll( input, btn);
        boxControls.setAlignment(Pos.CENTER);

        HBox hboxControls = new HBox(10);
        hboxControls.setAlignment(Pos.CENTER);
        hboxControls.getChildren().addAll( input, inputLastName);
        boxControls.getChildren().addAll(hboxControls, btn);

        StackPane root = new StackPane();
        root.getChildren().add( boxControls );

        Scene scene = new Scene(root, 450, 250);

        primaryStage.setTitle("Hello world");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMessage(String message, Alert.AlertType alt){
        Alert alert = new Alert(alt);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}