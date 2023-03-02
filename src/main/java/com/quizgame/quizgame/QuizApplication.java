package com.quizgame.quizgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizApplication extends Application {
    public static Stage stage;
    public static Scene sampleScene;
    public static Scene gameMode;
    public static Scene playerChoose;
    public static Scene quizOptions;
    public static Scene questionPanel;
    public static Scene result;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        QuizApplication.stage = stage;
        sampleScene = new Scene(new FXMLLoader(QuizApplication.class.getResource("sample.fxml")).load(), 600, 400);
        gameMode = new Scene(new FXMLLoader(QuizApplication.class.getResource("gameMode.fxml")).load(), 600, 400);
        playerChoose = new Scene(new FXMLLoader(QuizApplication.class.getResource("playerChoose.fxml")).load(), 600, 400);
        quizOptions = new Scene(new FXMLLoader(QuizApplication.class.getResource("quizOptions.fxml")).load(), 600, 400);
        quizOptions = new Scene(new FXMLLoader(QuizApplication.class.getResource("questionPanel.fxml")).load(), 600, 400);
        result = new Scene(new FXMLLoader(QuizApplication.class.getResource("result.fxml")).load(), 600, 400);
    }

    public static void setScene(Scene scene){
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}