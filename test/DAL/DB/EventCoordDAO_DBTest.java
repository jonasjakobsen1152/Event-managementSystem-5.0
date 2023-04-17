package DAL.DB;

import BE.Event;
import BE.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventCoordDAO_DBTest {

    @Test
    void updateEvent() {
    }

    @Test
    void deleteEvent() {
    }

    @Test
    void createEvent() {
        String name = "Test";
        String date = "Test2";
        String time = "Test3";
        String location = "Test4";
        String notes = "Test5";
        User loggedInUser = new User(4,"TestUser","TestPass","Event coordinator");
        EventCoordDAO_DB eventCoordDAO_db = new EventCoordDAO_DB();


        Event actualEvent = eventCoordDAO_db.createEvent(name,date,time,location,notes,loggedInUser);
        Event expectedEvent = new Event(actualEvent.getId(),name,date,time,notes,location);
        
        String expectedName = expectedEvent.getEventName();
        String expectedDate = expectedEvent.getEventDate();
        String expectedTime = expectedEvent.getEventTime();
        String expectedLocation = expectedEvent.getEventLocation();
        String expectedNotes = expectedEvent.getEventNotes();

        String actualName = actualEvent.getEventName();
        String actualDate = actualEvent.getEventDate();
        String actualTime = actualEvent.getEventTime();
        String actualLocation = actualEvent.getEventLocation();
        String actualNotes = actualEvent.getEventNotes();



        Assertions.assertSame(expectedName, actualName);
        Assertions.assertSame(expectedDate,actualDate);
        Assertions.assertSame(expectedTime, actualTime);
        Assertions.assertSame(expectedLocation, actualLocation);
        Assertions.assertSame(expectedNotes,actualNotes);

    }
}