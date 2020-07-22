package quizfx.controllers;

import quizfx.LoginScene;
import quizfx.MainStage;
import quizfx.QuizScene;
import quizfx.ScoreScene;
import quizfx.SelectionScene;
import quizfx.models.Categories;
import quizfx.models.Question;
import quizfx.models.Quiz;

public class Controller {
    
    private MainStage mainStage;
    private LoginScene loginScene; 
    private SelectionScene selectionScene;
    private QuizScene quizScene;
    private ScoreScene scoreScene;
    private Quiz quiz;
    private Categories categories;
    
    public Controller(MainStage m) {
        mainStage = m;
        loginScene = new LoginScene();
        loginScene.setController(this);
        mainStage.setScene(loginScene.getScene());
    }
    
    public void displayQuizSelection() {
        categories = new Categories();
        categories.loadFromAPI();
        selectionScene = new SelectionScene(categories.getCategoryNames());
        selectionScene.setController(this);
        mainStage.setScene(selectionScene.getScene());
    }
    
    public void displayQuiz(String difficulty, String category) {
        quiz = new Quiz();
        int catID = categories.getCategoryID(category);
        quiz.loadFromAPI(catID, difficulty.toLowerCase());
        quizScene = new QuizScene(quiz.getNumberOfQuestions());
        quizScene.setController(this);
        nextQuestion();
        mainStage.setScene(quizScene.getScene());
    }
    
    public void displayScoreScene() {
        scoreScene = new ScoreScene(quiz.getScore(), quiz.getNumberOfQuestions());
        scoreScene.setController(this);
        mainStage.setScene(scoreScene.getScene());
    }
    
    public void checkAnswer(String ans) {
        quiz.setScore(ans);
        System.out.println("SCORE: " + quiz.getScore());
    }
    
    public void nextQuestion() {
        Question q = quiz.getNextQuestion();
        quizScene.setQuestion(q);
    }
    
}
