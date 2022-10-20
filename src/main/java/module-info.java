module start.structure {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;


    opens start.structure to javafx.fxml;
    exports start.structure;

    // to javafx.graphics
}