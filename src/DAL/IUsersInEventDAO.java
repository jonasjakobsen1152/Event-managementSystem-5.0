package DAL;

import BE.Event;
import BE.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface IUsersInEventDAO {

    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException;



}