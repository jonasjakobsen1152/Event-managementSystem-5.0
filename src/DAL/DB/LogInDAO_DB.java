package DAL.DB;

import BE.User;
import DAL.ILoginDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogInDAO_DB implements ILoginDAO {

    private MyDatabaseConnector databaseConnector;

    public LogInDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<User> getAllUsers() {
            ArrayList<User> allUsers = new ArrayList<>();

            try (Connection conn = databaseConnector.getConnection();
                 Statement stmt = conn.createStatement()) {
                String sql = "Select * From dbo.Users";

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
