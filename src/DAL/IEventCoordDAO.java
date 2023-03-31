package DAL;

import BE.Event;

import java.util.List;

public interface IEventCoordDAO {


    public void updateEvent(Event event);

    public void deleteEvent(int EventID, String EventName);

    Event createEvent(String name, String date, String time, String location, String notes);

    List<Event> getAllEvents() throws Exception;
}
