package BLL;

import BE.Event;
import BE.User;
import DAL.DB.EventCoordDAO_DB;
import DAL.IEventCoordDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EventCoordManager {

    private IEventCoordDAO eventDAO;

    public EventCoordManager(){
        eventDAO = new EventCoordDAO_DB();
    }

    public Event createEvent(String name, String date, String time, String notes, String location) {
       return eventDAO.createEvent(name,date,time,notes, location);
    }

    public List<Event> getAllEvent()  {
        return eventDAO.getAllEvents();
    }

    public List<User> getAllUsers() {
        return eventDAO.getAllUsers();
    }

    public void updateEvent(Event selectedEvent) {
        eventDAO.updateEvent(selectedEvent);
    }

    public void deleteEvent(Event selectedEvent) {
        eventDAO.deleteEvent(selectedEvent.getId(),selectedEvent.getEventName());
    }

    public List<Event> getLoggedInUserEvent(User user) throws SQLException {
        return eventDAO.getLoggedInUserEvent(user);
    }

//    public Event updateEvent(String EventName, String EventDate, String EventTime, String EventNotes, String EventLocation) {
//        return eventDAO.updateEvent();
//    }

}
