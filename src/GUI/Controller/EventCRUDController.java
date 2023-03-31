package GUI.Controller;

import GUI.Model.EventCoordModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Date;

public class EventCRUDController {


    public Button btnCreateEvent;
    public MFXTextField txtName;
    public MFXTextField txtDate;
    public MFXTextField txtTime;
    public MFXTextField txtLocation;
    public MFXTextField txtNotes;
    public EventCoordModel eventCoordModel = new EventCoordModel();

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
            throw new RuntimeException(e);
        }

    }
}
