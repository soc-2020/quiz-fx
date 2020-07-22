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

public class Categories {
    
    private ArrayList<Category> categories;
    
    public Categories() {
        categories = new ArrayList<>();
    }
    
    public void loadFromAPI() {
        InputStream is = null;
        try {
            is = new URL("https://opentdb.com/api_category.php").openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            JSONObject json = new JSONObject(line);
            JSONArray data = json.getJSONArray("trivia_categories");
            for(int i = 0; i < data.length(); i++){
                
                JSONObject category = data.getJSONObject(i);
                int id = category.getInt("id");
                String name = category.get("name").toString();
                Category cat = new Category(id, name);
                categories.add(cat);
                
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<String> getCategoryNames() {
        ArrayList<String> names = new ArrayList<>();
        for(Category cat : categories) {
            names.add(cat.getName());
        }
        return names;
    }
    
    public int getCategoryID(String name) {
        for(Category cat : categories) {
            if(name.equals(cat.getName())) {
                return cat.getId();
            }
        }
        return 0;
    }
    
}
