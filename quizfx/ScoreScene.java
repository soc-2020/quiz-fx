package quizfx;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ScoreScene extends GenericScene {
    
    
    public ScoreScene(int score, int total) {
        
        GridPane grid = new GridPane(); 
        
        Label scoreLbl = new Label("Result: " + score + "/" + total);
        
        Button loginBtn = new Button("PLAY AGAIN");
        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                controller.displayQuizSelection();
            }
        });
        
        grid.add(scoreLbl, 0, 0);
        grid.add(loginBtn, 0, 1);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    
}