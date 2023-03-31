package GUI.Controller;

import BE.User;
import GUI.Model.LoginModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {
    public Text txtFailedLogin;
    @FXML
    private Button btnLogInToApplication;
    @FXML
    private MFXTextField txtUserName;
    @FXML
    private MFXTextField txtPassWord;

    private LoginModel loginModel;

    ArrayList<User> allUsers;

    int userId = 0;

    public LoginController() throws SQLException {
        loginModel = new LoginModel();

        // Get a list of all users and use it to check against the entered userName and passWord
        allUsers = loginModel.getAllUsers();
    }

    /**
     * A method that pulls a list of all users and checks if the entered values matches an account
     * @param actionEvent
     */
    public void HandlelogInToApplication(ActionEvent actionEvent) throws SQLException, IOException {
        String userNameToCheck = txtUserName.getText();
        String passWordToCheck = txtPassWord.getText();

        for (User userToCheck : allUsers) {
            if (userToCheck.getUserName().equals(userNameToCheck) && userToCheck.getPassWord().equals(passWordToCheck)) {
                userId = userToCheck.getId();
                if(userToCheck.getRoles().equals("Admin")){
                    handleOpenAdmin(actionEvent);
                    Stage stage = (Stage) btnLogInToApplication.getScene().getWindow();
                    stage.close();
                }
                else{
                    openCoordinator();
                    Stage stage = (Stage) btnLogInToApplication.getScene().getWindow();
                    stage.close();
                }
            }

        }
        txtFailedLogin.setText("Failed Login");
    }

    public void handleOpenAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AdminWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        dialogWindow.show();
    }

    public void openCoordinator() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/CoordinatorWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        dialogWindow.show();
    }
}