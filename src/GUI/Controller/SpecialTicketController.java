package GUI.Controller;

import BE.SpecialTicket;
import GUI.Model.SpecialTicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SpecialTicketController {
    @FXML
    private TableView<SpecialTicket> tblAllSpecialTickets;
    @FXML
    private TableColumn clmSpecialTickets;
    @FXML
    private MFXButton btnCreateSpecialTicket;
    @FXML
    private MFXTextField txtDescribeTicket;
    @FXML
    private MFXButton btnPrintSpecialTicket;
    @FXML
    private SpecialTicketModel specialTicketModel;
    private ObservableList<SpecialTicket> allSpecialTickets;

    public SpecialTicketController() {

        specialTicketModel = new SpecialTicketModel();
        showSpecialTicket();
    }
    public void showSpecialTicket(){
        allSpecialTickets = specialTicketModel.getSpecialTicketsToBeViewed();
        clmSpecialTickets.setCellValueFactory(new PropertyValueFactory<SpecialTicket,String>("TicketType"));
        tblAllSpecialTickets.setItems(allSpecialTickets);

    }

    public void handlePrintSpecialTicket(ActionEvent actionEvent) {

    }

    public void handleCreateSpecialTicket(ActionEvent actionEvent) {
        String describe = txtDescribeTicket.getText();
        specialTicketModel.createSpecialTicket(describe,1);
        //showSpecialTicket();
        allSpecialTickets = specialTicketModel.getSpecialTicketsToBeViewed();

    }
}
