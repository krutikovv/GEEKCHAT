package client;

import db.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SettingsController {
    @FXML
    private TextField nickField;
    @FXML
    private Button btnCancel;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        nickField.appendText(controller.getNickname());
        nickField.setFocusTraversable(false);
    }

    public void saveSettings(ActionEvent actionEvent) {
        controller.saveSettings(controller.getLogin(), nickField.getText());
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
