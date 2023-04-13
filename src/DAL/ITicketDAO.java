package DAL;

import BE.Customer;
import BE.Ticket;

import java.util.ArrayList;

public interface ITicketDAO {

    ArrayList<Ticket> getAllTickets();

    Customer createCustomer(String name, String lastName, String email);

    void deleteTicket(Ticket selectedTicket);
}
