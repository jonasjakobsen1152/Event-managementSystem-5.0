package BE;

public class Ticket {
    private int id;

    private int eventID;

    private String name;

    private String lastName;

    private String email;

    private String ticketType;

    private String qr;

    public Ticket(int id, int eventID, String name, String lastName, String email, String ticketType, String qr) {
        this.id = id;
        this.eventID = eventID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.ticketType = ticketType;
        this.qr = qr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}