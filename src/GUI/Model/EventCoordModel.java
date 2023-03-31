package GUI.Model;

import BLL.EventCoordManager;

import java.util.Date;

public class EventCoordModel {

    EventCoordManager eventCoordManager;

    public EventCoordModel(){
        eventCoordManager = new EventCoordManager();
    }

    public void updateEvent(String EventName, String EventDate, String EventTime, String EventNotes, String EventLocation) {
      //  eventCoordManager.updateEvent(EventName, EventDate, EventTime, EventNotes, EventLocation);
    }

    public void createEvent(String name, String date, String time, String location, String notes) {
        eventCoordManager.createEvent(name, date, time, location, notes);
    }
}
