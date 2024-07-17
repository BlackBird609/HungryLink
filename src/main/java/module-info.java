module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.server to javafx.fxml;
    exports com.example.server;

    opens com.example.customer to javafx.fxml;
    exports com.example.customer;

    opens com.example.restaurant to javafx.fxml;
    exports com.example.restaurant;
}