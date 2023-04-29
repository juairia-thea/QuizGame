package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Networking.util.NetworkUtil;
import com.quizgame.quizgame.QuizApplication;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Server extends Thread{
    private Pane pane;
    private ServerSocket serverSocket;
    private HashMap<String, NetworkInformation> clientNetworkInformationMap;
    private Collection<Node> nodes;
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

    public void sendQuestion(){
        for(var client : clientNetworkInformationMap.keySet()){
            try{
                System.out.println("Trying to send " + client);
                var networkInformation = clientNetworkInformationMap.get(client);
                networkInformation.networkUtil.write(QuizApplication.game);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendScores(){
        for(var client : clientNetworkInformationMap.keySet()){
            try{
                System.out.println("Trying to send " + client);
                var networkInformation = clientNetworkInformationMap.get(client);
                networkInformation.networkUtil.write(QuizApplication.scores);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();

        NetworkInformation networkInformation = new NetworkInformation(networkUtil);
        clientNetworkInformationMap.put(clientName, networkInformation);
        new ReadThreadServer(networkInformation, clientNetworkInformationMap);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(nodes == null){
                    nodes = new ArrayList<>();
                }

                Label label = new Label();
                label.setText(clientName + " joined");
                label.setLayoutX(170);
                label.setLayoutY(80 + clientCount*30);
                pane.getChildren().add(label);
                nodes.add(label);
            }
        });

        clientCount++;
    }

    public void closeConnection(){
        try{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pane.getChildren().removeAll(nodes);
                }
            });

            for(var client : clientNetworkInformationMap.keySet()){
                var networkInformation = clientNetworkInformationMap.get(client);
                networkInformation.networkUtil.closeConnection();
            }
            serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
