package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.Networking.Server;
import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class hostWindowController implements Initializable {
    @FXML
    Pane paneHost;

    @FXML
    public void nextButtonClick(){
        QuizApplication.setScene(QuizApplication.gameMode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (QuizApplication.playerName == null){
            return;
        }

        Server server = new Server(paneHost);
        QuizApplication.server = server;
    }
}
