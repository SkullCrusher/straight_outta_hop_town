package package_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import package_1.Data_Manager.Feed;

public class FileIO {

	public static ArrayList<Feed> LoadFeeds(){
		   FileInputStream in = null;
		   
		   in = new FileInputStream("input.txt");
		        
		   String value; 
		   while ((value = in.read()) != -1) {
			   
		   }
		     
		         
		         if (in != null) {
		            in.close();
		         }
		       
		
		return null;
	}
	
	public static void DumpFeeds(ArrayList<Feed> arg){
		
		  FileOutputStream out = null;
		  
		  try {
			out = new FileOutputStream("output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		if (out != null) {
	       try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
		
		int c;
        
        
        out.write(c);
	}
	
}
