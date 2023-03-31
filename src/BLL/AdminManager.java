package BLL;

import BE.Event;
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

    public List<Event> getAllEvent() {
        return adminDAO.getAllEvent();
    }

    public void deleteCoord(User selectedUser) {
        adminDAO.deleteUser(selectedUser.getId(),selectedUser.getUserName());
    }

    public void removeCoord(User selectedUser) {
    adminDAO.removeUser(selectedUser.getId(),selectedUser.getUserName());
    }

    public void deleteEvent(Event selectedEvent) {
    adminDAO.deleteEvent(selectedEvent.getId(),selectedEvent.getEventName());
    }
}
