package DAL.DB;

import BE.Customer;
import BE.Ticket;
import DAL.ITicketDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;

public class TicketDAO_DB implements ITicketDAO {

    MyDatabaseConnector databaseConnector;

    public TicketDAO_DB(){
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> allTickets = new ArrayList<>();

        try(Connection conn = databaseConnector.getConnection();
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.TicketCustomer";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID");
                int eventID = rs.getInt("EventID");
                String name = rs.getString("Name");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String ticketType = rs.getString("TicketType");
                String qrCode = rs.getString("QRCode");

                Ticket ticket = new Ticket(id,eventID,name,lastName,email,ticketType,qrCode);
                allTickets.add(ticket);
            }
            return allTickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer createCustomer(String name, String lastName, String email) {
        try (Connection conn = databaseConnector.getConnection()){
            String sql = "Insert into dbo.Customers (Name,LastName,Email) VALUES (?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,name);
            stmt.setString(2,lastName);
            stmt.setString(3,email);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()){
                id = rs.getInt(1);
            }

            Customer customer = new Customer(id,name,lastName,email);
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error: Could not create customer");
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
