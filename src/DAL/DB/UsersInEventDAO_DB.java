package DAL.DB;

import BE.Event;
import BE.User;
import DAL.IUsersInEventDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersInEventDAO_DB implements IUsersInEventDAO {

    private MyDatabaseConnector dbConnector;

    public UsersInEventDAO_DB(){
        dbConnector = new MyDatabaseConnector();
    }



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

    @Override
    public List<User> getCoordinatorsInEvent(int selectedEventId) throws SQLServerException {
        ArrayList<User> allUser = new ArrayList<>();
        try(Connection connection = dbConnector.getConnection()){

            String sql = "SELECT * FROM Events eve, UserEvent ue, Users us \n" +
                    "WHERE us.LogInID = ue.UserID AND eve.ID = ue.EventID AND eve.ID =" + selectedEventId + ";";

            Statement stmt = connection.createStatement();

            if(stmt.execute(sql)){
                ResultSet resultSet = stmt.getResultSet();
                while(resultSet.next()){
                    int id = resultSet.getInt("LoginID");
                    String name = resultSet.getString("UserName");
                    String password = resultSet.getString("Password");
                    String roles = resultSet.getString("Roles");

                    User user = new User(id, name, password,roles);
                    allUser.add(user);

                }
            }
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database");
            throw new RuntimeException(e);
        }
        return allUser;
    }


}
