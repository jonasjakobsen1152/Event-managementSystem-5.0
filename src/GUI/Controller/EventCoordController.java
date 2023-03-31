package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AdminModel;
import GUI.Model.EventCoordModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordController extends BaseController implements Initializable {
    public MFXButton btnCreateNewEvent;
    public MFXButton btnUpdateEvent;
    public MFXButton btnViewTickets;
    public MFXButton btnDeleteTickets;
    public TableView tblAllEvents;
    public TableColumn clmEventName;
    private EventCoordModel eventCoordModel = new EventCoordModel();

    public EventCoordController() throws Exception {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEvent();
        try {
            updateEventCoordModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void setModel(EventCoordModel model) {
            this.eventCoordModel = model;

    }

    private void updateEventCoordModel() throws Exception {
        EventCoordModel updateEventCoordModel = new EventCoordModel();
        eventCoordModel = updateEventCoordModel;
        tblAllEvents.setItems(eventCoordModel.getObservableEvents());
    }

    public void handleCreateNewEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/EventCRUDWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        dialogWindow.show();
    }

    private void showEvent(){


        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));


        tblAllEvents.setItems(eventCoordModel.getObservableEvents());

    }

    public void handleUpdateEvent(ActionEvent actionEvent) {

    }

    public void handleViewTickets(ActionEvent actionEvent) {
    }

    public void handleDeleteTickets(ActionEvent actionEvent) {
    }

    @Override
    public void setup() {

    }


}
