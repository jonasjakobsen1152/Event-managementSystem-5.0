package DAL;

import BE.Event;
import BE.User;

import java.util.ArrayList;
import java.util.List;

public interface IAdminDAO {

    public User createUser(String UserName, String Password, String Roles);

    public void deleteUser(int LogInID, String UserName);

    void deleteEvent(int EventID, String EventName);

    public ArrayList<User> getAllUsers();


   public void removeUser(int id, String userName);

    List<Event> getAllEvent();
}
