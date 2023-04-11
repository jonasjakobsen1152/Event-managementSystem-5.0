package BLL;

import BE.Customer;
import BE.Event;
import DAL.DB.TicketDAO_DB;
import DAL.ILoginDAO;
import DAL.ITicketDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TicketManager {

    private ITicketDAO iTicketDAO;
    private ArrayList<Customer> allCustomers;

    public TicketManager(){
        iTicketDAO = new TicketDAO_DB();
        allCustomers = new ArrayList<>();
    }

    public List<Customer> getAllCustomers() {
        return iTicketDAO.getAllCustomers();
    }

}
