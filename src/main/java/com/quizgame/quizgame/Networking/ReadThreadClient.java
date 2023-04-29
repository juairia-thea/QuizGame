package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Game;
import com.quizgame.quizgame.Networking.util.NetworkUtil;
import com.quizgame.quizgame.QuizApplication;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThreadClient implements Runnable{
    private Thread thr;
    private NetworkUtil networkUtil;


    public ReadThreadClient(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;

        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            Object o = networkUtil.read();
            if (o instanceof Game) {
                QuizApplication.game = (Game)o;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        QuizApplication.startGame();
                    }
                });
            }
            while (true) {
                Object obj = networkUtil.read();
                if (obj instanceof ArrayList) {
                    QuizApplication.scores = (ArrayList<Score>)obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            QuizApplication.showScore();
                        }
                    });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
