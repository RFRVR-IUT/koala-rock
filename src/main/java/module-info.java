module start.structure.Client {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;


    opens start.structure.Client to javafx.fxml;
    exports start.structure.Client;
    exports start.structure.Controller;
    opens start.structure.Controller to javafx.fxml;
    exports start.structure.Model;
    opens start.structure.Model to javafx.fxml;
    exports start.structure.View;
    opens start.structure.View to javafx.fxml;
}