package quizfx.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    
    private int id;
    private String username;
    private String password;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    public static User find(String username, String password) {
        try {
            Connection con = DBConn.getConnection();
            
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM users WHERE username='" + 
                    username + "' AND password=MD5('" + password + "')";
            System.err.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            
            if(result.next()) {
                User user = new User(result.getInt("id"), result.getString("username"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
