package package_1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;

public class Data_Manager implements Observer {
	
	final class Feed{
		String RSS_FEED = ""; // This is the state code not the state name.
		int Refresh_Delay = 5000;
		boolean Enabled = true;
	}
	
		// A container for all of the feeds that have been added to the map.
	public static ArrayList<Feed> RSS_FEEDS = new ArrayList();
	
		// This takes a full name of the RSS feed and returns the state code.
	public String StateToStateCode(String arg){
					
		String Codes[] = {"us",
		"al", "ak", "as", "az",
		"ar", "ca", "co", "ct",
		"de", "dc", "fl", "ga",
		"gu", "hi", "id", "il",
		"in", "ia", "ks", "ky",
		"la", "me", "mp", "md",
		"ma", "mi", "um", "mn",
		"ms", "mo", "mt", "nv",
		"ne", "nh", "nj", "nm",
		"ny", "nc", "nd", "oh",
		"ok", "or", "pa", "pr",
		"ri", "sc", "sd", "tn",
		"tx", "ut", "vt", "vi",
		"va", "wa", "wv", "wi",
		"wy" };
		
		String[] RSS_List = {  
		"All", "Alabama", "Alaska",
		"America Samoa", "Arizona", "Arkansas",
		"California", "Colorado", "Connecticut",
		"Delaware", "District of Columbia", "Florida",
		"Georgia", "Guam", "Hawaii ", "Idaho", 
		"Illinois", "Indiana", "Iowa",
		"Kansas", "Kentucky", "Louisiana",
		"Maine", "Marianas", "Maryland",
		"Massachusetts", "Michigan", "Midway Island",
		"Minnesota", "Mississippi", "Missouri",
		"Montana", "Nevada", "Nebraska",
		"New Hampshire", "New Jersey", "New Mexico",
		"New York", "North Carolina", "North Dakota",
		"Ohio", "Oklahoma", "Oregon",
		"Pennsylvania", "Puerto Rico", "Rhode Island",
		"South Carolina", "South Dakota", "Tennessee",
		"Texas", "Utah", "Vermont", "Virgin Islands",
		"Virginia", "Washington", "West Virginia",
		"Wisconsin", "Wyoming"};
				
		// Find the index of the state code and return the correct state.		
		for(int i = 0; i < RSS_List.length; i++){
			if(arg.equals(RSS_List[i])){
				return Codes[i];						
			}
		}
			
		
			// fail safe.
		return "NULL";
	}
	
		// This takes a state code of the RSS feed and returns the state. 
	public String StateCodeToState(String arg){
				
		String Codes[] = {"us",
				"al", "ak", "as", "az",
				"ar", "ca", "co", "ct",
				"de", "dc", "fl", "ga",
				"gu", "hi", "id", "il",
				"in", "ia", "ks", "ky",
				"la", "me", "mp", "md",
				"ma", "mi", "um", "mn",
				"ms", "mo", "mt", "nv",
				"ne", "nh", "nj", "nm",
				"ny", "nc", "nd", "oh",
				"ok", "or", "pa", "pr",
				"ri", "sc", "sd", "tn",
				"tx", "ut", "vt", "vi",
				"va", "wa", "wv", "wi",
				"wy" };
				
				String[] RSS_List = {  
				"All", "Alabama", "Alaska",
				"America Samoa", "Arizona", "Arkansas",
				"California", "Colorado", "Connecticut",
				"Delaware", "District of Columbia", "Florida",
				"Georgia", "Guam", "Hawaii ", "Idaho", 
				"Illinois", "Indiana", "Iowa",
				"Kansas", "Kentucky", "Louisiana",
				"Maine", "Marianas", "Maryland",
				"Massachusetts", "Michigan", "Midway Island",
				"Minnesota", "Mississippi", "Missouri",
				"Montana", "Nevada", "Nebraska",
				"New Hampshire", "New Jersey", "New Mexico",
				"New York", "North Carolina", "North Dakota",
				"Ohio", "Oklahoma", "Oregon",
				"Pennsylvania", "Puerto Rico", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee",
				"Texas", "Utah", "Vermont", "Virgin Islands",
				"Virginia", "Washington", "West Virginia",
				"Wisconsin", "Wyoming"};
						
				// Find the index of the state and return the correct state code.				
				for(int i = 0; i < Codes.length; i++){
					if(arg.equals(Codes[i])){
						return RSS_List[i];
					}
				}
	
		
				// Fail safe.
		return "NULL";
	}
	
	
	
	
	public void addFeed (String areaCode){
		RequestThread rt = new RequestThread();
		rt.setAreaCode(areaCode);
		rt.start();
		System.out.println("oi");
		return;
	}
	
	private void Rerender(){
		Plotter plotter = new Plotter();
	    plotter.clearMap();
		
		R_Parser parser = new R_Parser();
		
		for(int i = 0; i < RSS_FEEDS.size(); i++){			
			parser.Parse(RSS_FEEDS.get(i).RSS_FEED);		
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
			// Check if it is a Add Remove request.
		if( arg1 instanceof ToolBar_AddRemove_RSS){
			
				// This is the area code or w.e.
			ToolBar_AddRemove_RSS temp = (ToolBar_AddRemove_RSS) arg1;			
			
			String NEW_RSS_FEED = new String((String) temp.RSS);
					
			if(NEW_RSS_FEED != null){		
				
				Feed temp_class = new Feed();
				temp_class.RSS_FEED = NEW_RSS_FEED;
				
				if(temp.Add == true){ RSS_FEEDS.add(temp_class); }
				if(temp.Remove == true){
					
						// This does not work for some reason.... maybe Jordan will know why.
					for(int i = 0; i < RSS_FEEDS.size(); i++){						
						if(RSS_FEEDS.get(i).RSS_FEED.equals((String) temp.RSS)){
							RSS_FEEDS.remove(i);
						}
					}	
				}
				
				Rerender();
			}
		}
		
		
		
			// Handles the change of the user settings.
		if(arg1 instanceof JTable){
				
				// Convert the argument so I don't have to reference it and cast it each time.
			JTable table = (JTable) arg1;		
				
				// Loop though each of the elements
			for(int i = 0; i < table.getModel().getRowCount(); i++){
				
					// Pull the table data.
				String RSS_Name = (String) table.getModel().getValueAt(i, 0);
				int Refresh_Time = (int) table.getModel().getValueAt(i, 1);
				boolean Enabled = (boolean) table.getModel().getValueAt(i, 2);
				
								
					// Get the state code.
				String State_Code = StateToStateCode(RSS_Name);
				
					// Look for the feed in the RSS_FEEDS and update it.
				for(int g = 0; g < RSS_FEEDS.size(); g++){						
					if(RSS_FEEDS.get(g).RSS_FEED.equals(State_Code)){
						
						RSS_FEEDS.get(g).Enabled = Enabled;
						RSS_FEEDS.get(g).Refresh_Delay = Refresh_Time;
						
							// Debugging.
						System.out.println(RSS_Name + " " + RSS_FEEDS.get(g).Refresh_Delay + " " + RSS_FEEDS.get(g).Enabled);
						
						break;
					}
				}	
			}		
		
			Rerender();
		}
		
		
			// Handle a search request.				
		if( arg1 instanceof ToolBar_Search_Button){
			
				// This is the area code or w.e.
			ToolBar_Search_Button temp = (ToolBar_Search_Button) arg1;			
			
		}
		
		
	}
	
}
