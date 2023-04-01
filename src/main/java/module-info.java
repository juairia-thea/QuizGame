module com.quizgame.quizgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Include the JDBC driver

    opens com.quizgame.quizgame to javafx.fxml;
    exports com.quizgame.quizgame;
    exports com.quizgame.quizgame.Controllers;
    opens com.quizgame.quizgame.Controllers to javafx.fxml;
}