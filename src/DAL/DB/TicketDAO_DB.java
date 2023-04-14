package DAL.DB;

import BE.Ticket;
import DAL.ITicketDAO;

import java.sql.*;
import java.util.ArrayList;

public class TicketDAO_DB implements ITicketDAO {

    MyDatabaseConnector databaseConnector;

    public TicketDAO_DB(){
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public ArrayList<Ticket> getAllTickets(int eventID) {
        ArrayList<Ticket> allTickets = new ArrayList<>();

        try(Connection conn = databaseConnector.getConnection();
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.TicketCustomer WHERE EventID = "+eventID+";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID");
                int eventIDFromSQL = rs.getInt("EventID");
                String name = rs.getString("Name");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String ticketType = rs.getString("TicketType");
                String qrCode = rs.getString("QRCode");
                int available = 1;

                Ticket ticket = new Ticket(id,eventIDFromSQL,name,lastName,email,ticketType,qrCode, available);
                allTickets.add(ticket);
            }
            return allTickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Ticket createTicket(int event, String name, String lastName, String email, String ticketType, String newQR, int available) {
        try (Connection conn = databaseConnector.getConnection()){
            String sql = "Insert into dbo.TicketCustomer (EventID,Name,LastName,Email,TicketType,QRCode,TicketAvailable) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,event);
            stmt.setString(2,name);
            stmt.setString(3,lastName);
            stmt.setString(4,email);
            stmt.setString(5,ticketType);
            stmt.setString(6,newQR);
            stmt.setInt(7,available);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()){
                id = rs.getInt(1);
            }

            Ticket ticket = new Ticket(id,event,name,lastName,email,ticketType,newQR,available);
            return ticket;

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error: Could not create ticket");
        }


    }

    @Override
    public void deleteTicket(Ticket selectedTicket) {
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "DELETE FROM dbo.TicketCustomer WHERE ID = ? AND Email = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,selectedTicket.getId());
            stmt.setString(2,selectedTicket.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete ticket",e);
        }
    }
}
