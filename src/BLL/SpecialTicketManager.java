package BLL;

import BE.SpecialTicket;
import BLL.Util.PDFCreator;
import BLL.Util.QRCodeStringGenerator;
import DAL.DB.SpecialTicketDAO_DB;
import DAL.ISpecialTicketDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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


    public void createSpecialTicket(String ticketType, int ticketAvailable) throws SQLException {
        QRCodeStringGenerator qrCodeStringGenerator = new QRCodeStringGenerator();
        String generatedQR = qrCodeStringGenerator.getGeneratedString();
        specialTicketDAO.createSpecielTicket(generatedQR, ticketType, ticketAvailable);
    }

    public void printSpecialTicket(SpecialTicket selectedSpecialTicket) throws IOException {
        PDFCreator pdfCreator = new PDFCreator();

        pdfCreator.printSpecialTicket(selectedSpecialTicket);
    }
}
