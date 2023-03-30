package GUI.Model;

import BE.User;
import BLL.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminModel {
    AdminManager adminManager;
    private ObservableList<User> usersToBeViewed;

    public AdminModel(){
        adminManager = new AdminManager();
        usersToBeViewed = FXCollections.observableArrayList();
        usersToBeViewed.addAll(adminManager.getAllUsers());
    }

    public void createCoord(String username, String password,String role) {
        adminManager.createCoord(username,password,role);
    }

    public void deleteCoord(User selectedUser) {
        adminManager.deleteCoord(selectedUser);
        usersToBeViewed.remove(selectedUser);
    }

    public ObservableList<User> getObservableUsers() {
        return usersToBeViewed;
    }
}
