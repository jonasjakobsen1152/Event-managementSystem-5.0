package BLL;

import BE.Event;
import DAL.DB.EventCoordDAO_DB;
import DAL.IEventCoordDAO;

import java.util.Date;
import java.util.List;

public class EventCoordManager {

    private IEventCoordDAO eventDAO;

    public EventCoordManager(){
        eventDAO = new EventCoordDAO_DB();
    }

    public Event createEvent(String name, String date, String time, String location, String notes) {
       return eventDAO.createEvent(name,date,time,location,notes);
    }

    public List<Event> getAllEvent() throws Exception {
        return eventDAO.getAllEvents();
    }

    public void updateEvent(Event selectedEvent) {
        eventDAO.updateEvent(selectedEvent);
    }

//    public Event updateEvent(String EventName, String EventDate, String EventTime, String EventNotes, String EventLocation) {
//        return eventDAO.updateEvent();
//    }

}
