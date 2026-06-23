module com.example.tareaguiajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tareaguiajavafx to javafx.fxml;
    exports com.example.tareaguiajavafx;
}