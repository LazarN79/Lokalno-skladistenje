package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserManipulation {

	public UserManipulation() {
		// TODO Auto-generated constructor stub
	}
	
	public void addUser(String username, String password, String directory) throws FileNotFoundException, IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new FileReader(directory+"\\users.json"));
		
		JSONObject jsonObject = (JSONObject) obj;
		
		if(jsonObject.containsKey(username)){
			System.out.println("Korisnik vec postoji");
		}else{
			jsonObject.put(username, password);
			FileWriter fr = new FileWriter(directory+"\\users.json");
			fr.write(jsonObject.toJSONString());
			fr.close();
		}
		
	}

}
