package DAL;

import BE.Customer;

import java.util.ArrayList;

public interface ITicketDAO {

    

    ArrayList<Customer> getAllCustomers();

    Customer createCustomer(String name, String lastName, String email);


    void deleteCustomer(Customer selectedCustomer);
}
