package GUI.Controller;

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

    public TicketCreateController(){
        ticketModel = TicketModel.getInstance();
    }

    public void handleCreateCustomer(ActionEvent actionEvent) {
    String name = txtName.getText();
    String lastName = txtLastName.getText();
    String email = txtEmail.getText();
    String ticketType = txtType.getText();
    String QR = "";

    ticketModel.createTicket(name,lastName,email,ticketType,QR);
    ticketModel.replaceOldTicketList();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
