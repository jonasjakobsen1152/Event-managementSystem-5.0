package GUI.Controller;

import GUI.Model.ETEBModel;
import GUI.Model.EventCoordModel;

public abstract class BaseController {
    protected EventCoordModel getModel;

    public BaseController() throws Exception {
        try{
            this.model = new ETEBModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ETEBModel getModel(){
        return model;
    }
    private ETEBModel model;

    public abstract void setup(); //This is here so we can override it in MainController
}