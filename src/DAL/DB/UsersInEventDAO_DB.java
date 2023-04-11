package DAL.DB;

import BE.Event;
import BE.User;
import DAL.IUsersInEventDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersInEventDAO_DB implements IUsersInEventDAO {

    private MyDatabaseConnector dbConnector;

    @Override
    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        String sql ="INSERT INTO UserEvent (UserID, EventID) VALUES (?,?);";

        try(Connection connection = dbConnector.getConnection()){

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int selectedEventID = selectedEvent.getId();
            int selectedUserID = selectedUser.getId();

            stmt.setInt(1, selectedUserID);
            stmt.setInt(2, selectedEventID);

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
