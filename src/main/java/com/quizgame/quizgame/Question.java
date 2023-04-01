package com.quizgame.quizgame;

public class Question {
    private int id;
    private String statement;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOption;
    private String category;
    private String difficulty;

    public Question(int id, String statement, String option1, String option2, String option3, String option4, int correctOption, String category, String difficulty) {
        this.id = id;
        this.statement = statement;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.category = category;
        this.difficulty = difficulty;
    }

    public Question(String statement, String option1, String option2, String option3, String option4, int correctOption, String category) {
        this.statement = statement;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.category = category;
        this.difficulty = "Easy";
    }

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", statement='" + statement + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correctOption=" + correctOption +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    public String getDifficulty() {
        return difficulty;
    }
}
