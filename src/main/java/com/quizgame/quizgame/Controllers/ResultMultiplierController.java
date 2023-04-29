package com.quizgame.quizgame.Controllers;

import com.quizgame.quizgame.Networking.Score;
import com.quizgame.quizgame.QuizApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ResultMultiplierController  implements Initializable {
    @FXML
    Pane resultMultiPane;
    Collection<Node> nodes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (QuizApplication.scores == null)
            return;

        if(nodes != null)
            resultMultiPane.getChildren().removeAll(nodes);

        nodes = new ArrayList<>();

        for(int i = 0; i < QuizApplication.scores.size(); i++){
            Score score = QuizApplication.scores.get(i);
            Label rankLabel = new Label((i+1) + "");
            Label nameLabel = new Label(score.name);
            Label scoreLabel = new Label((score.score + ""));
            Label timeLabel = new Label((score.time + ""));

            int YPosition = 43 + (i+1)*30;

            rankLabel.setLayoutX(74);
            rankLabel.setLayoutY(YPosition);

            nameLabel.setLayoutX(174);
            nameLabel.setLayoutY(YPosition);

            scoreLabel.setLayoutX(334);
            scoreLabel.setLayoutY(YPosition);

            timeLabel.setLayoutX(454);
            timeLabel.setLayoutY(YPosition);

            nodes.add(rankLabel);
            nodes.add(nameLabel);
            nodes.add(scoreLabel);
            nodes.add(timeLabel);
        }
        resultMultiPane.getChildren().addAll(nodes);
    }

    @FXML
    protected void goToStart(){
        if(QuizApplication.server != null){
            QuizApplication.server.closeConnection();
        }
        QuizApplication.setScene(QuizApplication.startWindow);
        QuizApplication.game.initialize();
    }
}
