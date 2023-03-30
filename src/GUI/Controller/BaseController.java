package GUI.Controller;

import GUI.Model.ETEBModel;

public abstract class BaseController {
    public BaseController() throws Exception {
        try{
            this.model = new ETEBModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private ETEBModel model;

    public abstract void setup(); //This is here so we can override it in MainController
}