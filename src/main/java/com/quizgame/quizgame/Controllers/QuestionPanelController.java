package com.quizgame.quizgame.Controllers;
import com.quizgame.quizgame.Question;
import com.quizgame.quizgame.QuizApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionPanelController implements Initializable {
    @FXML
    private Label questionLabel;

    @FXML
    private Label questionNumberLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label Option1Label;

    @FXML
    private Label Option2Label;

    @FXML
    private Label Option3Label;

    @FXML
    private Label Option4Label;

    private Question currentQuestion;
    Timer myTimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentQuestion = QuizApplication.game.nextQuestion();
        if(currentQuestion == null){
            return;
        }

        setCurrentQuestionToUI();

        QuizApplication.game.timeRemaining = QuizApplication.game.getTotalQuestionNumbers()*10;
        QuizApplication.game.totalTime = QuizApplication.game.timeRemaining;
        myTimer = new Timer();

        myTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                QuizApplication.game.timeRemaining--;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timerLabel.setText(QuizApplication.game.timeRemaining + "");
                        if(QuizApplication.game.timeRemaining == 0){
                            myTimer.cancel();
                            QuizApplication.stopGame();
                        }
                    }
                });
            }
        }, 0, 1000);
        timerLabel.setText(QuizApplication.game.timeRemaining + "");
    }

    private void setCurrentQuestionToUI(){
        if(currentQuestion == null){
            return;
        }
        Option1Label.setText(currentQuestion.getOption1());
        Option2Label.setText(currentQuestion.getOption2());
        Option3Label.setText(currentQuestion.getOption3());
        Option4Label.setText(currentQuestion.getOption4());

        questionLabel.setText(currentQuestion.getStatement());
        questionNumberLabel.setText(String.valueOf(QuizApplication.game.getCurrentQuestion()));

    }

    @FXML
    private void option1Click() {
        clickOptionEvent(1);
    }

    @FXML
    private void option2Click() {
        clickOptionEvent(2);
    }

    @FXML
    private void option3Click() {
        clickOptionEvent(3);
    }

    @FXML
    private void option4Click() {
        clickOptionEvent(4);
    }

    private void clickOptionEvent(int option)
    {
        if (option == currentQuestion.getCorrectOption()){
            QuizApplication.game.addScore();
        }

        currentQuestion = QuizApplication.game.nextQuestion();

        if (currentQuestion == null){
            myTimer.cancel();
            QuizApplication.stopGame();
        }

        setCurrentQuestionToUI();
    }
}