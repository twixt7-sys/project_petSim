module dir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens dir to javafx.fxml;
    opens dir.controllers to javafx.fxml;
    opens dir.utilities to javafx.fxml;
    exports dir;
    exports dir.utilities;
    exports dir.controllers;
}
