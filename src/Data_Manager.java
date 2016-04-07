package package_1;

import java.util.Observable;
import java.util.Observer;

public class Data_Manager implements Observer {

	public void addFeed (String areaCode){
		//you need to pass in the parser that is referencing the WorldWindow
		R_Parser parser = new R_Parser();
		parser.Parse(areaCode);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
			// This is the area code or w.e.
		String NEW_RSS_FEED = (String) arg1;
	}
	
}
