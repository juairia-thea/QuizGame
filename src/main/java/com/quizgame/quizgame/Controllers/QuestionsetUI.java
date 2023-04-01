package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.Question;
import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class QuestionsetUI {
    @FXML
    private TextField questionField;

    @FXML
    private TextField option1Field;

    @FXML
    private TextField option2Field;

    @FXML
    private TextField option3Field;

    @FXML
    private TextField option4Field;

    @FXML
    private TextField correctAnswerField;

    @FXML
    private TextField categoryField;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> correctAnswerComboBox;

    @FXML
    private Label questionStatementLabel;

    @FXML
    private Label option1Label;

    @FXML
    private Label option2Label;

    @FXML
    private Label option3Label;

    @FXML
    private Label option4Label;

    @FXML
    private Label correctAnsLabel;

    @FXML
    private Label categoryIdLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
    }

    @FXML
    void save() {
        String questionStatement = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        int correctAnswer = Integer.valueOf(correctAnswerComboBox.getValue());
        String category = categoryComboBox.getValue();
        String difficulty = difficultyComboBox.getValue();

        Question question = new Question(1, questionStatement, option1, option2, option3, option4, correctAnswer, category, difficulty);

        QuizApplication.questionDAO.addQuestion(question);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Question saved successfully!");
        alert.showAndWait();

        clearAll();

        System.out.println(question);
    }

    private void clearAll(){
        questionField.clear();
        option1Field.clear();
        option2Field.clear();
        option3Field.clear();
        option4Field.clear();
    }

    @FXML
    void goBack() {
        QuizApplication.setScene(QuizApplication.startWindow);
    }

}
