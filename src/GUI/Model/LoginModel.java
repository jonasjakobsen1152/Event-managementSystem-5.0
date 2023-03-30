package GUI.Model;

import BE.User;
import BLL.LogInManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginModel {

    private LogInManager logInManager;

    private ArrayList<User> allUsers;

    public LoginModel(){
        logInManager = new LogInManager();
        allUsers = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        allUsers.addAll(logInManager.getAllUsers());
        return allUsers;
    }
}
