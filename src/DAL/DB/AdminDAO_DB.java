package DAL.DB;

import BE.Event;
import BE.User;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import DAL.IAdminDAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO_DB implements IAdminDAO {

    private MyDatabaseConnector databaseConnector;

    public AdminDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }


    @Override
    public User createUser(String UserName, String Password, String Roles) {
        //SQL string that creates the user
        String sql = "INSERT INTO Users (UserName, Password, Roles) VALUES (?,?,?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind Parameters
            stmt.setString(1, UserName);
            stmt.setString(2, Password);
            stmt.setString(3, Roles);

            //Run the specified SQL statement
            stmt.executeUpdate();

            //Get the generated ID from the Database
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            User user = new User(id, UserName, Password, Roles);
            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Could not create user", e);
        }

    }

    @Override
    public void deleteUser(int LogInID, String UserName) {

        try (Connection conn = databaseConnector.getConnection()) {
            //SQL string that deletes the user
            String sql = "DELETE FROM Users WHERE LogInID = ? AND UserName = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, LogInID);
            stmt.setString(2, UserName);

            stmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException("Could not delete user", e);
        }

    }


    @Override
    public ArrayList<User> getAllUsers() {
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

    @Override
    public void removeUser(int id, String userName) {

    }

    @Override
    public List<Event> getAllEvent() {
        ArrayList<Event> allEvents = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            //SQL string that gets all information from the Event table
            String sql = "Select * From dbo.Events;";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String eventName = rs.getString("EventName");
                String eventDate = rs.getString("EventDate");
                String eventTime = rs.getString("EventTime");
                String eventNotes = rs.getString("EventNotes");
                String eventLocation = rs.getString("EventLocation");
                Event event = new Event(id, eventName, eventDate, eventTime, eventNotes, eventLocation);
                allEvents.add(event);
            }
            return allEvents;
        } catch (SQLException e) {
            throw new RuntimeException("Could not receive event information from the database" ,e);
        }
    }


    @Override
    public void deleteEvent(int EventID, String EventName) {

        try (Connection conn = databaseConnector.getConnection()) {
            //SQL string that makes it possible to delete events from the admin window
            String sql = "DELETE FROM Events WHERE ID = ? AND EventName = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, EventID);
            stmt.setString(2, EventName);

            stmt.executeUpdate();
            //deleteUsersInEvent(EventID);
            //deleteTicketsInEvent(EventID);

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete event", e);
        }
    }

    public void deleteUsersInEvent(int iD){
        //SQL string that deletes all information that is connected to the deleted event
        String sql = "DELETE FROM UserEvent WHERE EventID = ?;";

        try (Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,iD);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete users in the event",e);
        }
    }

    public void deleteTicketsInEvent(int id){
        //SQl string that makes it possible to delete tickets from the giving event
        String sql = "DELETE FROM TicketCustomer WHERE EventID = ?;";
        try (Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete tickets in the event",e);
        }
    }

}



