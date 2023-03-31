package GUI.Model;

import BLL.EventCoordManager;

import java.util.Date;

public class EventCoordModel {

    EventCoordManager eventCoordManager;

    public void updateEvent(String EventName, Date EventDate, String EventTime, String EventNotes, String EventLocation) {
      //  eventCoordManager.updateEvent(EventName, EventDate, EventTime, EventNotes, EventLocation);
    }

    public void createEvent(String name, String date, String time, String location, String notes) {
        eventCoordManager.createEvent(name, date, time, location, notes);
    }
}
