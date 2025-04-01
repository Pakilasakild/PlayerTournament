package com.example.playerstournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.OptionalLong;

public class TournamentController {
    @FXML
    public Button btn_add_player;
    @FXML
    public Button btn_player_data;
    @FXML
    public TableView table_players;
    @FXML
    public TableColumn players_name_column;
    @FXML
    public TableColumn players_surname_column;
    @FXML
    public TableColumn players_team_column;
    @FXML
    public TableView table_tour;
    @FXML
    public TableColumn tour_name_column;
    @FXML
    public TableColumn tour_surname_column;
    @FXML
    public TableColumn tour_team_column;
    @FXML
    public TableColumn tour_wins_column;
    @FXML
    public Button btn_add_player_to_tour;
    @FXML
    public Button btn_find_winner;
    @FXML
    static ObservableList<Player> players = FXCollections.observableArrayList(
            new Player("Jonas", "Meskauskas", "Dainava"),
            new Player("Paulius", "Brazevicius", "Rytas"),
            new Player("Gytis", "Pradenas", "GAS")
    );
    @FXML
    static ObservableList<Player> tourPlayers = FXCollections.observableArrayList();
    @FXML
    ObservableList<Player> empty = FXCollections.observableArrayList();


    @FXML
    public void initialize() {


        players_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        players_surname_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        players_team_column.setCellValueFactory(new PropertyValueFactory<>("team"));

        tour_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        tour_surname_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tour_team_column.setCellValueFactory(new PropertyValueFactory<>("team"));
        tour_wins_column.setCellValueFactory(new PropertyValueFactory<>("wins"));

        table_players.setItems(players);
        table_tour.setItems(tourPlayers);
    }

    @FXML
    private void addPlayer(){
        Optional<Player> newPlayer = AddPlayer.showAndWait();
        newPlayer.ifPresent(player -> {
            players.add(player);
        });
    }
    @FXML
    private void addPlayerToTournament(){
        Optional<Player> newPlayer = AddPlayerToTournament.showAndWait();
        newPlayer.ifPresent(player -> {
            tourPlayers.add(player);
        });
    }
    @FXML
    private void editData(){
        Optional<Player> editedPlayer = EditPlayerData.showAndWait();
        editedPlayer.ifPresent(player -> {
            for (Player value : players) {
                if (value.getName().equals(player.getName()) && value.getSurname().equals(player.getSurname())) {
                    value.setTeam(player.getTeam());
                    value.setWins(player.getWins());
                }
            }
            for (Player tourPlayer : tourPlayers) {
                if (tourPlayer.getName().equals(player.getName()) && tourPlayer.getSurname().equals(player.getSurname())) {
                    tourPlayer.setTeam(player.getTeam());
                    tourPlayer.setWins(player.getWins());
                }
            }
        });
        table_players.refresh();
        table_tour.refresh();
    }




    @FXML
    public static ObservableList<Player> getPlayers(){
        return players;
    }
    @FXML
    public static boolean containsPlayer(Player player){
        for (Player value : players) {
            if (value.getName().equals(player.getName()) && value.getSurname().equals(player.getSurname())) {
                return true;
            }
        }
        return false;
    }
    @FXML
    public static boolean containsTourPlayer(Player player){
        for (Player tourPlayer : tourPlayers) {
            if (tourPlayer.getName().equals(player.getName()) && tourPlayer.getSurname().equals(player.getSurname())){
                return true;
            }
        }
        return false;
    }
    @FXML
    public static Player getBestPlayer(){
        Player bestPlayer = new Player("temp", "temp", "temp", -1);
        for (Player player : tourPlayers){
            if (player.getWins() > bestPlayer.getWins()){
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
    @FXML
    private void showResult(){
        Optional<Player> winner = CalculateWinner.showAndWait();
    }
}
