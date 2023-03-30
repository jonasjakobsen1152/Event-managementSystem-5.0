package BLL;

import BE.User;
import DAL.DB.AdminDAO_DB;
import DAL.IAdminDAO;

import java.util.List;

public class AdminManager {
private IAdminDAO adminDAO;

public AdminManager(){
    adminDAO = new AdminDAO_DB();
}
    public User createCoord(String username, String password, String role) {
        return adminDAO.createUser(username,password,role);
    }

    public List<User> getAllUsers() {
        return adminDAO.getAllUsers();
    }

    public void deleteCoord(User selectedUser) {
        adminDAO.deleteUser(selectedUser.getId(),selectedUser.getUserName());
    }
}
