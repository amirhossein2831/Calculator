package com.example.calculator.Error;

import javafx.scene.control.Alert;

public class Error {
    public static void ErrorNoNumber() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("there is no number man!!");
        alert.showAndWait();
    }
    public static void ErrorNoOperand() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("the format in wrong man!!");
        alert.showAndWait();
    }
}
