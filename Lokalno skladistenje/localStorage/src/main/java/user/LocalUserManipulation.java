package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocalUserManipulation implements UserManipulation{

	public LocalUserManipulation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CurrentUser logIn(String username, String password, String directory) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		try {
			FileReader fr = new FileReader(directory+"\\users.json");
			BufferedReader br = new BufferedReader(fr);
			try {
				JSONObject obj = (JSONObject) parser.parse(br);
				if(obj.containsKey(username) && obj.containsValue(password)){
					FileReader fpr = new FileReader(directory+"\\privilegije.json");
					BufferedReader bpr = new BufferedReader(fpr);
					obj = (JSONObject) parser.parse(bpr);
					CurrentUser cu = new LocalCurrentUser(username, password, (JSONArray)obj.get(username), directory);
					System.out.println(cu.getCurrentStorage());
					return cu;
					
				}else{
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void addUser(CurrentUser currentUser, String username, String password, String directory, String... privileges) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		
		FileReader fr = new FileReader(directory+"\\users.json");
		BufferedReader br = new BufferedReader(fr);
		JSONObject obj = (JSONObject) parser.parse(br);
		JSONArray privilegije = new JSONArray();
		for(int i = 0; i<privileges.length; i++) {
			privilegije.add(privileges[i]);
		
		//Object obj = parser.parse(new FileReader(directory+"\\users.json"));
		
		//JSONObject jsonObject = (JSONObject) obj;
		
		//if(obj.containsKey(username)){
			//System.out.println("Korisnik vec postoji");
		//}else{
			obj.put(username, password);
			FileWriter fw = new FileWriter(directory+"\\users.json");
			fw.write(obj.toJSONString());
			fw.close();
			
			FileReader pr = new FileReader(directory+"\\privilegije.json");
			BufferedReader bpr = new BufferedReader(pr);
			
			JSONObject obj2 = (JSONObject) parser.parse(bpr);
			
			obj2.put(username, privilegije);
			FileWriter pwr = new FileWriter(directory+"\\privilegije.json");
			BufferedWriter bpwr = new BufferedWriter(pwr);
			
			bpwr.write(obj2.toJSONString());
			
			bpwr.close();
			
		}
		//}
	}
}
