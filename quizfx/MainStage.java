package quizfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizfx.controllers.Controller;

public class MainStage extends Application {

    private Stage mainStage;
    private Controller controller;
    // private LoginScene loginScene; 
    // private QuizScene quizScene;
    // private ScoreScene scoreScene;
    
    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        controller = new Controller(this);
                
        mainStage.setTitle("QUIZ");

        mainStage.show();
    }
    
    public void setScene(Scene scene) {
        mainStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
/*
    @Override
    public void displayQuizScene() {
        quizScene = new QuizScene();
        quizScene.setController(this);
        setScene(quizScene.getScene());
    }

    @Override
    public void displayScoreScene() {
        scoreScene = new ScoreScene();
        scoreScene.setController(this);
        setScene(scoreScene.getScene());
    }
    */
}