package DAL;

import BE.Event;
import BE.User;

import java.sql.SQLException;
import java.util.List;

public interface IEventCoordDAO {


    public void updateEvent(Event event);

    public void deleteEvent(int EventID, String EventName);

    Event createEvent(String name, String date, String time, String location, String notes);

    public List<User> getAllUsers();

    List<Event> getAllEvents() ;
    public List<Event> getLoggedInUserEvent(User user) throws SQLException;
}
