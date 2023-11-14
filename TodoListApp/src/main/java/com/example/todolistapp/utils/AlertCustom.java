package com.example.todolistapp.utils;

import javafx.scene.control.Alert;

public class AlertCustom {
    public void showMessage(String message, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.show();
    }
}
