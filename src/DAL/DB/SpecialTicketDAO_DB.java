package DAL.DB;

import BE.SpecialTicket;
import DAL.ISpecialTicketDAO;

import java.sql.*;
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

            String sql = "SELECT * FROM SpecialTickets";

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
    public SpecialTicket createSpecielTicket(String generatedQR, String ticketType, int ticketAvailable) {
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "Insert into dbo.SpecialTickets (QR, TicketType, TicketAvailable) VALUES (?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, generatedQR);
            stmt.setString(2, ticketType);
            stmt.setInt(3, ticketAvailable);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            SpecialTicket specialTicket = new SpecialTicket(id, generatedQR, ticketType, ticketAvailable);
            return specialTicket;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
