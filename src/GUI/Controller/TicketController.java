package GUI.Controller;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import GUI.Model.EventCoordModel;
import GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML
    private TableView<Ticket> tblTicket;
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

    private EventCoordModel eventCoordModel;

    private int eventID;


    public void initialize(URL location, ResourceBundle resources) {

        try {
            eventCoordModel = EventCoordModel.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        eventID = eventCoordModel.getSelectedEvent().getId();

        ticketModel = TicketModel.getInstance();
        showTickets();

        tblTicket.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ticket>() {
            @Override
            public void changed(ObservableValue<? extends Ticket> observable, Ticket oldValue, Ticket newValue) {
                selectedTicket = tblTicket.getSelectionModel().getSelectedItem();
            }
        });
    }

    private void showTickets() {
        try {
            tblTicket.setItems(ticketModel.getTicketsToBeViewed(eventID));

            CLMID.setCellValueFactory(new PropertyValueFactory<Ticket, String>("id"));
            CLMName.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Name"));
            CLMLastName.setCellValueFactory(new PropertyValueFactory<Ticket, String>("LastName"));
            CLMEmail.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Email"));
            CLMType.setCellValueFactory(new PropertyValueFactory<Ticket, String>("TicketType"));
            CLMQR.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Qr"));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception here
        }
    }


    public void handleCreateTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/TicketsCreate.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner((((Node) actionEvent.getSource()).getScene().getWindow()));
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();
    }

    public void handleDeleteTicket(ActionEvent actionEvent) {
        if (selectedTicket == null) {
            alertUser("Please select a ticket to delete");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedTicket.getName().concat("?"));
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ticketModel.deleteTicket(selectedTicket);
            }
        }
        ticketModel.getTicketsToBeViewed(eventID);
    }


    private void alertUser(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select a Customer");
        alert.setHeaderText(error);
        alert.show();
    }

    public void handlePrintTicket(ActionEvent actionEvent) {
        try {
            if (selectedTicket == null) {
                JOptionPane.showMessageDialog(null, "Please select a ticket to print.", "Ticket Printer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ticketModel.printTicket(selectedTicket, eventCoordModel.getSelectedEvent());
            }
        } catch (IOException e) {
            alertUser("Could not print Ticket");
        }
    }
}
