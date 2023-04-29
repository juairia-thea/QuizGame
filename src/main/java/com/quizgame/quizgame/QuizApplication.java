package com.quizgame.quizgame;

import com.quizgame.quizgame.Networking.Client;
import com.quizgame.quizgame.Networking.Score;
import com.quizgame.quizgame.Networking.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public static Scene hostWindow;
    public static Scene joinWindow;
    public static Scene multiPlayer;
    public static Scene resultMultiplayer;

    public static DatabaseHandler databaseHandler;
    public static QuestionDAO questionDAO;
    public static Game game;

    public static String serverAddress = "127.0.0.1";
    public static int serverPort = 33333;

    public static Server server;
    public static Client client;
    public static boolean isHost;
    public static boolean isMultiplayer;
    public static String playerName;
    public static ArrayList<Score> scores;
    public static boolean gameRunning = false;

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
        startWindow = newScene(new FXMLLoader(QuizApplication.class.getResource("StartWindow.fxml")), 600, 400);
        gameMode = new Scene(new FXMLLoader(QuizApplication.class.getResource("gameMode.fxml")).load(), 600, 400);
        playerChoose = new Scene(new FXMLLoader(QuizApplication.class.getResource("playerChoose.fxml")).load(), 600, 400);
        quizOptions = new Scene(new FXMLLoader(QuizApplication.class.getResource("quizOptions.fxml")).load(), 600, 400);
        questionPanel = newScene(new FXMLLoader(QuizApplication.class.getResource("questionPanel.fxml")), 600, 400);
        result = newScene(new FXMLLoader(QuizApplication.class.getResource("result.fxml")), 600, 400);
        questionSetUI = new Scene(new FXMLLoader(QuizApplication.class.getResource("questionsetUI.fxml")).load(), 650, 522);
        hostWindow = newScene(new FXMLLoader(QuizApplication.class.getResource("hostWindow.fxml")), 600, 400);
        joinWindow = newScene(new FXMLLoader(QuizApplication.class.getResource("joinWindow.fxml")), 600, 400);
        resultMultiplayer = newScene(new FXMLLoader(QuizApplication.class.getResource("resultMulti.fxml")), 600, 400);
        multiPlayer = new Scene(new FXMLLoader(QuizApplication.class.getResource("multiPlayer.fxml")).load(), 600, 400);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });


        QuestionDAO questionDAO = new QuestionDAO(databaseHandler);

        List<Question> questions = questionDAO.getAllQuestions();
//        for(Question question : questions){
//            System.out.println(question);
//        }
    }

    public static void setScene(Scene scene){

        try{
            FXMLLoader loader = (FXMLLoader) scene.getUserData();

            if (loader != null && loader.getController()  instanceof Initializable){
                Initializable controller = (Initializable)loader.getController();
                controller.initialize(null, null);
            }

            stage.setScene(scene);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Scene newScene(FXMLLoader loader, double width, double height) throws IOException
    {
        Scene scene = new Scene(loader.load(), 600, 400);
        scene.setUserData(loader);
        return scene;
    }

    public static void startGame(){
        scores = new ArrayList<>();
        setScene(QuizApplication.questionPanel);
        gameRunning = true;
    }

    public static void stopGame(){
        gameRunning = false;

        if(isMultiplayer){
            if(isHost){
                addScore(new Score(QuizApplication.game.getCurrentScore(), playerName, game.totalTime - game.timeRemaining));
                server.sendScores();
            }else{
                client.sendScore();
            }

        }

        showScore();
    }

    public static void showScore(){
        if(gameRunning)
            return;
        if(isMultiplayer){
            setScene(resultMultiplayer);
        }else{
            setScene(result);
        }
    }

    public static void addScore(Score score){
        scores.add(score);

        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o2.score - o1.score;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}