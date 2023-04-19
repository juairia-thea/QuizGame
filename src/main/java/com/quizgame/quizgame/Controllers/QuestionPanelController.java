package com.quizgame.quizgame.Controllers;
import com.quizgame.quizgame.Question;
import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentQuestion = QuizApplication.game.nextQuestion();
        System.out.println("Initializing");

        setCurrentQuestionToUI();
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
            QuizApplication.setScene(QuizApplication.result);
        }

        setCurrentQuestionToUI();
    }
}