package BLL;

import BE.Event;
import BE.User;
import DAL.DB.UsersInEventDAO_DB;
import DAL.IUsersInEventDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class UsersInEventManager {

    IUsersInEventDAO usersInEventDAO;
    public UsersInEventManager(){
        usersInEventDAO = new UsersInEventDAO_DB();
    }
    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        usersInEventDAO.addEventCoordinatorToEvent(selectedEvent,selectedUser);
    }
    public List<User> getUsersInEvent(int selectedEventId) throws SQLServerException {
        return usersInEventDAO.getCoordinatorsInEvent(selectedEventId);
    }

    public void removeUserFromEvent(User selectedUser, Event selectedEvent, int selectedUserInEventId){
        usersInEventDAO.removeUserFromEvent(selectedUser,selectedEvent,selectedUserInEventId);
    }

    public int getUserEventId(int userId, int eventId){
        return usersInEventDAO.getUserEventId(userId,eventId);
    }
}
