package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class AdminModel {
    AdminManager adminManager;
    private ObservableList<User> usersToBeViewed;
    private ObservableList<Event> eventsToBeViewed;

    public AdminModel() {
        adminManager = new AdminManager();
        //Making usersToBeViewed Observable
        usersToBeViewed = FXCollections.observableArrayList();
        usersToBeViewed.addAll(adminManager.getAllUsers());

        //Making eventsToBeViewed Observable
        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(adminManager.getAllEvent());
    }


    public void createCoord(String username, String password,String role) {
        adminManager.createCoord(username,password,role);
    }

    public void deleteCoord(User selectedUser) {
        adminManager.deleteCoord(selectedUser);
        usersToBeViewed.remove(selectedUser);
    }

    public void removeCoord(User selectedUser) {
        adminManager.removeCoord(selectedUser);
        usersToBeViewed.remove(selectedUser);
    }

    public ObservableList<User> getObservableUsers() {
        return usersToBeViewed;
    }

    public ObservableList<Event> getObservableEvents() {
        return eventsToBeViewed;
    }

    public void deleteEvent(Event selectedEvent) {
        adminManager.deleteEvent(selectedEvent);
        eventsToBeViewed.remove(selectedEvent);
    }
}
