package BE;

import javafx.collections.ObservableList;

public class SpecialTicket {
    private int id;
    private String qr;
    private String ticketType;
    private int ticketAvailable;

    public SpecialTicket(int id, String qr, String ticketType, int ticketAvailable) {
        this.id = id;
        this.qr = qr;
        this.ticketType = ticketType;
        this.ticketAvailable = ticketAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketAvailable() {
        return ticketAvailable;
    }

    public void setTicketAvailable(int ticketAvailable) {
        this.ticketAvailable = ticketAvailable;
    }
}
