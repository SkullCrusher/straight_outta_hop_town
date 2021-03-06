package package_1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Data_Manager implements Observer {
	private Plotter plotter;
	
	Data_Manager(){
		this.plotter = new Plotter();
		RequestThread rt = new RequestThread();
		RefreshThread rft = new RefreshThread();
		R_Parser parser = new R_Parser(plotter);
		rt.setParser(parser);
		rft.setParser(parser);
	}
	
	final class Feed{
		String RSS_FEED = ""; // This is the state code not the state name.
		int Refresh_Delay = 5;
		boolean Enabled = true;
	}
	
		// A container for all of the feeds that have been added to the map.
	public ArrayList<Feed> RSS_FEEDS = new ArrayList<Feed>();
	public ArrayList<RefreshThread> REF_THREADS = new ArrayList<RefreshThread>();
	
	public int GetRSSSize(){
		return RSS_FEEDS.size();
	}
	
		// Gets the delay of a RSS_Feed.
	public String GetString(int index){
		return RSS_FEEDS.get(index).RSS_FEED;
	}
	
		// Gets the delay of a index.
	public int GetDelay(int index){
		return RSS_FEEDS.get(index).Refresh_Delay;
	}
	
	
		// Allows outside feeds to be added.
	public void AddRSSFeedFromFile(String name, int delay){
		Feed Temp = new Feed();
		
		
		Temp.Refresh_Delay = delay;
		Temp.RSS_FEED = name;
		Temp.Enabled = true;
		
		RSS_FEEDS.add(Temp);
	}
	
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
		
	public void Rerender(){
	//	Plotter plotter = new Plotter();
	    plotter.clearMap();
		
		for(int i = 0; i < RSS_FEEDS.size(); i++){	
			
			if(RSS_FEEDS.get(i) == null){
				continue;
			}
		//	System.out.println(RSS_FEEDS.get(i).RSS_FEED);
			if(RSS_FEEDS.get(i).Enabled==true){
				RequestThread rt = new RequestThread();
				rt.setAreaCode(RSS_FEEDS.get(i).RSS_FEED);
				rt.start();//thread to load the data initially
				RefreshThread refT = new RefreshThread();
				refT.setThreadName(RSS_FEEDS.get(i).RSS_FEED);
				refT.setSleepTime(RSS_FEEDS.get(i).Refresh_Delay);
				refT.start();//thread to keep refreshing the data
				//only one refresh thread per rss feed
				removeIfExists(REF_THREADS, RSS_FEEDS.get(i).RSS_FEED);
				REF_THREADS.add(refT);
			}
		}
	}
	
	public void makeCustomRequst(String city, String state ){}

	public static void removeIfExists(ArrayList<RefreshThread> refList, String name){
		for (int i=0; i<refList.size(); i++){
			if(refList.get(i).getThreadName().equals(name)){
				refList.get(i).interrupt();
				refList.get(i).setRunning(false);//kind of a hack, but interrupt wasn't working for me	
				refList.remove(refList.indexOf(refList.get(i)));
				return;
			}
		}
	}
	
		// This resets the search settings.
	private void Search_Reset(){
		plotter.resetFilters();
		Rerender();
		//System.out.println("hjk");
	}
		// Search settings.
	
	private String convertDates(String datetime){
		//System.out.println(datetime);
		String[] tmp = datetime.split(" ");

		switch(tmp[1]){
		case "January":
			tmp[1] = "1";
			break;
		case "February":
			tmp[1] ="2";
			break;
		case "March":
			tmp[1] = "3";
			break;
		case "April":
			tmp[1] = "4";
			break;
		case "May":
			tmp[1] = "5";
			break;
		case "June":
			tmp[1] ="6";
			break;
		case "July":
			tmp[1] ="7";
			break;
		case "August":
			tmp[1] = "8";
			break;
		case "September":
			tmp[1] = "9";
			break;
		case "October":
			tmp[1] = "10";
			break;
		case "November":
			tmp[1] = "11";
			break;
		case "December":
			tmp[1] = "12";
			break;
		}
		String date;
		String Month = tmp[1];
		String Day = tmp[2];
		String Year = tmp[5];
		String time = tmp[3];
		String ampm;
		String [] hms = time.split(":");
	    Integer h = Integer.parseInt(hms[0]);
	    if(h>12){
	    	h = h-12;
	    	ampm = "PM";
	    } else {
	    	ampm = "AM";
	    }
	    time = h.toString()+":"+hms[1]+" "+ampm;
		Year.replace("20", "");
		if(Day.startsWith("0")){
			Day = Day.substring(1, 2);
		}
		date =Day+"/"+Month+"/"+Year+" ";
		datetime = date+time;
		
		
		return datetime;
	}
	
	private void Search_Set(String State, String City, String BeforeDateTime , String AfterDateTime, boolean Severity_Severe, boolean Severity_Moderate, boolean Severity_Minor, boolean Severity_Unknown, boolean Urgency_Expected, boolean Urgency_Future, boolean Urgency_Immediate, boolean Urgency_Unknown){
		BeforeDateTime = convertDates(BeforeDateTime);
		AfterDateTime = convertDates(AfterDateTime);	
		plotter.setFilterParams(BeforeDateTime, AfterDateTime, Severity_Severe, Severity_Moderate, Severity_Minor, Severity_Unknown, Urgency_Expected, Urgency_Future, Urgency_Immediate, Urgency_Unknown);
//		if(!State.isEmpty()||!City.isEmpty()){
//			makeCustomRequst(State, City);
//		}
		Rerender();
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
						// Jordan thinks this works
					for(int i = 0; i < RSS_FEEDS.size(); i++){						
						if(RSS_FEEDS.get(i).RSS_FEED.equals((String) temp.RSS)){
							//remove all threads too
							removeIfExists(REF_THREADS, RSS_FEEDS.get(i).RSS_FEED);
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
						//System.out.println(RSS_Name + " " + RSS_FEEDS.get(g).Refresh_Delay + " " + RSS_FEEDS.get(g).Enabled);
						
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
			
				// Check if the user clicked the search or reset.
			if(temp.Reset){
				// Reset the search.
				 Search_Reset();				
			}else{
				
				String State = temp.textField1.getText(); // State
				String City = temp.textField2.getText(); // City
				 

				boolean Severity_Severe = temp.Severity_Severe.isSelected();
				boolean Severity_Moderate = temp.Severity_Moderate.isSelected();
				boolean Severity_Minor = temp.Severity_Minor.isSelected();
				boolean Severity_Unknown = temp.Severity_Unknown.isSelected();
				
				boolean Urgency_Expected = temp.Urgency_Expected.isSelected();
				boolean Urgency_Future = temp.Urgency_Future.isSelected();
				boolean Urgency_Immediate = temp.Urgency_Immediate.isSelected();
				boolean Urgency_Unknown = temp.Urgency_Unknown.isSelected();
				
				Date AfterDateTime = (Date)temp.AfterDateTime.getValue();
				Date BeforeDateTime = (Date)temp.AfterDateTime.getValue();

				String After = AfterDateTime.toString();
				String Before = BeforeDateTime.toString();
					
				
				Search_Set(State, City, Before, After, Severity_Severe, Severity_Moderate, Severity_Minor, Severity_Unknown, Urgency_Expected, Urgency_Future, Urgency_Immediate, Urgency_Unknown);				
			}			
		}
		
	
		
		
	}
	
}
