package GUI.Model;


import BE.Customer;
import BLL.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public ObservableList<Customer> getCustomersToBeViewed(){
        return customersToBeViewed;
    }


}
