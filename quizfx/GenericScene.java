package quizfx;

import javafx.scene.Scene;
import quizfx.controllers.Controller;

public class GenericScene {
    
    protected Scene scene;
    protected Controller controller;
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public Scene getScene() {
        return scene;
    }
    
}