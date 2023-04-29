package com.quizgame.quizgame;

import com.quizgame.quizgame.Networking.Client;
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

    public static DatabaseHandler databaseHandler;
    public static QuestionDAO questionDAO;
    public static Game game;

    public static String serverAddress = "127.0.0.1";
    public static int serverPort = 33333;

    public static Server server;
    public static Client client;
    public static boolean isHost;
    public static String playerName;

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

    public static void main(String[] args) {
        launch();
    }
}