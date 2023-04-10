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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    public void handleCreateNewEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/EventCRUDWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();
    }

    void showEvent(){

        tblAllEvents.setItems(eventCoordModel.getObservableEvents());
        clmEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));

        tblAllEvents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                eventCoordModel.setSelectedEvent(newValue);
            }
        });


    }

    public void handleUpdateEvent(ActionEvent actionEvent) throws IOException {

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
            showEvent();

        }
    }

    public void handleViewTickets(ActionEvent actionEvent) {
    }

    public void handleDeleteTickets(ActionEvent actionEvent) {
    }

    @Override
    public void setup() {
        eventCoordModel = getModel().getEventCoordModel();
        showEvent();
    }


}
