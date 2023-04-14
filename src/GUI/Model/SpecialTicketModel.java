package GUI.Model;

import BE.SpecialTicket;
import BE.Ticket;
import BLL.SpecialTicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpecialTicketModel {

    SpecialTicketManager specialTicketManager;

    private ObservableList<SpecialTicket> specialTicketsToBeViewed;

    public SpecialTicketModel() {
        specialTicketManager = new SpecialTicketManager();

        specialTicketsToBeViewed = FXCollections.observableArrayList();

    }

    public ObservableList<SpecialTicket> getSpecialTicketsToBeViewed(){
        specialTicketsToBeViewed.clear();
        specialTicketsToBeViewed.addAll(specialTicketManager.getAllSpecialTicket());
        return specialTicketsToBeViewed;
    }

    public void createSpecialTicket(String ticketType, int ticketAvailable){
        specialTicketManager.createSpecialTicket(ticketType,ticketAvailable);
    }

}
