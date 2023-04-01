package com.quizgame.quizgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class QuizApplication extends Application {
    public static Stage stage;
    public static Scene startWindow;
    public static Scene gameMode;
    public static Scene playerChoose;
    public static Scene quizOptions;
    public static Scene questionPanel;
    public static Scene result;
    public static Scene questionSetUI;
    public static DatabaseHandler databaseHandler;
    public static QuestionDAO questionDAO;
    public static Game game;

    @Override
    public void start(Stage stage) throws IOException {
        databaseHandler = new DatabaseHandler();
        questionDAO = new QuestionDAO(databaseHandler);
        game = new Game();

        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("StartWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        QuizApplication.stage = stage;
        startWindow = new Scene(new FXMLLoader(QuizApplication.class.getResource("StartWindow.fxml")).load(), 600, 400);
        gameMode = new Scene(new FXMLLoader(QuizApplication.class.getResource("gameMode.fxml")).load(), 600, 400);
        playerChoose = new Scene(new FXMLLoader(QuizApplication.class.getResource("playerChoose.fxml")).load(), 600, 400);
        quizOptions = new Scene(new FXMLLoader(QuizApplication.class.getResource("quizOptions.fxml")).load(), 600, 400);
        questionPanel = new Scene(new FXMLLoader(QuizApplication.class.getResource("questionPanel.fxml")).load(), 600, 400);
        result = new Scene(new FXMLLoader(QuizApplication.class.getResource("result.fxml")).load(), 600, 400);
        questionSetUI = new Scene(new FXMLLoader(QuizApplication.class.getResource("questionsetUI.fxml")).load(), 650, 522);

        QuestionDAO questionDAO = new QuestionDAO(databaseHandler);

        List<Question> questions = questionDAO.getAllQuestions();
        for(Question question : questions){
            System.out.println(question);
        }
    }

    public static void setScene(Scene scene){
        try{
            if (scene == questionPanel) {
                scene = questionPanel = new Scene(new FXMLLoader(QuizApplication.class.getResource("questionPanel.fxml")).load(), 600, 400);
            }
            if (scene == result) {
                scene = result = new Scene(new FXMLLoader(QuizApplication.class.getResource("result.fxml")).load(), 600, 400);
            }

            stage.setScene(scene);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}