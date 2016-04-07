package package_1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;

public class Data_Manager implements Observer {
		
	private ArrayList<String> RSS_FEEDS = new ArrayList();
	
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
			parser.Parse(RSS_FEEDS.get(i));		
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
			// This is the area code or w.e.
		ToolBar_AddRemove_RSS temp = (ToolBar_AddRemove_RSS) arg1;
		
		
		String NEW_RSS_FEED = new String((String) temp.RSS);
		
		if(NEW_RSS_FEED != null){
		
			if(temp.Add == true){
			RSS_FEEDS.add(NEW_RSS_FEED);
			}
			if(temp.Remove == true){
				RSS_FEEDS.remove(NEW_RSS_FEED);
			}
			
			Rerender();
		}
	}
	
}
