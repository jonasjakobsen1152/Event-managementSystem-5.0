package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.EventCoordModel;
import GUI.Model.LoginModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventCRUDController extends BaseController implements Initializable {


    public Button btnCreateEvent;
    public MFXTextField txtName;
    public MFXTextField txtDate;
    public MFXTextField txtTime;
    public MFXTextField txtLocation;
    public MFXTextField txtNotes;
    public EventCoordModel eventCoordModel;


    public Button btnUpdate;
    private Event selectedEvent;

    public LoginModel loginModel;



    public EventCRUDController() throws Exception {
        eventCoordModel = EventCoordModel.getInstance();
        loginModel = LoginModel.getInstance();

    }

    public void setupUpdate(Event selectedEvent) {
        eventCoordModel = getModel().getEventCoordModel();

        if(selectedEvent != null){
            txtName.setText(selectedEvent.getEventName());
            txtDate.setText(selectedEvent.getEventDate());
            txtTime.setText(selectedEvent.getEventTime());
            txtLocation.setText(selectedEvent.getEventLocation());
            txtNotes.setText(selectedEvent.getEventNotes());
            btnCreateEvent.setVisible(false);
        }
        else {

            btnUpdate.setVisible(false);
        }
    }

    public void handleCreateEvent(ActionEvent actionEvent) {

        String name = txtName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String location = txtLocation.getText();
        String notes = txtNotes.getText();
        User loggedInUser = loginModel.getLoggedInUser();
        try {
            eventCoordModel.createEvent(name,date,time, location, notes, loggedInUser);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            alertUser("Invalid data inputs");
            throw new RuntimeException(e);
        }

    }

    private void alertUser(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(error + "");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleUpdateEvent(ActionEvent actionEvent) throws Exception {
        String updatedName = txtName.getText();
        String updatedDate = txtDate.getText();
        String updatedTime = txtTime.getText();
        String updatedLocation = txtLocation.getText();
        String updatedNotes = txtNotes.getText();
        Event updatedEvent = new Event(selectedEvent.getId(), updatedName, updatedDate, updatedTime, updatedNotes, updatedLocation);
        eventCoordModel.updateEvent(updatedEvent);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    public void setup() {
        btnUpdate.setVisible(false);
    }

    public void setEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public void setOnEventUpdated(Object o) {
    }
}
