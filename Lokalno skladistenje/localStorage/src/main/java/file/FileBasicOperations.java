package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class FileBasicOperations {
	
	public static void uploadFile(String path,String storagePath, String name, String... metapodaci) throws IOException,org.json.simple.parser.ParseException{
		//if(new File(storagePath).exists()) {
			//System.out.println("A file with this name already exists");
		//}else{
			File file = new File(storagePath+name);
			file.getParentFile().mkdir();
			file.createNewFile();
			try {
			byte[] buffer = new byte[1024];
			
			FileOutputStream fos = new FileOutputStream(file);
			
			FileInputStream fis = new FileInputStream(path);
				
			int length;
			while((length = fis.read(buffer))>0){
					fos.write(buffer,0,length);
				}
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONArray metadata = new JSONArray();
			for(int i =0;i<metapodaci.length;i++) {
			metadata.add(metapodaci[i]);
			}
			
			
			JSONParser parser = new JSONParser();
			FileReader fr = new FileReader(storagePath+"\\metadata.json");
			JSONObject obj = (JSONObject) parser.parse(fr);
			
			
			obj.put(name, metadata);
			
			FileWriter metadatafr = new FileWriter(storagePath+"\\metadata.json");
			BufferedWriter metadatabr = new BufferedWriter(metadatafr);
			metadatabr.write(obj.toJSONString());
			metadatabr.close();
			metadatafr.close();
			System.out.println(metadata);
			

		}

	//}

	
	public void downloadFile(String path, String storagePath) throws FileNotFoundException{
		File file = new File(storagePath);
		File selectedFile = new File(storagePath);
		
		try {
		byte[] buffer = new byte[1024];
		
		FileOutputStream fos = new FileOutputStream(selectedFile);
		
		FileInputStream fis = new FileInputStream(file);
		
		int length;
		

			while((length = fis.read(buffer))>0){
				fos.write(buffer,0,length);
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
