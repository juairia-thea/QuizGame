package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.QuizApplication;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadThreadServer implements Runnable{
    NetworkInformation networkInformation;
    HashMap<String, NetworkInformation> clientNetworkInformationMap;
    private Thread thr;

    public ReadThreadServer(NetworkInformation networkInformation, HashMap<String, NetworkInformation> clientNetworkInformationMap) {
        this.networkInformation = networkInformation;
        this.clientNetworkInformationMap = clientNetworkInformationMap;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object o = this.networkInformation.networkUtil.read();

                if(o instanceof Score){
                    var score = (Score)o;
                    QuizApplication.addScore((Score)o);
                    System.out.println(score.name);

                    QuizApplication.server.sendScores();

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
                this.networkInformation.networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
