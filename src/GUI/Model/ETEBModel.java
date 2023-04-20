package GUI.Model;

public class ETEBModel {
    private EventCoordModel eventCoordModel;

    public ETEBModel() throws Exception {
        eventCoordModel = EventCoordModel.getInstance();
    }


    public EventCoordModel getEventCoordModel(){return eventCoordModel;}

}
