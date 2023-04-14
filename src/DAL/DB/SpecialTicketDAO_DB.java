package DAL.DB;

import BE.SpecialTicket;
import BE.Ticket;
import DAL.ISpecialTicketDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpecialTicketDAO_DB implements ISpecialTicketDAO {

    MyDatabaseConnector databaseConnector;

    public SpecialTicketDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public ArrayList<SpecialTicket> getAllSpecialTickets() {
        ArrayList<SpecialTicket> allSpecialTickets = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM SpecialTicket";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String qr = rs.getString("QR");
                String ticketType = rs.getString("TicketType");
                int ticketAvailable = rs.getInt("TicketAvailable");

                SpecialTicket specialTicket = new SpecialTicket(id, qr, ticketType, ticketAvailable);
                allSpecialTickets.add(specialTicket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSpecialTickets;
    }


    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket) {

    }

    @Override
    public SpecialTicket createSpecielTicket(int ID, String QR, String TicketType, int TicketAvailable) {
        return null;
    }


}
