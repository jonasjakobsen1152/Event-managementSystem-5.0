package BLL;

import BE.User;
import DAL.DB.LogInDAO_DB;
import DAL.ILoginDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LogInManager {

  private ILoginDAO iLoginDAO;

  private ArrayList<User> allUsers;

    public LogInManager(){
        iLoginDAO = new LogInDAO_DB();
        allUsers = new ArrayList<>();
    }
    public ArrayList<User> getAllUsers() throws SQLException {
      allUsers.addAll(iLoginDAO.getAllUsers());
      return allUsers;
    }

}
