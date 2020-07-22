package quizfx.models;

import java.util.ArrayList;
import java.util.Collections;


public class Question {
    
    private String questionText;
    private String correctAnswer;
    private ArrayList<String> wrongAnswers;
    
    public Question(String text, String correct, ArrayList<String> wrong) {
        questionText = text;
        correctAnswer = correct;
        wrongAnswers = wrong;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }
    
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }
    
    public ArrayList<String> getAllAnswers() {
        ArrayList<String> all = new ArrayList<>();
        all.addAll(wrongAnswers);
        all.add(correctAnswer);
        Collections.shuffle(all);
        
        return all;
    }
}
