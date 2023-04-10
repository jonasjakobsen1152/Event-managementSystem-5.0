package GUI.Model;

public class ETEBModel {
    private EventCoordModel eventCoordModel;

    public ETEBModel() throws Exception {
        eventCoordModel = new EventCoordModel();
    }
    public void setEventCoordModel(EventCoordModel eventCoordModel){
        this.eventCoordModel = eventCoordModel;
    }


    public EventCoordModel getEventCoordModel(){return eventCoordModel;}

}
