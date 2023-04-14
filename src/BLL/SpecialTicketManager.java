package BLL;

import BE.SpecialTicket;
import BLL.Util.QRCodeStringGenerator;
import DAL.DB.SpecialTicketDAO_DB;
import DAL.ISpecialTicketDAO;

import java.util.List;

public class SpecialTicketManager {
    private ISpecialTicketDAO specialTicketDAO;

    public SpecialTicketManager() {
        specialTicketDAO = new SpecialTicketDAO_DB();
    }



    public List<SpecialTicket> getAllSpecialTicket(){
        return specialTicketDAO.getAllSpecialTickets();
    }

    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket){
        specialTicketDAO.deleteSpecialTicket(selectedSpecialTicket);
    }


    public void createSpecialTicket(String ticketType, int ticketAvailable) {
        QRCodeStringGenerator qrCodeStringGenerator = new QRCodeStringGenerator();
        String generatedQR = qrCodeStringGenerator.getGeneratedString();
        specialTicketDAO.createSpecielTicket(ticketType, generatedQR, ticketAvailable);
    }
}
