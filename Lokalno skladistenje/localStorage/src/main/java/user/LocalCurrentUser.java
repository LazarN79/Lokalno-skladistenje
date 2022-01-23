package user;

import org.json.simple.JSONArray;

public class LocalCurrentUser extends CurrentUser{
	
	public LocalCurrentUser(String username, String password, JSONArray privilegije, String currentStorage) {
		super(username, password, privilegije, currentStorage);
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JSONArray getPrivilegije() {
		return privilegije;
	}
	public void setPrivilegije(JSONArray privilegije) {
		this.privilegije = privilegije;
	}
	public String getCurrentStorage() {
		return currentStorage;
	}
	public void setCurrentStorage(String currentStorage) {
		this.currentStorage = currentStorage;
	}
	@Override
	public String toString() {
		return "LocalCurrentUser [username=" + username + ", password=" + password + ", privilegije=" + privilegije
				+ "]";
	}
	
	
	
	
	
	
}
