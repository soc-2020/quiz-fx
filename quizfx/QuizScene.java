package quizfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import quizfx.models.Question;

public class QuizScene extends GenericScene {
    
    private int counter;
    private int total;
    private Label question;
    private Label questionText;
    private GridPane grid;
    private ToggleGroup group;
    private HBox box;
    
    public QuizScene(int t) {
        counter = 1;
        this.total = t;
        
        grid = new GridPane(); 
        
        question = new Label("Question " + counter + "/" + total);
        questionText = new Label("");
        
        
        Button btn = new Button("NEXT");
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if(counter < total) {
                    counter++;
                    question.setText("Question " + counter + "/" + total);
                    if (group.getSelectedToggle() != null) {
                        controller.checkAnswer(group.getSelectedToggle().getUserData().toString());
                    } 
                    controller.nextQuestion();
                }
                else {
                    // update DB          
                    controller.displayScoreScene();
                }
            }
        });
        
        grid.add(question, 0, 0);
        grid.add(questionText, 0, 1);
        grid.add(btn, 0, 3);
        
        scene = new Scene(grid, 600, 300);
        
    }
    
    public void setQuestion(Question q) {
        questionText.setText(q.getQuestionText());
        grid.getChildren().remove(box);
        group = new ToggleGroup();
        box = new HBox();
        for(String ans : q.getAllAnswers()) {
            RadioButton rb = new RadioButton(ans);
            rb.setToggleGroup(group);
            rb.setUserData(ans);
            box.getChildren().add(rb);
        }
        grid.add(box, 0, 2);
    }
    
}