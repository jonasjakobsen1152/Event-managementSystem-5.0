package DAL.DB;

import BE.Event;
import DAL.IEventCoordDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCoordDAO_DB implements IEventCoordDAO {
    private MyDatabaseConnector databaseConnector;

    private EventCoordDAO_DB(){
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> allEvents = new ArrayList<>();

        try(Connection conn = databaseConnector.getConnection();
            Statement stmt = conn.createStatement()) {

            //SQL string that gets all information from the Event table
            String sql = "Select * From dbo.Events;";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("ID");
                String eventName = rs.getString("EventName");
                String eventDate = rs.getString("EventDate");
                String eventTime = rs.getString("EventTime");
                String eventNotes = rs.getString("EventNotes");
                String eventLocation = rs.getString("EventLocation");
            Event event = new Event(id,eventName,eventDate,eventTime,eventNotes,eventLocation);
            allEvents.add(event);
            }
            return allEvents;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Could not get Songs from database", e);
        }
    }

    public void updateEvent(Event event) {

        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "UPDATE event SET EventName = ?, EventDate = ?, EventTime = ?, EventNotes = ?, EventLocation = ? WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, event.getEventName());
          //  stmt.setDate(2, event.getEventDate());
            stmt.setString(3, event.getEventTime());
            stmt.setString(4, event.getEventNotes());
            stmt.setString(5, event.getEventLocation());

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void deleteEvent(int EventID, String EventName) {

        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "DELETE FROM Events WHERE EventID = ? AND EventName = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, EventID);
            stmt.setString(2, EventName);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete event", e);
        }
    }

}
