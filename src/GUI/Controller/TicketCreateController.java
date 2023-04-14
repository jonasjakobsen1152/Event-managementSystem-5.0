package GUI.Controller;

import GUI.Model.EventCoordModel;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TicketCreateController {
    @FXML
    public MFXButton btnCreateTicket;
    @FXML
    public MFXTextField txtEmail;
    @FXML
    public MFXTextField txtLastName;
    @FXML
    public MFXTextField txtName;
    @FXML
    public MFXTextField txtType;

    private TicketModel ticketModel;

    private EventCoordModel eventCoordModel;

    public TicketCreateController() throws Exception {
        ticketModel = TicketModel.getInstance();
        eventCoordModel = EventCoordModel.getInstance();
    }

    public void handleCreateTicket(ActionEvent actionEvent) {
        int event = eventCoordModel.getSelectedEvent().getId();
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String ticketType = txtType.getText();
        String QR = "";
        int available = 1;

        // Creates the tickets for the specific event
    ticketModel.createTicket(event,name,lastName,email,ticketType,QR,available);

    // Refreshes the list of tickets visible to the event coordinator by what event he is managing.
    ticketModel.getTicketsToBeViewed(eventCoordModel.getSelectedEvent().getId());
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
