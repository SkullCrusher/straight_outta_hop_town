

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ToolBar_AddRemove_RSS {

	public ToolBar_AddRemove_RSS(){
		windowOpen = false;
	}
	
	// If the window is currently open.
	private boolean windowOpen;
	
		// Any cleanup that needs to happen plus allow a new one be opened.
	private void OnCloseEvent(){
		windowOpen = false;
	}
	
	public void Create() {
		
		if(windowOpen){
			return;
		}
		
		JFrame frame = new JFrame("Add/Remove RSS Feeds");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel controls = new JPanel();
		
		String[] RSS_List = {  
		 "All - 'us'",
		 "Alabama - 'al'",
		 "Alaska - 'ak'",
		 "America Samoa - 'as'",
		 "Arizona - 'az'",
		 "Arkansas - 'ar'",
		 "California - 'ca'",
		 "Colorado - 'co'",
		 "Connecticut - ct",
		 "Delaware - 'de'",
		 "District of Columbia - 'dc'",
		 "Florida - 'fl'",
		 "Georgia - 'ga'",
		 "Guam - 'gu'",
		 "Hawaii - 'hi'",
		 "Idaho - 'id'",
		 "Illinois - 'il'",
		 "Indiana - 'in'",
		 "Iowa - 'ia'",
		 "Kansas - 'ks'",
		 "Kentucky - 'ky'",
		 "Louisiana - 'la'",
		 "Maine - 'me'",
		 "Marianas - 'mp'",
		 "Maryland - 'md'",
		 "Massachusetts - 'ma'",
		 "Michigan - 'mi'",
		 "Midway Island - 'um'",
		 "Minnesota - 'mn'",
		 "Mississippi - 'ms'",
		 "Missouri - 'mo'",
		 "Montana - 'mt'",
		 "Nevada - 'nv'",
		 "Nebraska - 'ne'",
		 "New Hampshire - 'nh'",
		 "New Jersey - 'nj'",
		 "New Mexico - 'nm'",
		 "New York - 'ny'",
		 "North Carolina - 'nc'",
		 "North Dakota - 'nd'",
		 "Ohio - 'oh'",
		 "Oklahoma - 'ok'",
		 "Oregon - 'or'",
		 "Pennsylvania - 'pa'",
		 "Puerto Rico - 'pr'",
		 "Rhode Island - 'ri'",
		 "South Carolina - 'sc'",
		 "South Dakota - 'sd'",
		 "Tennessee - 'tn'",
		 "Texas - 'tx'",
		 "Utah - 'ut'",
		 "Vermont - 'vt'",
		 "Virgin Islands - 'vi'",
		 "Virginia - 'va'",
		 "Washington - 'wa'",
		 "West Virginia - 'wv'",
		 "Wisconsin - 'wi'",
		 "Wyoming - 'wy'"};
		
		
		JComboBox Select = new JComboBox(RSS_List);
		
		controls.add(Select);
		
		frame.add(controls);
		
		//frame.add(P);
		//frame.getContentPane().add(P);
				
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	OnCloseEvent();
		    }
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		frame.setSize(300,200);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		frame.setVisible(true);
				
		windowOpen = true;
	}
}
