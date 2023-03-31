package BLL;

import BE.Event;
import DAL.DB.EventCoordDAO_DB;
import DAL.IEventCoordDAO;

import java.util.Date;

public class EventCoordManager {

    private IEventCoordDAO eventDAO;

    public EventCoordManager(){
        eventDAO = new EventCoordDAO_DB();
    }

    public Event createEvent(String name, String date, String time, String location, String notes) {
       return eventDAO.createEvent(name,date,time,location,notes);
    }

//    public Event updateEvent(String EventName, String EventDate, String EventTime, String EventNotes, String EventLocation) {
//        return eventDAO.updateEvent();
//    }

}
