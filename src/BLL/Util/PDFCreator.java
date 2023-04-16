package BLL.Util;


import BE.Event;
import BE.Ticket;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class PDFCreator {

    public void printTicket(Ticket selectedTicket, Event selectedEvent) {
        try {

            PdfWriter pdfWriter = new PdfWriter("Ticket.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            pdfDocument.addNewPage();

            Paragraph paragraph = new Paragraph();

            paragraph.add(selectedTicket.getTicketType() + " ticket for the EASV Event: " + " " + selectedEvent.getEventName() + "\r\n" + "\r\n");
            paragraph.add("Issued To: " + selectedTicket.getName() + " " + selectedTicket.getLastName() + "\r\n" + "With Email: " + selectedTicket.getEmail() + "\r\n" + "\r\n");
            paragraph.add("Event information:" + "\r\n" + "\r\n" + "Event Date: " + selectedEvent.getEventDate() + "\r\n" + "\r\n" + "Event Time: " + selectedEvent.getEventTime()
                    + "\r\n" + "\r\n" + "Event Location: " + selectedEvent.getEventLocation() + "\r\n" + "\r\n" + "Event notes: " + selectedEvent.getEventNotes() + "\r\n" + "________________________________________________________________________");

            ImageData imageData = ImageDataFactory.create("QRCODE.png");
            Image image = new Image(imageData);
            paragraph.add(image);
            document.add(paragraph);

            document.close();


            File file = new File("Ticket.pdf");

            Desktop.getDesktop().open(file);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
