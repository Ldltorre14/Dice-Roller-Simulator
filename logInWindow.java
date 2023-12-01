package application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import java.util.ArrayList;

public class logInWindow {
	private Stage logInStage;
	private Button closeButton;
	private Button logInButton;
	private Pane root;
	private Scene logInScene;
	private Label title;
	private Label username;
	private Label password;
	private PasswordField passwordField;
	private TextField usernameField;
	private Rectangle rectFrame;
	private User loggedInUser;
	
	public logInWindow() {
		this.logInStage = new Stage();
		
		this.rectFrame = new Rectangle(100,75,300,300);
		this.rectFrame.setId("log_rectFrame");
		//this.rectFrame.setFill(Color.web("#E9F9F6"));
		
		this.closeButton = new Button("Close");
		this.closeButton.setId("log_closeButton");
		this.closeButton.setLayoutX(420);
		this.closeButton.setLayoutY(50);
		
		this.logInButton = new Button("Log In");
		this.logInButton.setId("log_logInButton");
		this.logInButton.setLayoutX(230);
		this.logInButton.setLayoutY(330);
		
		this.title = new Label("Log In");
		this.title.setId("log_TitleLabel");
		this.title.setLayoutX(210);
		this.title.setLayoutY(100);
		
		this.username = new Label("Username");
		this.username.setId("log_UsernameLabel");
		this.username.setLayoutX(115);
		this.username.setLayoutY(150);
		this.usernameField = new TextField();
		this.usernameField.setId("log_UsernameField");
		this.usernameField.setLayoutX(115);
		this.usernameField.setLayoutY(175);
		
		this.password = new Label("Password");
		this.password.setId("log_PasswordLabel");
		this.password.setLayoutX(115);
		this.password.setLayoutY(250);
		this.passwordField = new PasswordField();
		this.passwordField.setId("log_UsernameField");
		this.passwordField.setLayoutX(115);
		this.passwordField.setLayoutY(275);
		
		
		this.root = new Pane();
		this.root.getChildren().add(rectFrame);
		this.root.getChildren().add(closeButton);
		this.root.getChildren().add(title);
		this.root.getChildren().add(username);
		this.root.getChildren().add(password);
		this.root.getChildren().add(usernameField);
		this.root.getChildren().add(passwordField);
		this.root.getChildren().add(logInButton);
		
		this.logInScene = new Scene(root, 500, 700);
		this.logInScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		logInStage.setScene(logInScene);
		logInStage.setTitle("Log In");
        
	}
	
	
	public void show(Stage primaryStage) {
        
		primaryStage.getScene().getRoot().setDisable(true);
		
		logInStage.setOnCloseRequest(event -> {
            // consume the event for disabling window's closing with 'X'
            event.consume();
        });

        closeButton.setOnAction(event -> {
            logInStage.close();
            primaryStage.getScene().getRoot().setDisable(false);
        });
		
       
        logInStage.showAndWait();
                        
    }
	
	public void logIn(ArrayList<User> users) {
		this.logInButton.setOnAction(event ->{
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			User aux_user = new User();
			aux_user.setUsername(this.usernameField.getText());
			aux_user.setPassword(this.passwordField.getText());
			
			/*System.out.println("User:"+aux_user.getUsername()+"\nPassword:"+aux_user.getPassword());
			for(User us : users) {
				System.out.println("RUser:"+us.getUsername()+"\nPassword"+us.getPassword());
			}*/
			
			
			if(this.usernameField.getText().isBlank() || this.passwordField.getText().isBlank()) {
				System.out.println("MISSING");
				alert.setTitle("Error!");
				alert.setHeaderText("Missing Fields");
				alert.setContentText("Missing information (Username/Password)\nPlease, fill the fields");
				alert.showAndWait();
			}
			else if(!isUser(aux_user,users)) {
				System.out.println("WRONG");
				alert.setTitle("Error!");
				alert.setHeaderText("Wrong Credentials");
				alert.setContentText("Username/Password might be wrong, please type the right credentials");
				alert.showAndWait();
			}
			else if(isUser(aux_user,users)){
				System.out.println("RIGHT");
				Alert succesfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
				succesfulAlert.setTitle("Log In");
				succesfulAlert.setHeaderText("Succesful");
				succesfulAlert.setContentText("Log In Succesful, you have accessed your account");
				succesfulAlert.showAndWait();
				this.loggedInUser = aux_user;
			}
			
			
		});
	}
	
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	public boolean isUser(User aux_user,ArrayList<User> users) {
		for(User us : users) {
			if(us.getUsername().equals(aux_user.getUsername()) && us.getPassword().equals(aux_user.getPassword())) {
				return true;
			}
		}
		
		return false;
	}

}
