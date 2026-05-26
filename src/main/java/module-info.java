module com.franco.sistemapuntoventa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.franco.sistemapuntoventa to javafx.fxml;
    exports com.franco.sistemapuntoventa;
    exports com.franco.sistemapuntoventa.application;
    opens com.franco.sistemapuntoventa.application to javafx.fxml;
    exports com.franco.sistemapuntoventa.controller;
    opens com.franco.sistemapuntoventa.controller to javafx.fxml;
}