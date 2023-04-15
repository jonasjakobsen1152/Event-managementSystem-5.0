package BLL.Util;


import BE.Event;
import BE.Ticket;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;

import java.awt.*;
import java.io.*;


public class PDFCreator {

    public void printTicket(Ticket selectedTicket, Event selectedEvent) {
        try {

            PdfWriter pdfWriter = new PdfWriter("Ticket.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            Paragraph paragraph = new Paragraph();

            paragraph.add(selectedTicket.getTicketType() + " ticket for the EASV Event: " + " " + selectedEvent.getEventName() + "\r\n" + "\r\n");
            paragraph.add("Issued To: " + selectedTicket.getName() + " " + selectedTicket.getLastName() + "\r\n" + "With Email: " + selectedTicket.getEmail() + "\r\n" + "\r\n");
            paragraph.add("Event information:" + "\r\n" + "\r\n" + "Event Date: " + selectedEvent.getEventDate() + "\r\n" + "\r\n" + "Event Time: " + selectedEvent.getEventTime()
                    + "\r\n" + "\r\n" + "Event Location: " + selectedEvent.getEventLocation() + "\r\n" + "\r\n" + "Event notes: " + selectedEvent.getEventNotes());

            document.add(paragraph);

            BarcodeQRCode qrCode = new BarcodeQRCode();
            qrCode.setCode(selectedTicket.getQr());
            Image image = qrCode.createAwtImage(java.awt.Color.white, java.awt.Color.BLACK);
            document.add((IBlockElement) image);


            document.close();


            File file = new File("Ticket.pdf");

            Desktop.getDesktop().open(file);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
