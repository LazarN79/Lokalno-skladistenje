package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StorageCreation {

	public StorageCreation() {
	}
	
	public void createStorage(String storagePath) throws IOException{
			
			//if(new File(storagePath).exists()){
			//	System.out.println("A storage with this path already exists");
			//}else {
				
				File storage = new File(storagePath);
				storage.mkdir();
				
				File users = new File(storagePath+"\\users.json");
				users.createNewFile();
				
				ArrayList<String> a = new ArrayList();
				a.add("a");
				a.add("b");
				JSONArray jarray = new JSONArray();
				jarray.addAll(a);
				JSONObject metadata = new JSONObject();
				metadata.put("File name", jarray);
				
				
				FileWriter userWriter = new FileWriter(storagePath+"\\users.json");
				userWriter.close();
				FileWriter metadataWriter = new FileWriter(storagePath+"\\metadata.json");
				metadataWriter.write(metadata.toJSONString());
				metadataWriter.close();
			//}

	}

}
