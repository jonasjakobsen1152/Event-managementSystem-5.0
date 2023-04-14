package GUI.Model;

import BE.User;
import BLL.LogInManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginModel {

    private static LoginModel instance;
    private LogInManager logInManager;
    private ArrayList<User> allUsers;
    private User loggedInUser;


    private LoginModel() {
        logInManager = new LogInManager();
        allUsers = new ArrayList<>();
    }

    public static LoginModel getInstance(){
        if (instance == null){
            instance = new LoginModel();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user){
        loggedInUser = user;
        System.out.println(loggedInUser.toString());

    }

    public ArrayList<User> getAllUsers() throws SQLException {
        allUsers.addAll(logInManager.getAllUsers());
        return allUsers;
    }
}
