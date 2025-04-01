package com.example.playerstournament;

import com.example.playerstournament.utilities.AlertUtilities;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class EditPlayerData {
    public static Optional<Player> showAndWait() {
        Dialog<Player> dialog = new Dialog<>();
        dialog.setTitle("Edit player data");
        dialog.setHeaderText("Edit data of a player");

        ButtonType createButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

        TextField teamField = new TextField();
        teamField.setPromptText("Player team");

        TextField winsField = new TextField();
        winsField.setPromptText("Player wins");

        ComboBox<Player> combo = new ComboBox<>(TournamentController.getPlayers());

        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(new Label("Choose player: "), 0, 0);
        grid.add(combo, 0, 0);
        grid.add(new Label("Team: "), 0, 1);
        grid.add(teamField, 1, 1);
        grid.add(new Label("Wins: "), 0, 2);
        grid.add(winsField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButton) {
                String name = combo.getValue().getName();
                String surname = combo.getValue().getSurname();
                String team;
                if (teamField.getText().isEmpty()) {
                    team = combo.getValue().getTeam();
                } else {
                    team = teamField.getText().trim();
                }
                int wins;
                if (winsField.getText().isEmpty()) {
                    wins = combo.getValue().getWins();
                } else {
                    wins = Integer.parseInt(winsField.getText().trim());
                }
                if (wins < 0){
                    AlertUtilities.displayAlert("Invalid wins amount!");
                    return null;
                }
                return new Player(name, surname, team, wins);
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
