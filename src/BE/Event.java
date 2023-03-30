package BE;

import java.time.LocalDate;

public class Event {
    private int id;
    private String eventName;
    private LocalDate eventDate;
    private String eventTime;
    private String eventNotes;
    private String eventLocation;
    public Event(int id, String eventName, LocalDate eventDate, String eventTime, String eventNotes, String eventLocation){
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventNotes = eventNotes;
        this.eventLocation = eventLocation;
    }


    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventNotes() {
        return eventNotes;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventNotes(String eventNotes) {
        this.eventNotes = eventNotes;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
