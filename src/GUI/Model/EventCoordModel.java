package GUI.Model;

import BE.Event;
import BE.User;
import BLL.AdminManager;
import BLL.EventCoordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;

public class EventCoordModel {

    EventCoordManager eventCoordManager;
    private Event selectedEvent;
    private static EventCoordModel instance;
    private ObservableList<Event> eventsToBeViewed;
    private ObservableList<User> usersToBeViewed;


    private EventCoordModel() throws Exception {

        eventCoordManager = new EventCoordManager();
        //Making usersToBeViewed Observable
        usersToBeViewed = FXCollections.observableArrayList();
        usersToBeViewed.addAll(eventCoordManager.getAllUsers());

        //Making eventsToBeViewed Observable
        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(eventCoordManager.getAllEvent());
    }

    public static EventCoordModel getInstance() throws Exception {
        if(instance == null){
            instance = new EventCoordModel();
        }
        return instance;
    }

    public void updateEvent(Event selectedEvent) throws Exception {
        eventCoordManager.updateEvent(selectedEvent);

        showList();
    }

    public void createEvent(String name, String date, String time, String notes, String location) throws Exception {
        eventCoordManager.createEvent(name, date, time, notes, location);

        showList();
    }

    public ObservableList<Event> getObservableEvents(User user) throws SQLException {
        eventsToBeViewed.clear();
        eventsToBeViewed.addAll(eventCoordManager.getLoggedInUserEvent(user));
        //eventsToBeViewed.addAll(eventCoordManager.getAllEvent());
        return eventsToBeViewed;
    }

    public void setSelectedEvent(Event selectedEvent) {

        this.selectedEvent = selectedEvent;
    }
    public void showList() throws Exception {
        eventsToBeViewed.clear();
        eventsToBeViewed.addAll(eventCoordManager.getAllEvent());
    }
    public Event getSelectedEvent(){
        return selectedEvent;
    }

    public void deleteEvent(Event selectedEvent) throws Exception {
        eventCoordManager.deleteEvent(selectedEvent);
        eventsToBeViewed.remove(selectedEvent);
        showList();
    }

    public ObservableList<User> getObservableUsers() {
        return usersToBeViewed;
    }

}
