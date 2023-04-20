package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import BLL.UsersInEventManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UsersInEventModel {
    UsersInEventManager usersInEventManager;

    private ObservableList<User> usersInEventToBeViewed;

    public UsersInEventModel(){
        usersInEventManager = new UsersInEventManager();
        usersInEventToBeViewed = FXCollections.observableArrayList();
    }



    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        usersInEventManager.addEventCoordinatorToEvent(selectedEvent, selectedUser);

        showlist(selectedEvent.getId());

    }

    public ObservableList<User> getUsersInEventToBeViewed() {
        return usersInEventToBeViewed;
    }
    

    public void showlist(int selectedEventId) throws SQLServerException {
        usersInEventToBeViewed.clear();
        usersInEventToBeViewed.addAll(usersInEventManager.getUsersInEvent(selectedEventId));
    }

    public List<User> getAllUsersInEvent(int eventId) throws SQLServerException {
        return usersInEventManager.getUsersInEvent(eventId);
    }
    public void removeUserFromEvent(User selectedUser,Event selectedEvent,int selectedUserInEventId) throws SQLServerException {
        usersInEventManager.removeUserFromEvent(selectedUser,selectedEvent,selectedUserInEventId);
        showlist(selectedEvent.getId());
    }
    public int getUserEventId(int userId, int eventId){
        return usersInEventManager.getUserEventId(userId,eventId);


    }
}
