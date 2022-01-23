package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LocalFileUtil implements FileUtil{

	public LocalFileUtil() {
	}
	@Override
	public void zip(String name, String archivePath,ArrayList<File> files){
		File zipFile = new File(archivePath+"\\"+name+".zip");
		try {
			zipFile.getParentFile().createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			byte[] buffer = new byte[1024];
			
			FileOutputStream fos = new FileOutputStream(zipFile);
			
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for(File item: files){
				File srcFile = new File(item.getAbsolutePath());
				FileInputStream fis = new FileInputStream(srcFile);
				
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				
				int length;
				while((length = fis.read(buffer))>0){
					zos.write(buffer,0,length);
				}
				item.getAbsoluteFile().delete();
				zos.closeEntry();
				fis.close();
			}
			zos.close();
		}
		catch (IOException e) {
			System.out.println("Error creating zip file: "+e);
		}
	 }
	

}
