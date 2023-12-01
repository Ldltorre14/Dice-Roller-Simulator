package application;


//Simple class for managing user accounts on App.java for logIn/SignIn
public class User {
	
	private String username;
	private String password;
	private int diceHistory[];
	
	//Constructor
	public User() {
		this.username = "";
		this.password = "";
		this.diceHistory = new int[100];
		}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//Getters
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	
	//Setters
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
