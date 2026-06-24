module com.example.tareaguiajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tareaguiajavafx to javafx.fxml;
    exports com.example.tareaguiajavafx;
    exports com.example.tareaguiajavafx.strategy;
    opens com.example.tareaguiajavafx.strategy to javafx.fxml;
}