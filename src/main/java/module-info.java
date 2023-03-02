module com.quizgame.quizgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.quizgame.quizgame to javafx.fxml;
    exports com.quizgame.quizgame;
}