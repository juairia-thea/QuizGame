package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.Networking.Client;
import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class joinWindowController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (QuizApplication.playerName == null){
            return;
        }

        Client client = new Client(QuizApplication.serverAddress, QuizApplication.serverPort, QuizApplication.playerName);
    }
}
