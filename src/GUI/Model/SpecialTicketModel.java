package GUI.Model;

import BE.SpecialTicket;
import BE.Ticket;
import BLL.SpecialTicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void printSpecialTicket(SpecialTicket selectedSpecialTicket) throws IOException {
        specialTicketManager.printSpecialTicket(selectedSpecialTicket);
    }

    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket) {
        specialTicketManager.deleteSpecialTicket(selectedSpecialTicket);
    }
}
