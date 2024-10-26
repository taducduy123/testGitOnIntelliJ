module com.example.traningcentermanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.dbutils;


    opens com.example.traningcentermanagement to javafx.fxml;


    opens com.example.traningcentermanagement.Controller to javafx.fxml;
    exports com.example.traningcentermanagement.Controller;

    exports com.example.traningcentermanagement;
}