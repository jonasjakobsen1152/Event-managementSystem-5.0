package GUI.Controller;

import GUI.Model.SpecialTicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SpecialTicketController {

    public TableColumn clmSpecialTickets;
    public TableView tblAllSpecialTickets;
    public MFXButton btnCreateSpecialTicket;
    public MFXTextField txtDescribeTicket;
    public MFXButton btnPrintSpecialTicket;

    public SpecialTicketController() {

    }

    public SpecialTicketModel specialTicketModel = new SpecialTicketModel();

    public void handlePrintSpecialTicket(ActionEvent actionEvent) {
    }

    public void handleCreateSpecialTicket(ActionEvent actionEvent) {
    }
}
