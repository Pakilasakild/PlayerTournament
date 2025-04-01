package com.example.playerstournament;

public class Player {
    String name;
    String surname;
    String team;
    int wins;

    public Player(String name, String surname, String team) {
        this.name = name;
        this.surname = surname;
        this.team = team;
    }

    public Player(String name, String surname, String team, int wins) {
        this.name = name;
        this.surname = surname;
        this.team = team;
        this.wins = wins;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTeam() {
        return team;
    }
    public int getWins(){
        return wins;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + team;
    }
}
