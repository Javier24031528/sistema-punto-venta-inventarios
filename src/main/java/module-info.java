module com.franco.sistemapuntoventa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.franco.sistemapuntoventa.application to javafx.fxml;
    opens com.franco.sistemapuntoventa.controller to javafx.fxml;
    opens com.franco.sistemapuntoventa.model to javafx.base;

    exports com.franco.sistemapuntoventa.application;
    exports com.franco.sistemapuntoventa.controller;
}