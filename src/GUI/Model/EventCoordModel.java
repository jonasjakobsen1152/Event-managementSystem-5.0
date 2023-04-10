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

    public void updateEvent(Event selectedEvent) throws Exception {
        eventCoordManager.updateEvent(selectedEvent);

        showList();
    }

    public void createEvent(String name, String date, String time, String location, String notes) throws Exception {
        eventCoordManager.createEvent(name, date, time, location, notes);
    }

    public ObservableList<Event> getObservableEvents() {
        return eventsToBeViewed;
    }

    public void setSelectedEvent(Event selectedEvent) {
        System.out.println(selectedEvent);
        this.selectedEvent = selectedEvent;
    }
    public void showList() throws Exception {
        eventsToBeViewed.clear();
        eventsToBeViewed.addAll(eventCoordManager.getAllEvent());
        System.out.println(eventsToBeViewed.toString());
    }
    public Event getSelectedEvent(){
        return selectedEvent;
    }
}
