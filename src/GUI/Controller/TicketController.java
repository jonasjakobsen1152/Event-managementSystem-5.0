package GUI.Controller;

import BE.Customer;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML
    private TableView<Customer> tblCustomer;
    @FXML
    private TableColumn CLMID;
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

    private TicketModel ticketModel;


    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();
        showCustomers();
    }

    private void showCustomers(){
        try {
            tblCustomer.setItems(ticketModel.getCustomersToBeViewed());

            CLMID.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
            CLMName.setCellValueFactory(new PropertyValueFactory<Customer,String>("Name"));
            CLMLastName.setCellValueFactory(new PropertyValueFactory<Customer,String>("LastName"));
            CLMEmail.setCellValueFactory(new PropertyValueFactory<Customer,String>("Email"));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception here
        }
    }


    public void handleCreateCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/TicketsCreate.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner((((Node)actionEvent.getSource()).getScene().getWindow()));
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();
    }

    public void handleDeleteCustomer(ActionEvent actionEvent) {
    }

    public void handleCreateTicket(ActionEvent actionEvent) {
    }

    public void handleDeleteTicket(ActionEvent actionEvent) {
    }
}
