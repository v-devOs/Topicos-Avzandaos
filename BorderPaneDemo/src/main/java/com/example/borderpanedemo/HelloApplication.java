package com.example.borderpanedemo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.setTop(generateTopSection());
        root.setLeft(generateLeftSection());
        root.setBottom(generateBottomSection());
        root.setRight(generateRigthSection());
        root.setCenter(generateCenterSection());



        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private HBox generateTopSection(){
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 10; i++) {
            hbox.getChildren().add( new Button("Button " + i));
        }
        return  hbox;
    }

    private VBox generateLeftSection(){
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        String[] names = {"juan", "maria", "pepe" ,"julieta", "teresa"};

        for (int i = 0; i < 10 ; i++) {
            vbox.getChildren().add( new ComboBox<String>
                    (FXCollections.observableArrayList(names)));
        }

        return vbox;
    }
    private HBox generateBottomSection (){
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding( new Insets(15));

        for (int i = 0; i < 10 ; i++) {
            hbox.getChildren().add( new DatePicker());
        }

        return hbox;
    }

    private VBox generateRigthSection(){
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        String[] names = {"juan", "maria", "pepe" ,"julieta", "teresa"};

        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < 10 ; i++) {

            RadioButton r = new RadioButton("Option " + i);
            r.setToggleGroup(group);
            vbox.getChildren().add( r );
        }

        return vbox;
    }

    private StackPane generateCenterSection(){
        StackPane stackPane = new StackPane();
        Circle circle = new Circle(200);
        circle.setFill(Color.BLUE);
        Circle circle1 = new Circle(100);
        circle1.setFill(Color.BROWN);

        Rectangle rectangle = new Rectangle(150, 80);
        rectangle.setFill(Color.CORAL);

        Label label = new Label("Hello, welcome to javafx");


        stackPane.getChildren().addAll(circle, circle1, rectangle, label);



        return stackPane;
    }

    public static void main(String[] args) {
        launch();
    }
}