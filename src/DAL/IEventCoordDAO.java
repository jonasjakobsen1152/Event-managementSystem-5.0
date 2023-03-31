package DAL;

import BE.Event;

public interface IEventCoordDAO {


    public void updateEvent(Event event);

    public void deleteEvent(int EventID, String EventName);

    Event createEvent(String name, String date, String time, String location, String notes);
}
