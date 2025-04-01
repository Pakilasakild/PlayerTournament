package com.example.playerstournament;

import com.example.playerstournament.utilities.AlertUtilities;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class AddPlayerToTournament {
    public static Optional<Player> showAndWait() {
        Dialog<Player> dialog = new Dialog<>();
        dialog.setTitle("Add player to tournament");
        dialog.setHeaderText("Choose player!");

        ButtonType createButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

        ComboBox<Player> combo = new ComboBox<>(TournamentController.getPlayers());

        TextField winsField = new TextField();
        winsField.setPromptText("Amount of wins");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Choose player: "), 0, 0);
        grid.add(combo, 1, 0);
        grid.add(new Label("Wins: "), 0, 1);
        grid.add(winsField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButton) {
                Player player = combo.getValue();
                int wins = Integer.parseInt(winsField.getText().trim());
                if (TournamentController.containsTourPlayer(player)) {
                    AlertUtilities.displayAlert("Player already exists in tournament.");
                } else {
                    return new Player(player.getName(), player.getSurname(), player.getTeam(), wins);
                }
            }
            return null;
        });
        return dialog.showAndWait();
    }
}
