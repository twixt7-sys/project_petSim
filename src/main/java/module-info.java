module dir {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.media;
    requires transitive javafx.graphics;

    opens dir to javafx.fxml;
    opens dir.controllers to javafx.fxml;
    opens dir.utilities to javafx.fxml;
    exports dir;
    exports dir.utilities;
    exports dir.controllers;
}
