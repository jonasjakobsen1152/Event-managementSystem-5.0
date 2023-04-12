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

    private TicketModel ticketModel;

    public TicketCreateController(){
        ticketModel = TicketModel.getInstance();
    }

    public void handleCreateCustomer(ActionEvent actionEvent) {
    String name = txtName.getText();
    String lastName = txtLastName.getText();
    String email = txtEmail.getText();

    ticketModel.createCustomer(name,lastName,email);
    ticketModel.replaceOldCustomerList();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
