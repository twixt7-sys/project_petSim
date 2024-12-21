module dir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens dir to javafx.fxml;
    opens dir.utilities to javafx.fxml;
    opens dir.controllers to javafx.fxml;
    exports dir;
}
