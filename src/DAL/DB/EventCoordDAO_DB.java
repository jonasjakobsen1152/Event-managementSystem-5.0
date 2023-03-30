package DAL.DB;

import BE.Event;
import DAL.IEventCoordDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventCoordDAO_DB implements IEventCoordDAO {
    private MyDatabaseConnector databaseConnector;

    private EventCoordDAO_DB(){
        databaseConnector = new MyDatabaseConnector();
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
