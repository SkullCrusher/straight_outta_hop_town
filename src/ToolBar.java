

public class ToolBar {

		// The classes of the tool bar.
	private ToolBar_Search_Button 	Search;
	private ToolBar_Config_Settings	Config_Settings;
	private ToolBar_AddRemove_RSS 	AddRemove;
	
		// Default constructor
	public ToolBar(){
		
		Search			= new ToolBar_Search_Button();
		Config_Settings = new ToolBar_Config_Settings();
		AddRemove 		= new ToolBar_AddRemove_RSS();		
	}

		// The function to create the search window
	public void Create_Search_Dialog(){
		Search.Create();
	}
	
		// The function to create the RSS_List window
	public void Create_Config_Settings_Dialog(){
		Config_Settings.Create();
	}
	
		// The function to create the AddRemove window
	public void Create_Add_Remove_Dialog(){
		AddRemove.Create();
	}
	
}
