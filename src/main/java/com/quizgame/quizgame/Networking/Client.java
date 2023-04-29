package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Networking.util.NetworkUtil;
import com.quizgame.quizgame.QuizApplication;

import java.util.Scanner;

public class Client {

    NetworkUtil networkUtil;
    String name;

    public Client(String serverAddress, int serverPort, String name) {
        try {
            this.name = name;
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(name);

            new ReadThreadClient(networkUtil);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sendScore(){
        Score score = new Score(
                QuizApplication.game.getCurrentScore(),
                name,
                QuizApplication.game.totalTime - QuizApplication.game.timeRemaining);
        try{
            System.out.println("Trying to write");
            networkUtil.write(score);
            System.out.println("wrote");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


