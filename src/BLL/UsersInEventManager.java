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
}
