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
            //SQL string that gets all the information from the SpecialTicket Tabel
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


    @Override
    public void deleteSpecialTicket(SpecialTicket selectedSpecialTicket) {
        try (Connection conn = databaseConnector.getConnection()) {
            //SQL string that makes it possible to delete SpecialTickets
            String sql = "DELETE FROM dbo.SpecialTickets WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, selectedSpecialTicket.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public SpecialTicket createSpecielTicket(String generatedQR, String ticketType, int ticketAvailable) throws SQLException {
        try (Connection conn = databaseConnector.getConnection()) {
            //SQL string that makes it possible to create our special tickets
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


        }
    }
}
