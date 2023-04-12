package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import BLL.UsersInEventManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersInEventModel {
    UsersInEventManager usersInEventManager;

    private ObservableList<User> usersInEventToBeViewed;

    public UsersInEventModel(){
        usersInEventManager = new UsersInEventManager();
        usersInEventToBeViewed = FXCollections.observableArrayList();
    }



    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        usersInEventManager.addEventCoordinatorToEvent(selectedEvent, selectedUser);

        //showlist();

    }

    public ObservableList<User> getUsersInEventToBeViewed() {
        return usersInEventToBeViewed;
    }

    public void setUsersInEventToBeViewed(ObservableList<User> usersInEventToBeViewed) {
        this.usersInEventToBeViewed = usersInEventToBeViewed;
    }

    public void showlist(int selectedEventId) throws SQLServerException {
        usersInEventToBeViewed.clear();
        usersInEventToBeViewed.addAll(usersInEventManager.getUsersInEvent(selectedEventId));
    }
}
