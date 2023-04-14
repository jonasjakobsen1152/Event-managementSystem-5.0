package GUI.Controller;

import GUI.Model.ETEBModel;
import GUI.Model.EventCoordModel;

import java.sql.SQLException;

public abstract class BaseController {
    public ETEBModel model;

    public ETEBModel getModel(){
        return model;
    }
    public void setModel(ETEBModel model){
        this.model = model;
    }


    public abstract void setup() throws SQLException; //This is here so we can override it in MainController
}