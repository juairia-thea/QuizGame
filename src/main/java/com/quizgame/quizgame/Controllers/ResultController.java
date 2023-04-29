package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.QuizApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultLabel.setText("You got " + QuizApplication.game.getCurrentScore() + "/" +
                QuizApplication.game.getTotalQuestionNumbers());
    }

    @FXML
    protected void goToStart(){
        QuizApplication.setScene(QuizApplication.startWindow);

        QuizApplication.game.initialize();
    }

    @FXML
    protected void exit(){
        Platform.exit();
        System.exit(0);
    }
}
