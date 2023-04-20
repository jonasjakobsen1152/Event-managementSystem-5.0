package DAL;

import BE.SpecialTicket;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISpecialTicketDAO {
    ArrayList<SpecialTicket> getAllSpecialTickets();
    void deleteSpecialTicket(SpecialTicket selectedSpecialTicket);

    SpecialTicket createSpecielTicket(String ticketType, String generatedQR, int ticketAvailable) throws SQLException;

}
