package BLL;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import DAL.DB.TicketDAO_DB;
import DAL.ILoginDAO;
import DAL.ITicketDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TicketManager {

    private ITicketDAO iTicketDAO;


    public TicketManager(){
        iTicketDAO = new TicketDAO_DB();
    }


    public ArrayList<Ticket> getAllTickets(){
        return iTicketDAO.getAllTickets();
    }

    public void createCustomer(String name, String lastName, String email) {
        iTicketDAO.createCustomer(name,lastName,email);
    }
    public void deleteTicket(Ticket selectedTicket) {
        iTicketDAO.deleteTicket(selectedTicket);
    }

    public void createTicket(int event, String name, String lastName, String email, String ticketType, String qr) {
       // todo qr code
    }
}
