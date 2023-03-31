package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AdminModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController extends BaseController implements Initializable {


    public TableView<User> tableViewCoord;
    public TextField txtCoordUsername;
    public TextField txtCoordPassword;
    public TableColumn clmUsername;
    public TableColumn clmPassword;
    public TableView<User> CoordToEvents;
    public TableView<Event> tblShowEvents;
    public TableColumn clmEventName;
    public TableColumn clmStartTime;
    public TableColumn clmEndTime;
    public TableColumn clmLocation;
    private User selectedUser;
    private Event selectedEvent;
    public AdminModel adminModel = new AdminModel();


    public AdminController() throws Exception {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUserModel();
        updateEventModel();
        showUsersAndEvent();
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

    //Used to update and show the list in real time
    private void showUsersAndEvent(){
        clmUsername.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<User, String>("passWord"));

        tableViewCoord.setItems(adminModel.getObservableUsers());

        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        clmStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        //clmEndTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventEndTime"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        tblShowEvents.setItems(adminModel.getObservableEvents());

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

    }

    @Override
    public void setup() {

    }


    public void handleAddEventCoordinator(ActionEvent actionEvent) {

        /** TODO ADD EVENTCOORDINATOR TO NEW EVENT
         *
         */

    }

    public void handleRemoveEventCoordinator(ActionEvent actionEvent) {
            selectedUser = CoordToEvents.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Select a user");
                alert.setHeaderText("Choose a user to remove");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("Are you sure you want to remove: " + selectedUser.getUserName().concat("?"));
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    adminModel.deleteCoord(getSelectedUser());
                    updateUserModel();
                    showUsersAndEvent();
                }
            }
        }
}
