package com.quizgame.quizgame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private DatabaseHandler databaseHandler;

    public QuestionDAO(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM question";
        ResultSet resultSet = databaseHandler.executeQuery(query);
        return createQuestionListFromResultSet(resultSet);
    }

    public List<Question> getQuestionsByCategoryAndDifficulty(String category, String difficulty) {
        String query = "SELECT * FROM question WHERE category = '" + category + "' AND difficulty = '" + difficulty + "'";
        ResultSet resultSet = databaseHandler.executeQuery(query);
        return createQuestionListFromResultSet(resultSet);
    }

    public List<Question> getQuestionsByCategoriesAndDifficulties(List<String> categories, List<String> difficulties) {
        String categoryStr = String.join("', '", categories);
        String difficultyStr = String.join("', '", difficulties);
        String query = "SELECT * FROM question WHERE category IN ('" + categoryStr + "') AND difficulty IN ('" + difficultyStr + "')";
        ResultSet resultSet = databaseHandler.executeQuery(query);
        return createQuestionListFromResultSet(resultSet);
    }

    public boolean addQuestion(Question question) {
        String query = "INSERT INTO question (statement, option1, option2, option3, option4, correctOption, category, difficulty) " +
                "VALUES ('" + question.getStatement() + "', '" + question.getOption1() + "', '" + question.getOption2() + "', " +
                "'" + question.getOption3() + "', '" + question.getOption4() + "', " + question.getCorrectOption() + ", " +
                "'" + question.getCategory() + "', '" + question.getDifficulty() + "')";
        return databaseHandler.executeUpdate(query) > 0;
    }

    public boolean updateQuestion(Question question) {
        String query = "UPDATE question SET statement = '" + question.getStatement() + "', option1 = '" + question.getOption1() + "', " +
                "option2 = '" + question.getOption2() + "', option3 = '" + question.getOption3() + "', option4 = '" + question.getOption4() + "', " +
                "correctOption = " + question.getCorrectOption() + ", category = '" + question.getCategory() + "', " +
                "difficulty = '" + question.getDifficulty() + "' WHERE id = " + question.getId();
        return databaseHandler.executeUpdate(query) > 0;
    }

    public boolean deleteQuestion(int id) {
        String query = "DELETE FROM question WHERE id = " + id;
        return databaseHandler.executeUpdate(query) > 0;
    }

    private List<Question> createQuestionListFromResultSet(ResultSet resultSet) {
        List<Question> questionList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String statement = resultSet.getString("statement");
                String option1 = resultSet.getString("option1");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");
                int correctOption = resultSet.getInt("correctOption");
                String category = resultSet.getString("category");
                String difficulty = resultSet.getString("difficulty");
                Question question = new Question(id, statement, option1, option2, option3, option4, correctOption, category, difficulty);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
