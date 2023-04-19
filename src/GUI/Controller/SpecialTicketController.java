package GUI.Controller;

import BE.SpecialTicket;
import GUI.Model.EventCoordModel;
import GUI.Model.SpecialTicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SpecialTicketController implements Initializable {
    @FXML
    private TableView<SpecialTicket> tblAllSpecialTickets;
    @FXML
    private TableColumn clmSpecialTickets;
    @FXML
    private MFXButton btnCreateSpecialTicket;
    @FXML
    private MFXTextField txtDescribeTicket;
    @FXML
    private SpecialTicketModel specialTicketModel;
    private ObservableList<SpecialTicket> allSpecialTickets;

    private SpecialTicket selectedSpecialTicket;
    private EventCoordModel eventCoordModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        specialTicketModel = new SpecialTicketModel();
        showSpecialTicket();
        try {
            eventCoordModel = EventCoordModel.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tblAllSpecialTickets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SpecialTicket>() {
            @Override
            public void changed(ObservableValue<? extends SpecialTicket> observable, SpecialTicket oldValue, SpecialTicket newValue) {
                selectedSpecialTicket = tblAllSpecialTickets.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void showSpecialTicket(){
        tblAllSpecialTickets.setItems(specialTicketModel.getSpecialTicketsToBeViewed());

        tblAllSpecialTickets.setItems(specialTicketModel.getSpecialTicketsToBeViewed());
        allSpecialTickets = specialTicketModel.getSpecialTicketsToBeViewed();
        clmSpecialTickets.setCellValueFactory(new PropertyValueFactory<SpecialTicket,String>("TicketType"));
        tblAllSpecialTickets.setItems(allSpecialTickets);

    }

    public void handlePrintSpecialTicket(ActionEvent actionEvent) {
        selectedSpecialTicket = tblAllSpecialTickets.getSelectionModel().getSelectedItem();
        try {
            specialTicketModel.printSpecialTicket(selectedSpecialTicket);
        } catch (IOException e) {
           alertUser("Could not print ticket");
        }
    }

    public void handleCreateSpecialTicket(ActionEvent actionEvent) {
        String describe = txtDescribeTicket.getText();
        specialTicketModel.createSpecialTicket(describe,1);
        allSpecialTickets = specialTicketModel.getSpecialTicketsToBeViewed();

    }
    private void alertUser(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Select a special ticket");
        alert.setHeaderText(error);
        alert.show();
    }

    public void handleDeleteSpecialTicket(ActionEvent actionEvent) {
        if (selectedSpecialTicket == null) {
            alertUser("Please select a special ticket to delete");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                specialTicketModel.deleteSpecialTicket(selectedSpecialTicket);
            }
        }
        specialTicketModel.getSpecialTicketsToBeViewed();
    }
}
