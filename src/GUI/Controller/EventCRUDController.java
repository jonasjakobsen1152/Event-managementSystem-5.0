package GUI.Controller;

import BE.Event;
import GUI.Model.EventCoordModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
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
    public TextField txtTest;
    private Event selectedEvent;

    public EventCRUDController() throws Exception {
        eventCoordModel = new EventCoordModel();
    }

    @Override
    public void setup() {
        eventCoordModel = getModel().getEventCoordModel();

        if(eventCoordModel.getSelectedEvent()!=null){
            txtName.setText(eventCoordModel.getSelectedEvent().getEventName());
            txtDate.setText(eventCoordModel.getSelectedEvent().getEventDate());
            txtTime.setText(eventCoordModel.getSelectedEvent().getEventTime());
            txtLocation.setText(eventCoordModel.getSelectedEvent().getEventLocation());
            txtNotes.setText(eventCoordModel.getSelectedEvent().getEventNotes());
            txtTest.setText(eventCoordModel.getSelectedEvent().getEventName());
        }
    }

    public void handleCreateEvent(ActionEvent actionEvent) {
        String name = txtName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String location = txtLocation.getText();
        String notes = txtNotes.getText();
        try {
            eventCoordModel.createEvent(name,date,time, location, notes);
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

    public void setEvent(Event event){
        selectedEvent = event;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
