package com.example.playerstournament;

import com.example.playerstournament.utilities.AlertUtilities;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class AddPlayer {
    public static Optional<Player> showAndWait() {

        Dialog<Player> dialog = new Dialog<>();
        dialog.setTitle("Add new player");
        dialog.setHeaderText("Enter data about player!");

        ButtonType createButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

        TextField nameField = new TextField();
        nameField.setPromptText("Player name");

        TextField surnameField = new TextField();
        surnameField.setPromptText("Player surname");

        TextField teamField = new TextField();
        teamField.setPromptText("Player team");

        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(new Label("Name: "), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Surname: "), 0, 1);
        grid.add(surnameField, 1, 1);
        grid.add(new Label("Team: "), 0, 2);
        grid.add(teamField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButton){
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String team = teamField.getText().trim();
                Player newPlayer = new Player(name, surname, team);
                if (TournamentController.containsPlayer(newPlayer)){
                    AlertUtilities.displayAlert("Player already exists!");
                    return null;
                }
                return newPlayer;
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
