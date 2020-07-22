package quizfx;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SelectionScene extends GenericScene {
    
    private ComboBox<String> difficultyCombo;
    private ComboBox<String> categoryCombo;
    
    public SelectionScene(ArrayList<String> categories) {
        
        GridPane grid = new GridPane(); 
        
        difficultyCombo = new ComboBox<>();
        difficultyCombo.getItems().add("Easy");
        difficultyCombo.getItems().add("Medium");
        difficultyCombo.getItems().add("Hard");
        
        categoryCombo = new ComboBox<>();
        for(String cat : categories) {
            categoryCombo.getItems().add(cat);
        }
        
        Button btn = new Button("Start");
        btn.setOnMouseClicked((MouseEvent arg0) -> {
            System.out.println(difficultyCombo.getValue());
            System.out.println(categoryCombo.getValue());
            controller.displayQuiz(difficultyCombo.getValue(), categoryCombo.getValue());
        });
        
        grid.add(difficultyCombo, 0, 1);
        grid.add(categoryCombo, 0, 2);
        grid.add(btn, 0, 3);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
    
}