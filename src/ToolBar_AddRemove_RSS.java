package package_1;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	static private ArrayList<String> RSS_Disabled_List = new ArrayList<String>();
	static private ArrayList<String> RSS_Enabled_List = new ArrayList<String>();
	
	public ToolBar_AddRemove_RSS(){
		windowOpen = false;
		String[] RSS_List = {  
				 "Alabama", "Alaska",
				 "America Samoa", "Arizona", "Arkansas",
				 "California", "Colorado", "Connecticut",
				 "Delaware", "District of Columbia", "Florida",
				 "Georgia", "Guam", "Hawaii", "Idaho", 
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
					
				if(RSS_Disabled_List.size()==0){
					for(int i=0; i<RSS_List.length; i++){
						RSS_Disabled_List.add(RSS_List[i]);
					}
				}
	
	}
	
	// If the window is currently open.
	private boolean windowOpen;
	
		// Any cleanup that needs to happen plus allow a new one be opened.
	private void OnCloseEvent(){
		windowOpen = false;
	}
	
	private String GetCodeFromName(String arg){
		switch (arg){
			case "Alabama":
				return "al";
			case "Alaska":
				return "ak";
			case "America Samoa":
				return "as";
			case "Arizona":
				return  "az";
			case "Arkansas":
				return "ar";
			case  "California":
				return "ca";
			case "Colorado":
				return "co";
			case "Connecticut":
				return "ct";
			case "Delaware":
				return "de";
			case "District of Columbia":
				return "dc";
			case "Florida":
				return "fl";
			case "Georgia":
				return "ga";
			case "Guam":
				return "gu";
			case "Hawaii":
				return "hi";
			case "Idaho":
				return "id";
			case "Illinois":
				return "il";
			case "Indiana":
				return "in";
			case "Iowa":
				return "ia";
			case "Kansas":
				return "ks";
			case "Kentucky":
				return "ky";
			case  "Louisiana":
				return "la";
			case "Maine":
				return "me";
			case "Marianas":
				return "mp";
			case "Maryland":
				return "md";
			case  "Massachusetts":
				return "ma";
			case "Michigan":
				return "mi";
			case "Midway Island":
				return "um";
			case "Minnesota":
				return "mn";
			case "Mississippi":
				return "ms";
			case "Missouri":
				return "mo";
			case "Montana":
				return "mt";
			case "Nevada":
				return "nv";
			case "Nebraska":
				return "ne";
			case "New Hampshire":
				return "nh";
			case "New Jersey":
				return "nj";
			case "New Mexico":
				return "nm";
			case "New York":
				return "ny";
			case "North Carolina":
				return "nc";
			case "North Dakota":
				return  "nd";
			case "Ohio":
				return "oh";
			case "Oklahoma":
				return "ok";
			case "Oregon":
				return "or";
			case "Pennsylvania":
				return "pa";
			case "Puerto Rico":
				return "pr";
			case "Rhode Island":
				return "ri";
			case "South Carolina":
				return "sc";
			case "South Dakota":
				return  "sd";
			case  "Tennessee":
				return  "tn";
			case "Texas":
				return "tx";
			case "Utah":
				return "ut";
			case "Vermont":
				return "vt";
			case "Virgin Islands":
				return "vi";
			case "Virginia":
				return "va";
			case "Washington":
				return"wa";
			case  "West Virginia":
				return "wv";
			case "Wisconsin":
				return "wi";
			case "Wyoming":
				return "wy";			
		}
		return "";
	}
	
	// Add the RSS feed to the data manager.
	private void AddRSS(int index){
		
		String Code = GetCodeFromName(RSS_Disabled_List.get(index));

		RSS_Enabled_List.add(RSS_Disabled_List.get(index));
		RSS_Disabled_List.remove(index);
		RSS_Enabled_List.sort(String.CASE_INSENSITIVE_ORDER);
		RSS = Code;
		Add = true;
		Remove = false;
		
		// Add directly to the data manager.
		setChanged();
	    notifyObservers(this);
	}
	
	// Remove the RSS feed from the data manager.
	private void RemoveRSS(int index){
		
		String Code = GetCodeFromName(RSS_Enabled_List.get(index));
		
		RSS_Disabled_List.add(RSS_Enabled_List.get(index));
		RSS_Enabled_List.remove(index);
		RSS_Disabled_List.sort(String.CASE_INSENSITIVE_ORDER);
		RSS = Code;
		Add = false;
		Remove = true;
		
		// Add directly to the data manager.
		setChanged();
	    notifyObservers(this);
	}
	
	public String Get_Name_From_Code(String cc){
		switch (cc){
		case "al":
			return "Alabama";
		case "ak":
			return"Alaska";
		case "as":
			return "America Samoa";
		case "az":
			return "Arizona";
		case "ar":
			return "Arkansas";
		case "ca":
			return "California";
		case "co":
			return "Colorado";
		case "ct":
			return "Connecticut";
		case "de":
			return "Delaware";
		case "dc":
			return "District of Columbia";
		case "fl":
			return "Florida";
		case "ga":
			return "Georgia";
		case "gu":
			return "Guam";
		case "hi":
			return "Hawaii";
		case "id":
			return "Idaho";
		case "il":
			return "Illinois";
		case "in":
			return "Indiana";
		case "ia":
			return "Iowa";
		case "ks":
			return "Kansas";
		case "ky":
			return "Kentucky";
		case  "la":
			return "Louisiana";
		case "me":
			return "Maine";
		case "mp":
			return "Marianas";
		case "md":
			return "Maryland";
		case  "ma":
			return "Massachusetts";
		case "mi":
			return "Michigan";
		case "um":
			return "Midway Island";
		case "mn":
			return "Minnesota";
		case "ms":
			return "Mississippi";
		case "mo":
			return "Missouri";
		case "mt":
			return "Montana";
		case "nv":
			return "Nevada";
		case "ne":
			return "Nebraska";
		case "nh":
			return "New Hampshire";
		case "nj":
			return "New Jersey";
		case "nm":
			return "New Mexico";
		case "ny":
			return "New York";
		case "nc":
			return "North Carolina";
		case "nd":
			return "North Dakota";
		case "oh":
			return "Ohio";
		case "ok":
			return "Oklahoma";
		case "or":
			return "Oregon";
		case "pa":
			return "Pennsylvania";
		case "pr":
			return "Puerto Rico";
		case "ri":
			return "Rhode Island";
		case "sc":
			return "South Carolina";
		case "sd":
			return "South Dakota";
		case "tn":
			return "Tennessee";
		case "tx":
			return "Texas";
		case "ut":
			return "Utah";
		case "vt":
			return "Vermont";
		case "vi":
			return "Virgin Islands";
		case "va":
			return "Virginia";
		case "wa":
			return "Washington";
		case "wv":
			return "West Virginia";
		case "wi":
			return "Wisconsin";
		case "wy":
			return "Wyoming";			
	}
	return "";
	}
	
	public void Add_Feed_From_File(String cc){
		String name = Get_Name_From_Code(cc);
		int index = RSS_Disabled_List.indexOf(name);
		//System.out.println(index+" "+name+" "+RSS_Disabled_List.get(1));
		AddRSS(index);
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
          

		
		JComboBox Select_Add = new JComboBox(RSS_Disabled_List.toArray());
		JComboBox Select_Remove = new JComboBox(RSS_Enabled_List.toArray());
		
		CS_Panel.add(Select_Add);
		
		JButton Add    = new JButton("Add Feed");
		JButton Remove = new JButton("Remove Feed");
        
    	// The function to be called when the button is pressed.
		Add.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent event){                	
			   AddRSS(Select_Add.getSelectedIndex());
			   
			   OnCloseEvent();
			   frame.dispose();			   			 
		   }
		});
		
		Remove.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent event){                	
				   RemoveRSS(Select_Remove.getSelectedIndex());
				   
				   OnCloseEvent();
				   frame.dispose();			   			 
			   }
			});
		
		 CS_Panel.add(Add);
		 
		 CS_Panel.add(Select_Remove);
		 
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
