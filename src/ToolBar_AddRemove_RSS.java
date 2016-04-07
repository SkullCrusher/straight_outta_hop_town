package package_1;



import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ToolBar_AddRemove_RSS extends Observable {

	public String RSS = "";
	public boolean Add = false;
	public boolean Remove = false;
	
	public ToolBar_AddRemove_RSS(){
		windowOpen = false;
	}
	
	// If the window is currently open.
	private boolean windowOpen;
	
		// Any cleanup that needs to happen plus allow a new one be opened.
	private void OnCloseEvent(){
		windowOpen = false;
	}
	
	private String GetCodeFromIndex(int arg){
		
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
		
		if((arg < 0) || (arg >= 50)){
			return "";
		}else{
			return Codes[arg];
		}
		
	}
	
	// Add the RSS feed to the data manager.
	private void AddRSS(int index){
		
		String Code = GetCodeFromIndex(index);
		
		RSS = Code;
		Add = true;
		
		// Add directly to the data manager.
		setChanged();
	    notifyObservers(Code);
	}
	
	// Remove the RSS feed from the data manager.
	private void RemoveRSS(int index){
		
		String Code = GetCodeFromIndex(index);
		
		RSS = Code;
		Add = true;
		
		// Add directly to the data manager.
		setChanged();
	    notifyObservers(Code);
	}
	
	public void Create() {
		
			// Prevent multiple windows from being opened.
		if(windowOpen){ return; }
		
		JFrame frame = new JFrame("Add/Remove RSS Feeds");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel controls = new JPanel();
		
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		controls.setAlignmentY(Component.TOP_ALIGNMENT);


          	// Add/Remove Feed (button) - Calls the tool bar element to open a JFrame of Add/Remove Feed.
        JPanel CS_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
        CS_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
          
		
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
		
		
		JComboBox Select = new JComboBox(RSS_List);
		
		CS_Panel.add(Select);
		
		JButton Add    = new JButton("Add Feed");
		JButton Remove = new JButton("Remove Feed");
        
    	// The function to be called when the button is pressed.
		Add.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent event){                	
			   AddRSS(Select.getSelectedIndex());
			   
			   OnCloseEvent();
			   frame.dispose();			   			 
		   }
		});
		
		Remove.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent event){                	
				   AddRSS(Select.getSelectedIndex());
				   
				   OnCloseEvent();
				   frame.dispose();			   			 
			   }
			});
		
		 CS_Panel.add(Add);
		 CS_Panel.add(Remove);
		 
		controls.add( CS_Panel);
		    
		frame.add(controls);
		
		
			// Reset the window open flag so another dialog can be opened. 
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	OnCloseEvent();
		    }
		});
		
			// Configure and display the dialog.
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);				
		frame.setSize(300,150);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		frame.setVisible(true);
				
		windowOpen = true;
	}
}
