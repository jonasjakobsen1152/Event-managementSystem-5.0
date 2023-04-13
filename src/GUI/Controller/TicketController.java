package GUI.Controller;

import BE.Customer;
import BE.Ticket;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TicketController implements Initializable {

    public TableView<Ticket> tblTicket;
    @FXML
    private TableColumn CLMID;
    @FXML
    private TableColumn CLMName;
    @FXML
    private TableColumn CLMLastName;
    @FXML
    private TableColumn CLMEmail;
    @FXML
    private TableColumn CLMType;
    @FXML
    private TableColumn CLMQR;

    private TicketModel ticketModel;

    private Ticket selectedTicket;


    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();
        showTickets();

        tblTicket.setOnMouseClicked(event -> {
            selectedTicket = tblTicket.getSelectionModel().getSelectedItem();
        });
    }

    private void showTickets(){
        try {
            tblTicket.setItems(ticketModel.getTicketsToBeViewed());

            CLMID.setCellValueFactory(new PropertyValueFactory<Ticket,String>("id"));
            CLMName.setCellValueFactory(new PropertyValueFactory<Ticket,String>("Name"));
            CLMLastName.setCellValueFactory(new PropertyValueFactory<Ticket,String>("LastName"));
            CLMEmail.setCellValueFactory(new PropertyValueFactory<Ticket,String>("Email"));
            CLMType.setCellValueFactory(new PropertyValueFactory<Ticket,String>("TicketType"));
            CLMQR.setCellValueFactory(new PropertyValueFactory<Ticket,String>("Qr"));
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
        if(selectedTicket == null){
        alertUser("Please select a ticket to delete");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedTicket.getName().concat("?"));
            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK){
                ticketModel.deleteTicket(selectedTicket);
            }
        }
        ticketModel.replaceOldTicketList();
    }


    private void alertUser(String error){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select a Customer");
        alert.setHeaderText(error);
        alert.show();
    }

    private void alertConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete: " + selectedTicket.getName().concat("?"));
        Optional<ButtonType> action = alert.showAndWait();
    }
}
