module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires lombok;

    exports com.example.view;

    opens com.example.view to javafx.fxml;
    opens com.example.controller to javafx.fxml;
}
