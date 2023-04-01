package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class GameModeController {
    @FXML
    private CheckBox easyCheck;

    @FXML
    private CheckBox mediumCheck;

    @FXML
    private CheckBox hardCheck;

    @FXML
    private TextField qsNumberText;

    @FXML
    protected void goNext(){
        ArrayList<String> difficultyLevels = new ArrayList<String>();

        if (easyCheck.isSelected()) {
            difficultyLevels.add("Easy");
        }
        if (mediumCheck.isSelected()) {
            difficultyLevels.add("Medium");
        }
        if (hardCheck.isSelected()) {
            difficultyLevels.add("Hard");
        }

        int numberOfQuestions = 10;
        try {
            numberOfQuestions = Integer.parseInt(qsNumberText.getText());
        } catch (NumberFormatException ex) {
            numberOfQuestions = 10;
        }

        QuizApplication.game.setDifficultyLevels(difficultyLevels);
        QuizApplication.game.setTotalQuestionNumbers(numberOfQuestions);

        System.out.println(QuizApplication.game);

        QuizApplication.setScene(QuizApplication.quizOptions);
    }
}
