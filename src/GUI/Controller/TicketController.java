package GUI.Controller;

import GUI.Model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML
    private TableView tblCustomer;
    @FXML
    private TableColumn CLMName;
    @FXML
    private TableColumn CLMLastName;
    @FXML
    private TableColumn CLMEmail;
    @FXML
    private TableView TBLTicket;
    @FXML
    private TableColumn CLMTicketNumber;
    @FXML
    private TableColumn CLMTicketQR;
    @FXML
    private TicketModel ticketModel;

    public void initialize(URL location, ResourceBundle resources) {


    }


    public void handleCreateCustomer(ActionEvent actionEvent) {
    
    }

    public void handleDeleteCustomer(ActionEvent actionEvent) {
    }

    public void handleCreateTicket(ActionEvent actionEvent) {
    }

    public void handleDeleteTicket(ActionEvent actionEvent) {
    }
}
