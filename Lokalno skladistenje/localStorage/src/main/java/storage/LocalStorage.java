package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import user.CurrentUser;

public class LocalStorage implements StorageCreation{
	
	String path;

	public LocalStorage(String path) {
		this.path = path;
	}
	
	
	@Override
	public String getPath() {
		return path;
	}


	@Override
	public void setPath(String path) {
		this.path = path;
	}



	@Override
	public void createStorage(CurrentUser currentUser, String storagePath) throws IOException{
		if(currentUser.getPrivilegije().contains("storage creation")){
		if(new File(storagePath).exists()){
			System.out.println("A storage with this path already exists");
		}else {
			
			File storage = new File(storagePath);
			storage.mkdir();
			
			JSONObject metadata = new JSONObject();
			
			JSONObject jusers = new JSONObject();
			FileWriter userWriter = new FileWriter(storagePath+"\\users.json");
			jusers.put(currentUser.getUsername(), currentUser.getPassword());
			userWriter.write(jusers.toJSONString());
			userWriter.close();
			FileWriter metadataWriter = new FileWriter(storagePath+"\\metadata.json");
			metadataWriter.write(metadata.toJSONString());
			metadataWriter.close();
			
			JSONObject privilegije = new JSONObject();
			JSONArray adminPrivilegije = new JSONArray();
			adminPrivilegije.add("storage creation");
			adminPrivilegije.add("add user");
			adminPrivilegije.add("delete");
			adminPrivilegije.add("upload");
			privilegije.put(currentUser.getUsername(), adminPrivilegije);
			File privilages = new File(storagePath+"\\privilegije.json");
			privilages.createNewFile();
			FileWriter pwriter = new FileWriter(storagePath+"\\privilegije.json");
			pwriter.write(privilegije.toJSONString());
			pwriter.close();
		}
		}else System.out.println(currentUser.getUsername()+" can't create new storage");

}

	@Override
	public void deleteStorage(String path) {
		File storage = new File(path);
		String[] items = storage.list();
		for(String s: items){
		    File currentFile = new File(storage.getPath(),s);
		    currentFile.delete();
		}
		
	}


	@Override
	public void createMainStorage(String storagePath) throws IOException {
		// TODO Auto-generated method stub
					
					File storage = new File(storagePath);
					storage.mkdir();
					
					JSONObject metadata = new JSONObject();
					
					JSONObject jusers = new JSONObject();
					jusers.put("Admin", "Admin");
					FileWriter userWriter = new FileWriter(storagePath+"\\users.json");
					userWriter.write(jusers.toJSONString());
					userWriter.close();
					FileWriter metadataWriter = new FileWriter(storagePath+"\\metadata.json");
					metadataWriter.write(metadata.toJSONString());
					metadataWriter.close();
					
					JSONObject privilegije = new JSONObject();
					JSONArray adminPrivilegije = new JSONArray();
					adminPrivilegije.add("storage creation");
					adminPrivilegije.add("add user");
					adminPrivilegije.add("delete");
					adminPrivilegije.add("upload");
					privilegije.put("Admin", adminPrivilegije);
					File privilages = new File(storagePath+"\\privilegije.json");
					privilages.createNewFile();
					FileWriter pwriter = new FileWriter(storagePath+"\\privilegije.json");
					pwriter.write(privilegije.toJSONString());
					pwriter.close();
				}
	}


