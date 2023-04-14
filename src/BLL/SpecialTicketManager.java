package BLL;

import BE.SpecialTicket;
import DAL.DB.SpecialTicketDAO_DB;
import DAL.ISpecialTicketDAO;

import java.util.List;

public class SpecialTicketManager {
    private ISpecialTicketDAO specialTicketDAO;

    public SpecialTicketManager() {
        specialTicketDAO = new SpecialTicketDAO_DB();
    }

    public SpecialTicket createSpecialTicket(int id, String qr, String ticketType, int ticketAvailable){
        return specialTicketDAO.createSpecielTicket(id,qr,ticketType,ticketAvailable);
    }

    public List<SpecialTicket> getAllSpecialTicket(){
        return specialTicketDAO.getAllSpecialTickets();
    }

    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket){
        specialTicketDAO.deleteSpecialTicket(selectedSpecialTicket);
    }


}
