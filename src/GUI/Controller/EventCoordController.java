package GUI.Controller;

import GUI.Model.EventCoordModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;

public class EventCoordController {
    public MFXButton btnCreateNewEvent;
    public MFXButton btnUpdateEvent;
    public MFXButton btnViewTickets;
    public MFXButton btnDeleteTickets;
    private EventCoordModel model;
    
    public void setModel(EventCoordModel model) {
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
