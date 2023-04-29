package com.quizgame.quizgame.Controllers;
import java.util.ArrayList;
import java.util.List;

import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class QuizOptionsController {
    @FXML
    private CheckBox sportCheck;

    @FXML
    private CheckBox scienceCheck;

    @FXML
    private CheckBox musicCheck;

    @FXML
    private CheckBox movieCheck;

    @FXML
    void play() {
        ArrayList<String> selectedCheckBoxes = new ArrayList<String>();
        if (sportCheck.isSelected()) {
            selectedCheckBoxes.add(sportCheck.getText());
        }
        if (scienceCheck.isSelected()) {
            selectedCheckBoxes.add(scienceCheck.getText());
        }
        if (musicCheck.isSelected()) {
            selectedCheckBoxes.add(musicCheck.getText());
        }
        if (movieCheck.isSelected()) {
            selectedCheckBoxes.add(movieCheck.getText());
        }

        QuizApplication.game.setCategories(selectedCheckBoxes);

        QuizApplication.game.setQuestions(QuizApplication.questionDAO.getQuestionsByCategoriesAndDifficulties(
                QuizApplication.game.getCategories(),
                QuizApplication.game.getDifficultyLevels()
        ));

        if(QuizApplication.isMultiplayer && QuizApplication.isHost){
            QuizApplication.server.sendQuestion();
        }

        QuizApplication.startGame();
    }
}
