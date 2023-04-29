package com.quizgame.quizgame.Networking;

import java.io.Serializable;

public class Score implements Serializable {
    public int score;
    public String name;
    public int time;

    public Score(int score, String name, int time) {
        this.score = score;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
