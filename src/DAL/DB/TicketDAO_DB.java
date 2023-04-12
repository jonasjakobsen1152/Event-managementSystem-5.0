package DAL.DB;

import BE.Customer;
import DAL.ITicketDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


}
