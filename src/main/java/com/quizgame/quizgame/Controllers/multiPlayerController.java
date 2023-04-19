package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class multiPlayerController {
    @FXML
    TextField nameText;

    @FXML
    protected void hostWindow() {
        QuizApplication.playerName = nameText.getText();
        QuizApplication.setScene(QuizApplication.hostWindow);
    }

    @FXML
    protected void joinWindow() {
        QuizApplication.playerName = nameText.getText();
        QuizApplication.setScene(QuizApplication.joinWindow);
    }
}
