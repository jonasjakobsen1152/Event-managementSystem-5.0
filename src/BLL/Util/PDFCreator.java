package BLL.Util;


import DAL.DB.MyDatabaseConnector;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PDFCreator {

    private void createPDF(){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));


            document.open();

            //Adding to the pdf
            Paragraph paragraph = new Paragraph("This is a test from jones");
            document.add(paragraph);

            document.close();

            File file = new File("Ticket.pdf");

            Desktop.getDesktop().open(file);




        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) throws SQLException {

        //MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        PDFCreator pdfCreator = new PDFCreator();

        pdfCreator.createPDF();

    }



}
