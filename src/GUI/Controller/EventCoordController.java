package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AdminModel;
import GUI.Model.ETEBModel;
import GUI.Model.EventCoordModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordController extends BaseController implements Initializable {
    public MFXButton btnCreateNewEvent;
    public MFXButton btnUpdateEvent;
    public MFXButton btnViewTickets;
    public MFXButton btnDeleteTickets;
    public TableView<Event> tblAllEvents;
    public TableColumn clmEventName;
    private EventCoordModel eventCoordModel;
    public EventCRUDController eventCRUDController;
    private Event selectedEvent;
    public TableView<Event> tblShowEvents;
    public TableColumn clmUsername;
    public TableColumn clmPassword;
    public TableView<User> tableViewCoord;
    public TableColumn clmStartTime;
    public TableColumn clmLocation;
    public TableColumn clmEndTime;
    private ETEBModel model;
    private BaseController baseController;


    public EventCoordController() throws Exception {
        eventCoordModel = new EventCoordModel();
        eventCRUDController = new EventCRUDController();
        eventCRUDController.setModel(new ETEBModel());
        setModel(new ETEBModel());




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEvent();
        try {
            updateEventCoordModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tblAllEvents.setOnMouseClicked(event -> {
            Event selectedEvent = tblAllEvents.getSelectionModel().getSelectedItem();
            eventCoordModel.setSelectedEvent(selectedEvent);
        });






    }
    
    public void setModel(EventCoordModel model) {
            this.eventCoordModel = model;

    }

    private void updateEventCoordModel() throws Exception {
        EventCoordModel updateEventCoordModel = new EventCoordModel();
        eventCoordModel = updateEventCoordModel;
        tblAllEvents.setItems(eventCoordModel.getObservableEvents());
    }

    public void handleCreateNewEvent(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/EventCRUDWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        eventCRUDController.setup();
        dialogWindow.showAndWait();
        updateEventCoordModel();
    }

    void showEvent(){

        tblAllEvents.setItems(eventCoordModel.getObservableEvents());
        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));



    }

    public void handleUpdateEvent(ActionEvent actionEvent) throws Exception {

        Event selectedEvent = tblAllEvents.getSelectionModel().getSelectedItem();

        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Needed Info");
            alert.setHeaderText("Please choose the event you would like to edit...");
            alert.show();
        } else {
            eventCoordModel.setSelectedEvent(selectedEvent);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EventCRUDWindow.fxml"));
            AnchorPane pane = loader.load();

            EventCRUDController eventCRUDController = loader.getController();
            eventCRUDController.setModel(super.getModel());

            eventCRUDController.setupUpdate(selectedEvent);
            eventCRUDController.setEvent(selectedEvent);

            Stage dialogWindow = new Stage();
            Scene scene = new Scene(pane);
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner((((Node)actionEvent.getSource()).getScene().getWindow()));
            dialogWindow.setScene(scene);
            dialogWindow.showAndWait();
            updateEventCoordModel();

        }
    }

    private Event getSelectedEvent(){
        Event event;
        event = tblAllEvents.getSelectionModel().getSelectedItem();
        return event;
    }


    private void updateEventModel() throws Exception {
        EventCoordModel updateEventModel = new EventCoordModel();
        eventCoordModel = updateEventModel;
        tblAllEvents.setItems(eventCoordModel.getObservableEvents());
    }

    private void showUsersAndEvent(){
        clmUsername.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<User, String>("passWord"));

        tableViewCoord.setItems(eventCoordModel.getObservableUsers());

        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        clmStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTime"));
        //clmEndTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventEndTime"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));

        tblAllEvents.setItems(eventCoordModel.getObservableEvents());

    }


    public void handleDeleteEvent(ActionEvent actionEvent) throws Exception {
        selectedEvent = tblAllEvents.getSelectionModel().getSelectedItem();

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
                eventCoordModel.deleteEvent(selectedEvent);
                try {
                    updateEventModel();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                showEvent();
            }
        }
    }



    public void handleViewTickets(ActionEvent actionEvent) {
    }


    @Override
    public void setup() {
        eventCoordModel = getModel().getEventCoordModel();
        showEvent();
    }
}
