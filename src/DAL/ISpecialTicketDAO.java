package DAL;

import BE.SpecialTicket;
import BE.Ticket;

import java.util.ArrayList;

public interface ISpecialTicketDAO {
    ArrayList<SpecialTicket> getAllSpecialTickets();
    void deleteSpecialTicket(SpecialTicket selectedSpecialTicket);
    SpecialTicket createSpecielTicket(int ID, String QR, String TicketType, int TicketAvailable);

}
