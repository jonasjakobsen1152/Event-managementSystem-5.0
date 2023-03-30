package DAL;

import BE.Event;

public interface IEventCoordDAO {


    public void updateEvent(Event event);

    public void deleteEvent(int EventID, String EventName);
}
