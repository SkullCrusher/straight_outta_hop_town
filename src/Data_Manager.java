package package_1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import package_1.ToolBar_Config_Settings.MyTableModel;

public class Data_Manager implements Observer {
	
	final class Feed{
		String RSS_FEED = ""; // This is the state code not the state name.
		int Refresh_Delay = 5000;
		boolean Enabled = true;
	}
	
		// A container for all of the feeds that have been added to the map.
	public static ArrayList<Feed> RSS_FEEDS = new ArrayList();
	
		// TO DO JORDAN: This takes a full name of the RSS feed and returns the state code so it will go into 
	private String StateToStateCode(String arg){
		
		if(arg == "Alaska"){
			return "ak";
		}
		
		if(arg == "Arizona"){
			return "az";
		}
		
			// debugging.
		return "ak";
	}
	
	
	public void addFeed (String areaCode){
		//you need to pass in the parser that is referencing the WorldWindow
		R_Parser parser = new R_Parser();
		parser.Parse(areaCode);
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
