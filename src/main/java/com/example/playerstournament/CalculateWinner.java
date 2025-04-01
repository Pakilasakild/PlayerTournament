package com.example.playerstournament;

import com.example.playerstournament.utilities.AlertUtilities;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;



public class CalculateWinner {
    public static Optional<Player> showAndWait(){

        Player player = TournamentController.getBestPlayer();
        String name = player.getName();
        String surname = player.getSurname();
        String team = player.getTeam();
        int wins = player.getWins();



        Dialog<Player> dialog = new Dialog<>();
        dialog.setTitle("Winner!");
        dialog.setHeaderText("The winner!");

        ButtonType createButton = new ButtonType("GG!", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().add(createButton);

        GridPane grid = new GridPane();

        grid.add(new Label("Name: "), 0, 0);
        grid.add(new Label(name), 1, 0);
        grid.add(new Label("Surname: "), 0, 1);
        grid.add(new Label(surname), 1, 1);
        grid.add(new Label("Team: "), 0, 2);
        grid.add(new Label(team), 1, 2);
        grid.add(new Label("Wins: "), 0, 3);
        grid.add(new Label(String.valueOf(wins)), 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButton){
                if (player.getWins() == -1){
                    AlertUtilities.displayAlert("No players in tournament!");
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }
}
