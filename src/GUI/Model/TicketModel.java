package GUI.Model;


import BE.Customer;
import BE.Ticket;
import BLL.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TicketModel {
    private static TicketModel instance;

    TicketManager ticketManager;

    private ObservableList<Ticket> ticketsToBeViewed;

    private TicketModel() {
        ticketManager = new TicketManager();

        ticketsToBeViewed = FXCollections.observableArrayList();
        ticketsToBeViewed.addAll(ticketManager.getAllTickets());
    }

    public static TicketModel getInstance() {
        if(instance == null){
            instance = new TicketModel();
        }
        return instance;
    }

    public void replaceOldTicketList(){
        ticketsToBeViewed.clear();
        ticketsToBeViewed.addAll(ticketManager.getAllTickets());
    }

    public ObservableList<Ticket> getTicketsToBeViewed() {
        return ticketsToBeViewed;
    }


    public void createCustomer(String name, String lastName, String email) {
        ticketManager.createCustomer(name,lastName,email);
    }

    public void deleteTicket(Ticket selectedTicket) {
        ticketManager.deleteTicket(selectedTicket);
    }

    public void createTicket(int event, String name, String lastName, String email, String ticketType, String qr) {
    ticketManager.createTicket(event,name,lastName,email,ticketType,qr);
    }

}
