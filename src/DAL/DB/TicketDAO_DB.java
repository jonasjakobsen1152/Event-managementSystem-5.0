package DAL.DB;

import BE.Customer;
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
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try(Connection conn = databaseConnector.getConnection();
            Statement stmt = conn.createStatement()) {
            String sql = "Select * From dbo.Customers";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");

                Customer customer = new Customer(id,name,lastName,email);
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allCustomers;
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
    public void deleteCustomer(Customer selectedCustomer) {
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "DELETE FROM dbo.Customers WHERE ID = ? AND Email = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,selectedCustomer.getId());
            stmt.setString(2,selectedCustomer.getEmail());

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Could not delete event",e);
        }


    }


}
