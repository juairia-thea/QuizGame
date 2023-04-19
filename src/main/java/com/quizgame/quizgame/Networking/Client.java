package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Networking.util.NetworkUtil;

import java.util.Scanner;

public class Client {

    public Client(String serverAddress, int serverPort, String name) {
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


