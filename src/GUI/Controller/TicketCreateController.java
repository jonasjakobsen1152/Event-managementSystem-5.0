package GUI.Controller;

import GUI.Model.EventCoordModel;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String ticketType = txtType.getText();
        String QR = "";

        if(name.isEmpty() || lastName.isEmpty() || email.isEmpty() || ticketType.isEmpty() || QR.isEmpty()){
            alertUser("Text fields cannot be empty");
        }
        else{
            int event = eventCoordModel.getSelectedEvent().getId();
            int available = 1;

            // Creates the tickets for the specific event
            ticketModel.createTicket(event,name,lastName,email,ticketType,QR,available);

            // Refreshes the list of tickets visible to the event coordinator by what event he is managing.
            ticketModel.getTicketsToBeViewed(eventCoordModel.getSelectedEvent().getId());
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void alertUser(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select a special ticket");
        alert.setHeaderText(error);
        alert.show();
    }
}
