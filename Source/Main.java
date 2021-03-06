package package_1;


import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.globes.*;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.view.orbit.*;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.FlatWorldPanel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends ApplicationTemplate
{
		// The tool bar that the user interacts with.
	static ToolBar GUI_Toolbar;
	
	static Data_Manager Manager;
	
				
    @SuppressWarnings("serial")
	public static class AppFrame extends ApplicationTemplate.AppFrame
    {
      
    		// Creates the user control panel. 
        public AppFrame(){           
                      	
            	// Add control panels.
            JPanel controls = new JPanel();
            
            	// The death will save the little ones with love.
            this.addWindowListener(new WindowAdapter(){
            	public void windowClosing(WindowEvent e){
            		FileIO.DumpFeeds(Manager);
            	}	
            });
            
            controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
            
            	// Add settings view control panel.
            controls.add(makeSettingsPanel());
            
            	// Add flat world projection control panel.
            controls.add(new FlatWorldPanel(this.getWwd()));
    
            	// Remove the layer display which is added by default.
            this.getLayerPanel().removeAll();
            
            	// Create the RSS table Panel.
            controls.add(MakeRSSTable());    		
            
            	// Add all of the elements to the JFrame.
            this.getLayerPanel().add(controls,  BorderLayout.NORTH);
        }
        
        	// Create the RSS feed table for the GUI.
        private JPanel MakeRSSTable(){
        	
        		// Create the controlling panel.
        	JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
            controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);
            

            	// The table that contains all of the feed information.
          	JTable		table;
          	
          		// Used to let the table scroll when the user has too many feeds to display all of them.
          	JScrollPane scrollPane;
                   

      			// The names of the columns.
      		String columnNames[] = { "Name", "Refresh Rate" };

      			// Create some data for example.
      		String dataValues[][] =
      		{
      			{ "12",   "234" },
      			{ "-123", "43" },
      			{ "93",   "89.2" },
         		{ "93",   "89.2" },
      			{ "279",  "9033"}
      		};
      		
		
      			// Create a new table instance.
      		table = new JTable( dataValues, columnNames );
      		
      			// Disable the user from being able to edit the table.
      		table.setEnabled(false);  	


      			// Add the table to a scrolling pane.
      		scrollPane = new JScrollPane( table );
               
      			// Add the scrolling pane to the panel containing the scrollpane.
            //controlPanel.add(scrollPane);              
              
              
              	// Return the panel to be added to the main control panel.
            return controlPanel;
        }

        	// Creates the setting panel for the user to change data.
        private JPanel makeSettingsPanel()
        {
        	
        		// The panel for the buttons.
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
            controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);


            	// Add/Remove Feed (button) - Calls the tool bar element to open a JFrame of Add/Remove Feed.
            JPanel CS_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            CS_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton CS_AddRemove = new JButton("Add/Remove Feeds");
            
            	// The function to be called when the button is pressed.
            CS_AddRemove.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                	GUI_Toolbar.Create_Add_Remove_Dialog();
                }
            });
            
            	// Add to the container.
            CS_Panel.add(CS_AddRemove);        
            controlPanel.add(CS_Panel);
            
            
            	// Configuration Settings (button) - Calls the tool bar element to open a JFrame of Configuration Settings.    
            JPanel Add_and_Remove_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            Add_and_Remove_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton Add_and_Remove = new JButton("Configuration Settings");
            
            	// The function to be called when the button is pressed.
            Add_and_Remove.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){                	
                	GUI_Toolbar.Create_Config_Settings_Dialog();                    
                }
            });
            
            	// Add to the container.
            Add_and_Remove_Panel.add(Add_and_Remove);        
            controlPanel.add(Add_and_Remove_Panel);
            
            
         		// Search Button (button) - Calls the tool bar element to open a JFrame of Search.   
            JPanel Search_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            Search_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton Search = new JButton("Search");
            
            	// The function to be called when the button is pressed.
            Search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                	GUI_Toolbar.Create_Search_Dialog();
                }
            });
            
            	// Add to the container.
            Search_Panel.add(Search);        
            controlPanel.add(Search_Panel);
            

            // Latest label
            // JPanel latestPanel = new JPanel(new GridLayout(0, 1, 0, 0));
            // latestPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            // this.latestLabel = new JLabel();
            // this.latestLabel.setPreferredSize(new Dimension(200, 140));
            // this.latestLabel.setVerticalAlignment(SwingConstants.TOP);
            // latestPanel.add(this.latestLabel);
            // controlPanel.add(latestPanel);

            	// Create a outline for the buttons and title it.
            controlPanel.setBorder( new CompoundBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9), new TitledBorder("Settings")));            
            controlPanel.setToolTipText("For thugs only.");
            
            return controlPanel;
        }

    } 

    
    public static void main(String[] args){
    	
    
    	
    		// Create a new instance of the tool bar.
    	GUI_Toolbar = new ToolBar();
    	Manager = new Data_Manager();
    	

    	
    	
    	
    	
        	// Adjust configuration values before instantiation.
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 0);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 0);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 50e6);
        Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
        Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());
        
       // WWPanel.getInstance().getWwd(), layer);
        

        	// Start the application.
        ApplicationTemplate.AppFrame af =  ApplicationTemplate.start("WeatherRSS - Straight Outta Hop Town", AppFrame.class);   
        
       WorldWindow ww = af.getWwjPanel().getWwd();
       RenderableLayer layer = new RenderableLayer();
       Plotter plotter = new Plotter();
       
       		// now we dont have to pass these anywhere
       plotter.setLayer(layer);
       plotter.setWw(ww);
       
       GUI_Toolbar.SetOb(Manager);
       GUI_Toolbar.setDataManager(Manager);
		
       // Load the config file is possible.  	
       FileIO.LoadFeeds(Manager, GUI_Toolbar);
	
       /* 
       R_Parser parser = new R_Parser();
       //this layer and map need to go everywhere bassically because thats the only way to interact
       // with the existing objects
       parser.Parse("al");
       plotter.clearMap();
   */
   	// Rerender the map if there are new pins.
       Manager.Rerender();
      
    }
}
