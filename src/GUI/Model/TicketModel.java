package GUI.Model;


import BE.Customer;
import BLL.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TicketModel {
    private static TicketModel instance;

    TicketManager ticketManager;

    private ObservableList<Customer> customersToBeViewed;
    private TicketModel() {
        ticketManager = new TicketManager();

        customersToBeViewed = FXCollections.observableArrayList();
        customersToBeViewed.addAll(ticketManager.getAllCustomers());
    }

    public static TicketModel getInstance() {
        if(instance == null){
            instance = new TicketModel();
        }
        return instance;
    }

    public void replaceOldCustomerList(){
        customersToBeViewed.clear();
        customersToBeViewed.addAll(ticketManager.getAllCustomers());
    }

    public ObservableList<Customer> getCustomersToBeViewed(){
        return customersToBeViewed;
    }


    public void createCustomer(String name, String lastName, String email) {
        ticketManager.createCustomer(name,lastName,email);
    }

    public void deleteCustomer(Customer selectedCustomer) {
        ticketManager.deleteCustomer(selectedCustomer);
    }
}
