package DAL;

import BE.User;

import java.util.List;

public interface IAdminDAO {

    public User createUser(String UserName, String Password, String Roles);

    public void deleteUser(int LogInID, String UserName);

    public void deleteEvent(int EventID, String EventName);

    public List<User> getAllUsers();


   public void removeUser(int id, String userName);
}
