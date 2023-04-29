package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Networking.util.NetworkUtil;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Thread{
    private Pane pane;
    private ServerSocket serverSocket;
    private HashMap<String, NetworkInformation> clientNetworkInformationMap;
    private int clientCount = 0;

    public Server(Pane pane) {
        this.pane = pane;
        this.start();
    }

    @Override
    public void run() {
        clientNetworkInformationMap = new HashMap<String, NetworkInformation>();

        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server has started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted a connection...");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = new Label();
                label.setText(clientName + " joined");
                label.setLayoutX(170);
                label.setLayoutY(80 + clientCount*30);
                pane.getChildren().add(label);
            }
        });

        clientCount++;
    }

}
