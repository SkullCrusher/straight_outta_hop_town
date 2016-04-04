package package_1;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;

public class Data_Manager {

	public void addFeed (String areaCode){
		//you need to pass in the parser that is referencing the WorldWindow
		R_Parser parser = new R_Parser();
		parser.Parse(areaCode);
	}
	
}
