package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;

public class PlayerChooseController {
    @FXML
    protected void singlePlayer() {
        QuizApplication.setScene(QuizApplication.gameMode);
    }

    @FXML
    protected void multiPlayer() {
        QuizApplication.setScene(QuizApplication.multiPlayer);
    }
}
