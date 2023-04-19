package DAL;

import BE.SpecialTicket;

import java.util.ArrayList;

public interface ISpecialTicketDAO {
    ArrayList<SpecialTicket> getAllSpecialTickets(int eventID);
    void deleteSpecialTicket(SpecialTicket selectedSpecialTicket);

    SpecialTicket createSpecielTicket(String ticketType, String generatedQR, int ticketAvailable);

}
