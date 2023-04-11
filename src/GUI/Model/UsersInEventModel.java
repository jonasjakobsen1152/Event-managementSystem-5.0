package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import BLL.UsersInEventManager;

public class UsersInEventModel {
    UsersInEventManager usersInEventManager;

    public UsersInEventModel(){
        usersInEventManager = new UsersInEventManager();
    }



    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) {
        usersInEventManager.addEventCoordinatorToEvent(selectedEvent, selectedUser);

    }
}
