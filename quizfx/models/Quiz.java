package quizfx.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz {
    
    private ArrayList<Question> questions;
    private int score;
    private int index;
    
    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        index = -1;
    }
    
    public void loadFromAPI(int catID, String difficulty) {
        
        //&type=multiple 
        //&type=boolean
        
        loadQuestions("https://opentdb.com/api.php?amount=5&category=" + 
                    catID + "&difficulty=" + difficulty + "&type=boolean");
        loadQuestions("https://opentdb.com/api.php?amount=5&category=" + 
                    catID + "&difficulty=" + difficulty + "&type=multiple");

    }
    
    private void loadQuestions(String url) {
        InputStream inputAPI = null;
        try {
            inputAPI = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputAPI, Charset.forName("UTF-8")));
            String line = reader.readLine();
            JSONObject json = new JSONObject(line);
            JSONArray results = json.getJSONArray("results");
            System.err.println(results.length());
            for (int i = 0; i < results.length(); i++) {
                questions.add(getQuestionFromJSON(results.getJSONObject(i)));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputAPI.close();
            } catch (IOException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Question getQuestionFromJSON(JSONObject json) {
         
        String q = json.get("question").toString();
        String ca = json.get("correct_answer").toString();
        JSONArray wrongAnswers = json.getJSONArray("incorrect_answers");
        ArrayList<String> wa = new ArrayList<>();
        for (int j = 0; j < wrongAnswers.length(); j++) {
            wa.add(wrongAnswers.get(j).toString());
        }
        
        Question question = new Question(q, ca, wa);
        
        return question;
    } 
    
    public Question getNextQuestion() {
        if(hasMoreQuestions()) {
            index++;
            return questions.get(index);
        }
        else {
            return null;
        }
    }
    
    public boolean hasMoreQuestions() {
        return index < questions.size();
    }
    
    public void setScore(String answer) {
        if(answer.equals(questions.get(index).getCorrectAnswer())) {
            score++;
        }
    }
    
    public int getScore() {
        return score;
    }
    
    public int getNumberOfQuestions() {
        return questions.size();
    }
    
    
    public void save() {
        // open DB connection
        
        // save current score and user
        
    }
      
}
