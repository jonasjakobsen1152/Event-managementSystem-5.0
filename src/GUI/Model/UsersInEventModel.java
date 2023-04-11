package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import BLL.UsersInEventManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class UsersInEventModel {
    UsersInEventManager usersInEventManager;

    public UsersInEventModel(){
        usersInEventManager = new UsersInEventManager();
    }



    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        usersInEventManager.addEventCoordinatorToEvent(selectedEvent, selectedUser);

    }
}
