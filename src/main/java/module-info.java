module com.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.demofx.entities to javafx.base;
    opens com.example.demofx to javafx.fxml;
    exports com.example.demofx;
}