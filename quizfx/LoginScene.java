package quizfx;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LoginScene extends GenericScene {
    
    
    public LoginScene() {
        
        GridPane grid = new GridPane(); 
        
        Button loginBtn = new Button("LOGIN");
        loginBtn.setOnMouseClicked((MouseEvent arg0) -> {
            controller.displayQuizSelection();
        });
        grid.add(loginBtn, 1, 1);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    
}

