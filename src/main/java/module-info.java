module com.example.playerstournament {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.playerstournament to javafx.fxml;
    exports com.example.playerstournament;
}