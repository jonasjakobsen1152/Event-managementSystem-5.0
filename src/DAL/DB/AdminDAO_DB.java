package DAL.DB;

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

            String sql = "DELETE FROM Users WHERE LogInID = ? AND UserName = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, LogInID);
            stmt.setString(2, UserName);

            stmt.executeUpdate();


        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete user", e);
        }

    }


    @Override
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

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
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
            ex.printStackTrace();
        }
        return allUsers;
    }
}



