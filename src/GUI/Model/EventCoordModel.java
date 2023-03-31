package GUI.Model;

import BE.Event;
import BLL.EventCoordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class EventCoordModel {

    EventCoordManager eventCoordManager;
    private Event selectedEvent;
    private ObservableList<Event> eventsToBeViewed;

    public EventCoordModel() throws Exception {
        eventCoordManager = new EventCoordManager();

        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(eventCoordManager.getAllEvent());
    }

    public void updateEvent(String EventName, String EventDate, String EventTime, String EventNotes, String EventLocation) {
      //  eventCoordManager.updateEvent(EventName, EventDate, EventTime, EventNotes, EventLocation);
    }

    public void createEvent(String name, String date, String time, String location, String notes) throws Exception {
        eventCoordManager.createEvent(name, date, time, location, notes);
    }

    public ObservableList<Event> getObservableEvents() {
        return eventsToBeViewed;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
}
