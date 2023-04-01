package com.quizgame.quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<String> difficultyLevels;
    private List<String> categories;
    private List<Question> questions;
    private int totalQuestionNumbers = 10;
    private int currentQuestion = 0;
    private int currentScore = 0;


    public List<String> getDifficultyLevels() {
        return difficultyLevels;
    }

    public void setDifficultyLevels(List<String> difficultyLevels) {
        this.difficultyLevels = difficultyLevels;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        if (this.questions != null){
            Collections.shuffle(questions);
            this.questions = questions.subList(0, totalQuestionNumbers);
            this.totalQuestionNumbers = this.questions.size();
        }
    }

    public Question nextQuestion(){
        if (questions != null && currentQuestion < questions.size() - 1){
            return questions.get(currentQuestion++);
        }

        return null;
    }

    public void addScore(){
        currentScore++;
    }

    public int getTotalQuestionNumbers() {
        return totalQuestionNumbers;
    }

    public void setTotalQuestionNumbers(int totalQuestionNumbers) {
        this.totalQuestionNumbers = totalQuestionNumbers;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void initialize()
    {
        totalQuestionNumbers = 10;
        currentQuestion = 0;
        currentScore = 0;
    }

    @Override
    public String toString() {
        return "Game{" +
                "difficultyLevels=" + difficultyLevels +
                ", categories=" + categories +
                ", questions=" + questions +
                ", totalQuestionNumbers=" + totalQuestionNumbers +
                ", currentQuestion=" + currentQuestion +
                ", currentScore=" + currentScore +
                '}';
    }
}
