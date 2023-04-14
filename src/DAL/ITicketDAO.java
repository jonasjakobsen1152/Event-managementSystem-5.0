package DAL;

import BE.Customer;
import BE.Ticket;

import java.util.ArrayList;

public interface ITicketDAO {

    ArrayList<Ticket> getAllTickets(int eventID);

    void deleteTicket(Ticket selectedTicket);

    Ticket createTicket(int event, String name, String lastName, String email, String ticketType, String newQR, int available);
}
