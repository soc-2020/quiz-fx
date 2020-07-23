package quizfx.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class Quiz {
    
    private ArrayList<Question> questions;
    private int score;
    private int index;
    private int catID;
    private String difficulty;
    
    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        index = -1;
    }
    
    public void loadFromAPI(int catID, String difficulty) {
        this.catID = catID;
        this.difficulty = difficulty;
        
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
        q = Jsoup.parse(q).text();
        String ca = json.get("correct_answer").toString();
        ca = Jsoup.parse(ca).text();
        JSONArray wrongAnswers = json.getJSONArray("incorrect_answers");
        ArrayList<String> wa = new ArrayList<>();
        for (int j = 0; j < wrongAnswers.length(); j++) {
            String ans = wrongAnswers.get(j).toString();
            ans = Jsoup.parse(ans).text();
            wa.add(ans);
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
    
    
    public void save(User user) {
        try {
            Connection con = DBConn.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO scores (category, difficulty, score, users_id, score_date) " +
                    "VALUES (" + catID + ", '" + difficulty + "', " + score + ", " + user.getId() + ", NOW())");
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
}
