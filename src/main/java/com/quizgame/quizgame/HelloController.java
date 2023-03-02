package com.quizgame.quizgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void startGame() {
        QuizApplication.setScene(QuizApplication.playerChoose);
    }

    @FXML
    protected void singlePlayer() {
        QuizApplication.setScene(QuizApplication.gameMode);
    }

    @FXML
    protected void gameModeNext() {
        QuizApplication.setScene(QuizApplication.quizOptions);
    }

    @FXML
    protected void play() {
        QuizApplication.setScene(QuizApplication.questionPanel);
    }

    @FXML
    protected void optionA() {
        QuizApplication.setScene(QuizApplication.result);
    }

    @FXML
    protected void mainMenu() {
        QuizApplication.setScene(QuizApplication.sampleScene);
    }
}