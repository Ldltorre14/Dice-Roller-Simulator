package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.ArrayList;


public class App extends Application{
	
	//Variables
	private ArrayList<User> users;
	private Pane root;
	private Scene scene;
	private Button playButton;
	private Button signUpButton;
	private Button logInButton;
	private Button rankButton;
	private Label loggedInUsername;
	private User loggedInUser;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initVariables();
			initAppConfig(primaryStage);
			initButtons(this.root,primaryStage);
			render(primaryStage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//Initializers
		private void initVariables() {
			this.root = new Pane();
			this.scene = new Scene(this.root,1080,720);
			this.users = new ArrayList<User>();
			this.users.add(new User("player1","123456789"));
			this.users.add(new User("player2","987654321"));
			this.loggedInUsername = new Label();
		}
		
		private void initAppConfig(Stage primaryStage) {
			this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(this.scene);
			primaryStage.setTitle("DICE ROLLER");
			
		}
	
		private void initButtons(Pane root,Stage primaryStage) {
			this.playButton = new Button("ROLL THE DICE!");
			playButton.setId("playButton");
			playButton.setLayoutX(400);
			playButton.setLayoutY(600);
			
			this.signUpButton = new Button("Sign In");
			signUpButton.setId("signInButton");
			signUpButton.setLayoutX(50);
			signUpButton.setLayoutY(25);
			signUpButton.setOnAction(e -> {
				callSignUp(primaryStage,this.users);
			});
			
			this.logInButton = new Button("Log In");
			logInButton.setId("logInButton");
			logInButton.setLayoutX(120);
			logInButton.setLayoutY(25);
			logInButton.setOnAction(event -> {
				callLogIn(primaryStage);
			});
			
			this.rankButton = new Button("RANKING");
			rankButton.setId("rankButton");
			rankButton.setLayoutX(850);
			rankButton.setLayoutY(300);
			rankButton.setOnAction(event -> {
				callRanking(primaryStage);
			});
			
			this.loggedInUsername.setId("Log_loggedInUsername");
			this.loggedInUsername.setLayoutX(500);
			this.loggedInUsername.setLayoutY(500);
			
			
			this.root.getChildren().add(playButton);
			this.root.getChildren().add(signUpButton);
			this.root.getChildren().add(logInButton);
			this.root.getChildren().add(rankButton);
			this.root.getChildren().add(loggedInUsername);
		}
		
		public void callSignUp(Stage primaryStage, ArrayList<User> users) {
			signUpWindow signUp = new signUpWindow();
		    signUp.signUp(users);  // Mueve la llamada al método signIn antes de show
		    signUp.show(primaryStage);

		}
		
		public void callLogIn(Stage primaryStage) {
			logInWindow logIn = new logInWindow();
			logIn.logIn(this.users);
		    this.loggedInUser = logIn.getLoggedInUser();
		    
		    System.out.println("User:"+loggedInUser.getUsername()+"\nPassword:"+loggedInUser.getPassword());
		    
		    if (loggedInUser != null) {
		        signUpButton.setVisible(false);
		        logInButton.setVisible(false);

		        this.loggedInUsername.setText("Welcome, " + loggedInUser.getUsername() + "!");
		    }

		    logIn.show(primaryStage);
			
		}
		
		public void callRanking(Stage primaryStage) {
			rankingWindow ranking = new rankingWindow();
			ranking.show(primaryStage);
		}
	
		
		//Methods/Functions
		public void render(Stage primaryStage) {
			primaryStage.show();
		}
		
		public static void run(String[] args) {
			launch(args);
		}

}
