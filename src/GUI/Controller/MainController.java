package GUI.Controller;

import GUI.Model.MainModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public MFXButton btnCreateNewEvent;
    public MFXButton btnUpdateEvent;
    public MFXButton btnViewTickets;
    public MFXButton btnDeleteTickets;
    private MainModel model;
    
    public void setModel(MainModel model) {
            this.model = model;

    }

    public void handleCreateNewEvent(ActionEvent actionEvent) {
    }

    public void handleUpdateEvent(ActionEvent actionEvent) {
    }

    public void handleViewTickets(ActionEvent actionEvent) {
    }

    public void handleDeleteTickets(ActionEvent actionEvent) {
    }
}
