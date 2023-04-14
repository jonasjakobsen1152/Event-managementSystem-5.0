package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AdminModel;
import GUI.Model.UsersInEventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController extends BaseController implements Initializable {


    public TableView<User> tableViewCoord;
    public TextField txtCoordUsername;
    public TextField txtCoordPassword;
    public TableColumn clmUsername;
    public TableColumn clmPassword;
    public TableView<User> tblCoordToEvents;
    public TableView<Event> tblShowEvents;
    public TableColumn clmEventName;
    public TableColumn clmStartTime;
    public TableColumn clmEndTime;
    public TableColumn clmLocation;
    public TableColumn clmUsersToEvent;
    public TableColumn clmEventDate;
    private User selectedUser;
    private int selectedEventId;
    private Event selectedEvent;
    public AdminModel adminModel = new AdminModel();
    public UsersInEventModel usersInEventModel = new UsersInEventModel();
    private List<User> allUsers;


    public AdminController() throws Exception {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUserModel();
        updateEventModel();
        updateUsersInEventModel();
        showUsersAndEvent();

        tblShowEvents.setOnMouseClicked(event -> {
            selectedEvent = tblShowEvents.getSelectionModel().getSelectedItem();
            if (selectedEvent == null){ //Fortæller user at personen skal lave en category
                alertUser("Please choose an event");
            }
            else {
                selectedEventId = selectedEvent.getId();
            }
            try{
                usersInEventModel.showlist(selectedEventId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        tblCoordToEvents.setOnMouseClicked(event -> {
            selectedUser = tblCoordToEvents.getSelectionModel().getSelectedItem();
        });
    }

    //Creates a new event coordinator

    public void handleCreateCoord(ActionEvent actionEvent) {
        String username = txtCoordUsername.getText();
        String password = txtCoordPassword.getText();
        String role = "Coordinator";
        adminModel.createCoord(username,password,role);

        updateUserModel();
        showUsersAndEvent();

    }
    private void alertUser(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(error + "");
        alert.showAndWait();
    }

    //Deletes an event coordinator
    public void handleDeleteCoord(ActionEvent actionEvent) {
        selectedUser = tableViewCoord.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select a user");
            alert.setHeaderText("Choose a user to delete");
            alert.show();
        } else if (selectedUser.getId() == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("You cant delete an Admin user");
            alert.show();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedUser.getUserName().concat("?"));
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                adminModel.deleteCoord(getSelectedUser());
                updateUserModel();
                showUsersAndEvent();
            }
        }
    }

    //Updates the model
    private void updateUserModel() {
        AdminModel updateAdminModel = new AdminModel();
        adminModel = updateAdminModel;
        tableViewCoord.setItems(adminModel.getObservableUsers());
    }

    private void updateEventModel() {
        AdminModel updateEventModel = new AdminModel();
        adminModel = updateEventModel;
        tblShowEvents.setItems(adminModel.getObservableEvents());
    }

    private void updateUsersInEventModel(){
        UsersInEventModel updateUsersInEventModel = new UsersInEventModel();
        usersInEventModel = updateUsersInEventModel;
        tblCoordToEvents.setItems(usersInEventModel.getUsersInEventToBeViewed());

    }

    //Used to update and show the list in real time
    private void showUsersAndEvent(){
        clmUsername.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<User, String>("passWord"));

        tableViewCoord.setItems(adminModel.getObservableUsers());

        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        clmStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        clmEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("eventDate"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        tblShowEvents.setItems(adminModel.getObservableEvents());

        clmUsersToEvent.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));

        tblCoordToEvents.setItems(usersInEventModel.getUsersInEventToBeViewed());

    }

    //Gets the selected user
    private User getSelectedUser() {
        User user;
        user = tableViewCoord.getSelectionModel().getSelectedItem();
        return user;
        
    }
    private Event getSelectedEvent(){
        Event event;
        event = tblShowEvents.getSelectionModel().getSelectedItem();
        return event;
    }


    //Deletes an event
    public void handleDeleteEvent(ActionEvent actionEvent) {
        selectedEvent = tblShowEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select an Event");
            alert.setHeaderText("Choose an event to delete");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete: " + selectedEvent.getEventName().concat("?"));
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                adminModel.deleteEvent(getSelectedEvent());
                updateEventModel();
                showUsersAndEvent();
            }
        }
    }

    //View notes about an event
    public void handleViewEventNotes(ActionEvent actionEvent) {
        String eventNotes = selectedEvent.getEventNotes();

        // Create and configure an Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Notes");
        alert.setHeaderText(null);
        alert.setContentText(eventNotes);

        // Show the Alert
        alert.showAndWait();
    }

    @Override
    public void setup() {

    }


    public void handleAddEventCoordinator(ActionEvent actionEvent) throws SQLServerException {

        selectedEvent = tblShowEvents.getSelectionModel().getSelectedItem();
        selectedUser = tableViewCoord.getSelectionModel().getSelectedItem();
        allUsers = usersInEventModel.getAllUsersInEvent(selectedEvent.getId());
        if(selectedEvent == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select an event");
            alert.setHeaderText("Choose a event to add to");
            alert.show();
        }
        else if (selectedUser == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select a user");
            alert.setHeaderText("Choose a user to add");
            alert.show();
        } else {
            String userNameToCheck = selectedUser.getUserName();
            for(User userToCheck : allUsers) {
                if(userToCheck.getUserName().equals(userNameToCheck)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("You cant add a event coordinator to the same Event more than once");
                    alert.show();
                }
                else {
                    usersInEventModel.addEventCoordinatorToEvent(selectedEvent, selectedUser);
                }
            }
        }

    }

    public void handleRemoveCoordFromEvent(ActionEvent actionEvent) throws SQLServerException {
        if(selectedUser == null || selectedEvent == null){
            alertUser("Please select a user and a event");
        }
        else{
            int userId = selectedUser.getId();
            int eventId = selectedEvent.getId();
            int userToBeDeletedId = usersInEventModel.getUserEventId(userId,eventId);
            usersInEventModel.removeUserFromEvent(selectedUser,selectedEvent,userToBeDeletedId);
            updateUsersInEventModel();
        }

    }
    @FXML
    public void handleExitAdminWindow(ActionEvent actionEvent) {
        // Close the current window
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        // Show the LogIn window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/Login.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("LogIn");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
