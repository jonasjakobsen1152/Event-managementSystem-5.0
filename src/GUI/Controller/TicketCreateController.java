package GUI.Controller;

import GUI.Model.EventCoordModel;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TicketCreateController {
    public MFXButton btnCreateCustomer;
    public MFXTextField txtEmail;
    public MFXTextField txtLastName;
    public MFXTextField txtName;
    public MFXTextField txtType;

    private TicketModel ticketModel;

    private EventCoordModel eventCoordModel;

    public TicketCreateController() throws Exception {
        ticketModel = TicketModel.getInstance();
        eventCoordModel = new EventCoordModel();
    }

    public void handleCreateCustomer(ActionEvent actionEvent) {
        int event = eventCoordModel.getSelectedEvent().getId();
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String ticketType = txtType.getText();
        String QR = "";

    ticketModel.createTicket(event,name,lastName,email,ticketType,QR);
    ticketModel.replaceOldTicketList();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
