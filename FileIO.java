package package_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileIO {

	public static void LoadFeeds(Data_Manager arg, ToolBar bar){
		
		 String current = System.getProperty("user.dir");
	      System.out.println("Current working directory in Java : " + current);


	      ArrayList<String> Lines = new ArrayList<String>();
		
	      try {
				File file = new File(current + "\\User_Configuration.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					Lines.add(line);
				}
				fileReader.close();
			} catch (IOException e) {
				return;
			}

	      if(Lines.size() % 2 != 0){
	    	  return;
	      }
	      
	      
	      for(int i = 0; i < Lines.size() - 1; i += 2){	    	  
	    	  String RSS_FEED = Lines.get(i); // This is the state code not the state name.
	    	  
	    	  int Refresh_Delay = 0;
	    	  
	    	  try{
	    		  Refresh_Delay = Integer.parseInt(Lines.get(i + 1));
	    	  }catch(Exception e){	    		 
	    		  return;  // Invalid file so reject.
	    	  }
	    	  bar.Add_Feed_From_File(RSS_FEED);    	  
	    	  //arg.AddRSSFeedFromFile(RSS_FEED, Refresh_Delay);
	      }	   
	      
		return;
	}
	
	public static void DumpFeeds(Data_Manager arg){
			
		String current = System.getProperty("user.dir");

	    PrintWriter writer = null;
	    
		try {
			writer = new PrintWriter(current + "\\User_Configuration.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			return;
		} catch (UnsupportedEncodingException e) {
			return;
		}
		
		for(int i = 0; i < arg.GetRSSSize();i++){			
	     	writer.println(arg.GetString(i));
	     	writer.println(Integer.toString(arg.GetDelay(i)));	      
		}
		
		writer.close();
	}
	
}
