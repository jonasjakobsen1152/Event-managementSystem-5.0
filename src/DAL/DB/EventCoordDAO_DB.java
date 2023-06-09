package DAL.DB;

import BE.Event;
import BE.User;
import DAL.IEventCoordDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCoordDAO_DB implements IEventCoordDAO {
    private MyDatabaseConnector databaseConnector;

    public EventCoordDAO_DB(){
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
        } catch (SQLException e) {
            throw new Exception("Could not get Events from database", e);
        }
        return allEvents;
    }

    public void updateEvent(Event event) {

        try (Connection conn = databaseConnector.getConnection()) {
        //SQL string that can update the events
            String sql = "UPDATE Events SET EventName = ?, EventDate = ?, EventTime = ?, EventNotes = ?, EventLocation = ? WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, event.getEventName());
            stmt.setString(2, event.getEventDate());
            stmt.setString(3, event.getEventTime());
            stmt.setString(4, event.getEventNotes());
            stmt.setString(5, event.getEventLocation());
            stmt.setInt(6, event.getId());

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException("Could not update event", e);
        }

    }


    public void deleteEvent(int ID, String EventName) {

        try (Connection conn = databaseConnector.getConnection()) {
            //SQL string that makes it possible to delete events from the program and database
            String sql = "DELETE FROM Events WHERE ID = ? AND EventName = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ID);
            stmt.setString(2, EventName);

            stmt.executeUpdate();
            deleteUsersInEvent(ID);

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete event", e);
        }
    }

    public void deleteUsersInEvent(int ID){
        //SQL string that deletes all information that is connected to the deleted event
        String sql = "DELETE FROM UserEvent WHERE EventID = ?;";

        try (Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,ID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete users in event", e);
        }
    }


    @Override
    public Event createEvent(String name, String date, String time, String location, String notes, User loggedInUser) {
        try (Connection conn = databaseConnector.getConnection()){
            //SQL string that makes it possible to create an event
            String sql = "INSERT INTO Events (EventName, EventDate, EventTime, EventNotes, EventLocation) VALUES (?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,name);
            stmt.setString(2,date);
            stmt.setString(3,time);
            stmt.setString(4,notes);
            stmt.setString(5,location);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }
            Event event = new Event(id, name, date, time, notes, location);
            addEventCoordinatorToEvent(event,loggedInUser);
            return event;
        }catch (SQLException e){
            throw new RuntimeException("SQL Error: could not create Event", e);
        }
    }

    public void addEventCoordinatorToEvent(Event selectedEvent, User selectedUser) throws SQLServerException {
        //SQL string that makes it possible to put EventCoordinators into events, when they create it.
        String sql ="INSERT INTO UserEvent (UserID, EventID) VALUES (?,?);";

        try(Connection connection = databaseConnector.getConnection()){

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int selectedEventID = selectedEvent.getId();
            int selectedUserID = selectedUser.getId();

            stmt.setInt(1, selectedUserID);
            stmt.setInt(2, selectedEventID);

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException("Could not add event coordinator to the event",e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            //SQL string that gets all the information from the User tabel
            String sql = "Select * From Users";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("LogInID");
                String userName = rs.getString("UserName");
                String userPassWord = rs.getString("Password");
                String roles = rs.getString("Roles");

                User user = new User(id, userName, userPassWord, roles);
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Could not receive user information from the database", ex);
        }
        return allUsers;
    }

    public List<Event> getLoggedInUserEvent(User user) throws SQLException {
        ArrayList<Event> allEvents = new ArrayList<>();
        try(Connection conn = databaseConnector.getConnection();
            Statement stmt = conn.createStatement()){
            //SQL string that gets the right information about the person that logs in
            String sql = "SELECT * FROM Events eve, UserEvent ue, Users us \n" +
                    "WHERE us.LogInID = ue.UserID AND eve.ID = ue.EventID AND us.LogInID =" + user.getId() + ";";

            if (stmt.execute(sql)){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    int id = rs.getInt("ID");
                    String eventName = rs.getString("EventName");
                    String eventDate = rs.getString("EventDate");
                    String eventTime = rs.getString("EventTime");
                    String eventNotes = rs.getString("EventNotes");
                    String eventLocation = rs.getString("EventLocation");
                    Event event = new Event(id,eventName,eventDate,eventTime,eventNotes,eventLocation);
                    allEvents.add(event);
                }
            }
        }
        return allEvents;
    }

}
