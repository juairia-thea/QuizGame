package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;

public class StartWindowController {
    @FXML
    protected void goToSetQuestion(){
        QuizApplication.setScene(QuizApplication.questionSetUI);
    }

    @FXML
    protected void goToPlayerChoose(){
        QuizApplication.setScene(QuizApplication.playerChoose);
    }
}
