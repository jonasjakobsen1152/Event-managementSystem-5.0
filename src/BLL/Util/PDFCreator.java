package BLL.Util;


import BE.Event;
import BE.SpecialTicket;
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
import java.io.*;


public class PDFCreator {

    public void printTicket(Ticket selectedTicket, Event selectedEvent) throws IOException {
            //Creates a pdf called Ticket.pdf if already created it overrides the already existing Ticket.pdf
            PdfWriter pdfWriter = new PdfWriter("Ticket.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            //Creates a document for writing
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            pdfDocument.addNewPage();
            //Creates a new paragraph that strings can be added to
            Paragraph paragraph;
            paragraph = addTextToParagraph(selectedTicket,selectedEvent);

            //Gets the QRCode image and puts it onto the pdf.
            ImageData imageData = ImageDataFactory.create("QRCODE.png");
            Image image = new Image(imageData);
            paragraph.add(image);
            document.add(paragraph);
            //Closing the document for writing
            document.close();

            //Creates a file out of the pdf so that it can be opnened by the users computer
            File file = new File("Ticket.pdf");
            Desktop.getDesktop().open(file);
    }

    private Paragraph addTextToParagraph(Ticket selectedTicket, Event selectedEvent) {
        //Creates a new paragraph that strings can be added to
        Paragraph paragraph = new Paragraph();
        //Adds strings to the paragraph
        paragraph.add(selectedTicket.getTicketType() + " ticket for the EASV Event: " + " ");
        paragraph.add(selectedEvent.getEventName() + "\r\n" + "\r\n");
        paragraph.add("Issued To: " + selectedTicket.getName() + " ");
        paragraph.add(selectedTicket.getLastName() + "\r\n");
        paragraph.add("With Email: " + selectedTicket.getEmail() + "\r\n" + "\r\n");

        paragraph.add("Event information:" + "\r\n" + "\r\n"); //Writing in the event information
        paragraph.add("Event Date: " + selectedEvent.getEventDate() + "\r\n" + "\r\n");
        paragraph.add("Event Time: " + selectedEvent.getEventTime() + "\r\n" + "\r\n");
        paragraph.add("Event Location: " + selectedEvent.getEventLocation() + "\r\n" + "\r\n"); // Spaces between the text on the pdf
        paragraph.add("Event notes: " + selectedEvent.getEventNotes() + "\r\n");
        paragraph.add("________________________________________________________________________"); // A "nice" custom bar for the pdf
        return paragraph;
    }

    public void printSpecialTicket(SpecialTicket selectedSpecialTicket) throws IOException {
        PdfWriter pdfWriter = new PdfWriter("SpecialTicket.pdf");
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        pdfDocument.addNewPage();
        //Creates a new paragraph that strings can be added to
        Paragraph paragraph = new Paragraph();
        //Adds strings to the paragraph
        paragraph.add("A Special Ticket for use at EASV: ");
        paragraph.add(selectedSpecialTicket.getTicketType() + "\r\n" + "\r\n");


        ImageData imageData = ImageDataFactory.create("QRCODE.png");
        Image image = new Image(imageData);
        paragraph.add(image);
        document.add(paragraph);

        document.close();


        File file = new File("SpecialTicket.pdf");

        Desktop.getDesktop().open(file);
    }
}
