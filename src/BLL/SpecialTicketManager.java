package BLL;

import BE.SpecialTicket;
import BLL.Util.PDFCreator;
import BLL.Util.QRCodeStringGenerator;
import DAL.DB.SpecialTicketDAO_DB;
import DAL.ISpecialTicketDAO;

import java.io.FileNotFoundException;
import java.util.List;

public class SpecialTicketManager {
    private ISpecialTicketDAO specialTicketDAO;

    public SpecialTicketManager() {
        specialTicketDAO = new SpecialTicketDAO_DB();
    }



    public List<SpecialTicket> getAllSpecialTicket(int eventID){
        return specialTicketDAO.getAllSpecialTickets(eventID);
    }

    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket){
        specialTicketDAO.deleteSpecialTicket(selectedSpecialTicket);
    }


    public void createSpecialTicket(String ticketType, int ticketAvailable) {
        QRCodeStringGenerator qrCodeStringGenerator = new QRCodeStringGenerator();
        String generatedQR = qrCodeStringGenerator.getGeneratedString();
        specialTicketDAO.createSpecielTicket(generatedQR, ticketType, ticketAvailable);
    }

    public void printSpecialTicket(SpecialTicket selectedSpecialTicket) throws FileNotFoundException {
        PDFCreator pdfCreator = new PDFCreator();

        pdfCreator.printSpecialTicket(selectedSpecialTicket);
    }
}
