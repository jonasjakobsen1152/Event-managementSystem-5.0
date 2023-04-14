package BLL;

import BE.Event;
import BE.User;
import DAL.DB.AdminDAO_DB;
import DAL.IAdminDAO;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {
private IAdminDAO adminDAO;

public AdminManager(){
    adminDAO = new AdminDAO_DB();
}
    public User createCoord(String username, String password, String role) {
        return adminDAO.createUser(username,password,role);
    }

    public ArrayList<User> getAllUsers() {
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
        adminDAO.deleteUsersInEvent(selectedEvent.getId());
    adminDAO.deleteTicketsInEvent(selectedEvent.getId());
    adminDAO.deleteEvent(selectedEvent.getId(),selectedEvent.getEventName());

    }

}
