package GUI.Model;


import BE.Event;
import BE.Ticket;
import BLL.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketModel {
    private static TicketModel instance;

    TicketManager ticketManager;

    private ObservableList<Ticket> ticketsToBeViewed;

    private TicketModel() {
        ticketManager = new TicketManager();

        ticketsToBeViewed = FXCollections.observableArrayList();
    }

    public static TicketModel getInstance() {
        if(instance == null){
            instance = new TicketModel();
        }
        return instance;
    }

//    public void replaceOldTicketList(){
//        ticketsToBeViewed.clear();
//        ticketsToBeViewed.addAll(ticketManager.getAllTickets());
//    }

    public ObservableList<Ticket> getTicketsToBeViewed(int eventID) {
        ticketsToBeViewed.clear();
        ticketsToBeViewed.addAll(ticketManager.getAllTickets(eventID));
        return ticketsToBeViewed;
    }

    public void deleteTicket(Ticket selectedTicket) {
        ticketManager.deleteTicket(selectedTicket);
    }

    public void createTicket(int event, String name, String lastName, String email, String ticketType, String qr, int available) {
    ticketManager.createTicket(event,name,lastName,email,ticketType,qr,available);
    }

    public void printTicket(Ticket selectedTicket, Event selectedEvent) {
        ticketManager.printTiket(selectedTicket,selectedEvent);
    }
}
