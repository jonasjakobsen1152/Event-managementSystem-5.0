package DAL.DB;

import DAL.IEventCoordDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventCoordDAO_DB implements IEventCoordDAO {
    private MyDatabaseConnector databaseConnector;

    public EventCoordDAO_DB(){
        databaseConnector = new MyDatabaseConnector();
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
