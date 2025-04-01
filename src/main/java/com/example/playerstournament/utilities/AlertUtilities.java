package com.example.playerstournament.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class AlertUtilities {
    public static void displayAlert(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        styleDialog(alert);
        alert.setContentText(content);
        alert.setTitle("Error!");
        alert.setHeaderText("Error!");
        alert.showAndWait();
    }

    private static void styleDialog(Dialog<?> dialog){
        DialogPane dialogPane = dialog.getDialogPane();
    }
}
