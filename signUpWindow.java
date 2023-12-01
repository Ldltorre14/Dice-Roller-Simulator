package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.util.ArrayList;

public class signUpWindow {
	private Stage signUpStage;
	private Button closeButton;
	private Button signUpButton;
	private Pane root;
	private Scene signUpScene;
	private Label title;
	private Label username;
	private Label password;
	private PasswordField passwordField;
	private TextField usernameField;
	private Rectangle rectFrame;
	
	public signUpWindow() {
		this.signUpStage = new Stage();
		
		this.rectFrame = new Rectangle(100,75,300,300);
		this.rectFrame.setId("sign_rectFrame");
		//this.rectFrame.setFill(Color.web("#E9F9F6"));
		
		this.closeButton = new Button("Close");
		this.closeButton.setId("sign_closeButton");
		this.closeButton.setLayoutX(420);
		this.closeButton.setLayoutY(50);
		
		this.signUpButton = new Button("Sign Up");
		this.signUpButton.setId("sign_signUpButton");
		this.signUpButton.setLayoutX(230);
		this.signUpButton.setLayoutY(330);
		
		this.title = new Label("Sign Up");
		this.title.setId("sign_TitleLabel");
		this.title.setLayoutX(210);
		this.title.setLayoutY(100);
		
		this.username = new Label("Username");
		this.username.setId("sign_UsernameLabel");
		this.username.setLayoutX(115);
		this.username.setLayoutY(150);
		this.usernameField = new TextField();
		this.usernameField.setId("sign_UsernameField");
		this.usernameField.setLayoutX(115);
		this.usernameField.setLayoutY(175);
		
		this.password = new Label("Password");
		this.password.setId("sign_PasswordLabel");
		this.password.setLayoutX(115);
		this.password.setLayoutY(250);
		this.passwordField = new PasswordField();
		this.passwordField.setId("sign_UsernameField");
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
		this.root.getChildren().add(signUpButton);
		
		this.signUpScene = new Scene(root, 500, 700);
		this.signUpScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		signUpStage.setScene(signUpScene);
		signUpStage.setTitle("Sign Up");
	}
	
	
	public void show(Stage primaryStage) {
        
		primaryStage.getScene().getRoot().setDisable(true);
		
		signUpStage.setOnCloseRequest(event -> {
            // consume the event for disabling window's closing with 'X'
            event.consume();
        });

        closeButton.setOnAction(event -> {
            signUpStage.close();
            primaryStage.getScene().getRoot().setDisable(false);
        });
		
       
        signUpStage.showAndWait();
                        
    }
	
	public void signUp(ArrayList<User> users) {
		this.signUpButton.setOnAction(event ->{
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			User aux_user = new User();
			aux_user.setUsername(this.usernameField.getText());
			aux_user.setPassword(this.passwordField.getText());
			
			
			if(this.usernameField.getText().isBlank() || this.passwordField.getText().isBlank()) {
				System.out.println("MISSING");
				alert.setTitle("Error!");
				alert.setHeaderText("Missing Fields");
				alert.setContentText("Missing information (Username/Password)\nPlease, fill the fields");
				alert.showAndWait();
			}
			else if(isUnavailable(aux_user,users)) {
				System.out.println("UNAVAILABLE");
				alert.setTitle("Error!");
				alert.setHeaderText("Unavailable Username");
				alert.setContentText("Username already used\nPlease select another username");
				alert.showAndWait();
			}
			else {
				users.add(aux_user);
				Alert succesfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
				succesfulAlert.setTitle("Sign Up");
				succesfulAlert.setHeaderText("Succesful");
				succesfulAlert.setContentText("Sign Up Succesful, you have created an account");
				succesfulAlert.showAndWait();
			}
			
			
		});
	}
	
	public boolean isUnavailable(User aux_user,ArrayList<User> users) {
		for(User us : users) {
			if(us.getUsername().equals(aux_user.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
