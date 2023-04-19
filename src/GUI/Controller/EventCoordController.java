package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.AdminModel;
import GUI.Model.ETEBModel;
import GUI.Model.EventCoordModel;
import GUI.Model.LoginModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordController extends BaseController implements Initializable {
    public MFXButton btnCreateNewEvent;
    public MFXButton btnUpdateEvent;
    public MFXButton btnViewTickets;
    public MFXButton btnDeleteTickets;
    public TableView<Event> tblAllEvents;
    public TableColumn clmEventName;
    public Text txtEventName;
    public Text txtEventTime;
    public Text txtEventNotes;
    public Text txtEventLocation;
    public Text txtEventDate;
    private EventCoordModel eventCoordModel;
    public EventCRUDController eventCRUDController;
    public LoginModel loginModel;
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
        loginModel = LoginModel.getInstance();
        eventCoordModel = EventCoordModel.getInstance();
        eventCRUDController = new EventCRUDController();
        eventCRUDController.setModel(new ETEBModel());
        setModel(new ETEBModel());




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showEvent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateEventCoordModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tblAllEvents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                selectedEvent = tblAllEvents.getSelectionModel().getSelectedItem();
                eventCoordModel.setSelectedEvent(selectedEvent);
                if(selectedEvent != null) {
                    eventCoordModel.setSelectedEvent(selectedEvent);
                    txtEventName.setText(getSelectedEvent().getEventName());
                    txtEventDate.setText(getSelectedEvent().getEventDate());
                    txtEventLocation.setText(getSelectedEvent().getEventLocation());
                    txtEventNotes.setText(getSelectedEvent().getEventNotes());
                    txtEventTime.setText(getSelectedEvent().getEventTime());
                }
            }
        });
    }
    
    public void setModel(EventCoordModel model) {
            this.eventCoordModel = model;

    }

    private void updateEventCoordModel() throws Exception {
        tblAllEvents.setItems(eventCoordModel.getObservableEvents(loginModel.getLoggedInUser()));
    }

    public void handleCreateNewEvent(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/EventCRUDWindow.fxml"));
        AnchorPane pane = loader.load();

        EventCRUDController eventCRUDController = loader.getController();
        eventCRUDController.setModel(super.getModel());

        eventCRUDController.setup();

        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner((((Node)actionEvent.getSource()).getScene().getWindow()));
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();
        updateEventCoordModel();
    }

    void showEvent() throws SQLException {
        tblAllEvents.setItems(eventCoordModel.getObservableEvents(loginModel.getLoggedInUser()));
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
//        EventCoordModel updateEventModel = new EventCoordModel();
//        eventCoordModel = updateEventModel;

        tblAllEvents.setItems(eventCoordModel.getObservableEvents(loginModel.getLoggedInUser()));
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



    public void handleViewTickets(ActionEvent actionEvent) throws IOException {
        selectedEvent = tblAllEvents.getSelectionModel().getSelectedItem();
        if(selectedEvent != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/Tickets.fxml"));
            AnchorPane pane = loader.load();
            Stage dialogWindow = new Stage();
            Scene scene = new Scene(pane);
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner((((Node)actionEvent.getSource()).getScene().getWindow()));
            dialogWindow.setScene(scene);
            dialogWindow.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select an Event");
            alert.setHeaderText("Choose an event to create tickets for");
            alert.show();
        }

    }


    @Override
    public void setup() throws SQLException {
        eventCoordModel = getModel().getEventCoordModel();
        showEvent();
    }

    @FXML
    public void handleExitCoordinatorWindow(ActionEvent actionEvent) {
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

    public void handleSpecialTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/SpecialTicketWindow.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogWindow = new Stage();
        Scene scene = new Scene(pane);
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner((((Node)actionEvent.getSource()).getScene().getWindow()));
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();
    }
}
