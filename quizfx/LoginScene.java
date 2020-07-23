package quizfx;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginScene extends GenericScene {
    
    private TextField usernameField;
    private PasswordField passwordField;
    
    public LoginScene() {
        
        GridPane grid = new GridPane(); 
        
        Text title = new Text("Login: ");
        usernameField = new TextField();
        passwordField = new PasswordField();
        
        Button loginBtn = new Button("LOGIN");
        loginBtn.setOnMouseClicked((MouseEvent arg0) -> {
            controller.authenticate(usernameField.getText(), passwordField.getText());
        });
        
        grid.add(title, 0, 0);
        grid.add(usernameField, 0, 1);
        grid.add(passwordField, 0, 2);
        grid.add(loginBtn, 0, 3);
        
        scene = new Scene(grid, 300, 300);
        
    }
    
}

