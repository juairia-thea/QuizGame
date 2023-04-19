package com.quizgame.quizgame.Networking;

import com.quizgame.quizgame.Networking.util.NetworkUtil;

import java.util.ArrayList;

public class NetworkInformation {
    ArrayList<String> inbox;
    NetworkUtil networkUtil;

    NetworkInformation(NetworkUtil networkUtil){
        inbox = new ArrayList<>();
        this.networkUtil = networkUtil;
    }

    public void addMessage(String message){
        inbox.add(message);
    }

    public NetworkUtil getNetworkUtil(){
        return this.networkUtil;
    }

    public ArrayList<String> getInbox(){
        return this.inbox;
    }
}
