package BLL;

import BE.Ticket;
import DAL.DB.TicketDAO_DB;
import DAL.ITicketDAO;

import java.util.ArrayList;

public class TicketManager {

    private ITicketDAO iTicketDAO;


    public TicketManager(){
        iTicketDAO = new TicketDAO_DB();
    }

    public ArrayList<Ticket> getAllTickets(int eventID){
        return iTicketDAO.getAllTickets(eventID);
    }

    public void deleteTicket(Ticket selectedTicket) {
        iTicketDAO.deleteTicket(selectedTicket);
    }

    public void createTicket(int event, String name, String lastName, String email, String ticketType, String qr, int available) {

       // todo qr code
    }
}
