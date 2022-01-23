package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import storage.StorageCreation;
import user.CurrentUser;

public final class LocalFileBasicOperations implements FileBasicOperations{

	@Override
	public void uploadFile(CurrentUser user,String path, String storagePath, String name, String... metapodaci) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
		if(storagePath.contains(user.getCurrentStorage())){
		if(!user.getPrivilegije().contains("upload")){
			System.out.println("Korisnik nema pravo da uploaduje podatke na bazu");
		}else{
		String extension = "";
		int j = name.lastIndexOf('.');
		if (j >= 0) {
		   extension = name.substring(j+1);
		}

		if(new File(storagePath+name).exists()) {
		System.out.println("A file with this name already exists");
		}else if(extension.equals("exe")){
		System.out.println("Fajl je .exe\nPrekinuto uploadovanje fajla.");
		}else if(extension.equals("jar")){
		System.out.println("Fajl je .jar\nPrekinuto uploadovanje fajla.");
		}else{
		File file = new File(storagePath+name);
		file.getParentFile().mkdir();
		file.createNewFile();

		try {
		byte[] buffer = new byte[1024];
		

		FileInputStream fis = new FileInputStream(path);

		FileOutputStream fos = new FileOutputStream(file);


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
		for(int i = 0; i<metapodaci.length; i++) {
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

			
		}
	}
	}
}






	@Override
	public void downlaodFile(String path, String storagePath) throws FileNotFoundException {
		// TODO Auto-generated method stub
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





	@Override
	public File[] search(String dirPath, String criteria){
		
		File[] results;
		
		File file = new File(dirPath);
		FilenameFilter filter = new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return name.startsWith(criteria);
			}
		};
		
		String[] children = file.list(filter);
		results = file.listFiles(filter);
		
	      if (children == null) {
	         System.out.println("Direktorijum ne postoji ili nije direktorijum."); 
	      } else { 
	         for (int i = 0; i< children.length; i++) {
	            String filename = children[i];
	            System.out.println(filename);
	         } 
	      }
	      
		//System.out.println(results);
		return results;
		
	}





	@Override
	public void deleteFile(CurrentUser currentUser, String dirPath) {
		
		if(currentUser.getPrivilegije().contains("delete") && dirPath.contains(currentUser.getCurrentStorage())){
			File fileToDelete = new File(dirPath);
			fileToDelete.delete();
		}else System.out.println("Trenutni korisnik nema pravo da brise fajl");
		
		
	}

		
	}

	
		



