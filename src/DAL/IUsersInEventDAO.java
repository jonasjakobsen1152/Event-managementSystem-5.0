package DAL;

import BE.Event;
import BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface IUsersInEventDAO {

    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException;
    List<User> getCoordinatorsInEvent(int selectedEventId) throws SQLServerException;

    public void removeUserFromEvent(User selectedUser, Event selectedEvent, int selectedUserInEvent);
    public int getUserEventId(int userId, int eventId);



}
