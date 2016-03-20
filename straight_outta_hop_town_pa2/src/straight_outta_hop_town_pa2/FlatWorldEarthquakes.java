
package straight_outta_hop_town_pa2;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.globes.*;
import gov.nasa.worldwind.view.orbit.*;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.FlatWorldPanel;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;


public class FlatWorldEarthquakes extends ApplicationTemplate
{
		
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
      
        public AppFrame()
        {
           
            	// Add control panels
            JPanel controls = new JPanel();
            controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
            
            	// Add earthquakes view control panel
            controls.add(makeEarthquakesPanel());
            
            	// Add flat world projection control panel
            controls.add(new FlatWorldPanel(this.getWwd()));
    
            this.getLayerPanel().removeAll();
            
  /*
   Create the list of rss feeds
   */
       
            controls.add(MakeRSSTable());
    		
            //---- end of list -----
            
            
            
            
            this.getLayerPanel().add(controls,  BorderLayout.NORTH);
            

        }
        
        private JPanel MakeRSSTable(){
        	
        	  JPanel controlPanel = new JPanel();
              controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
              controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);


       
              
                   
              
              
          	JTable		table;
          	JScrollPane scrollPane;
                   

      		// Create columns names
      		String columnNames[] = { "Name", "Refresh Rate" };

      		// Create some data
      		String dataValues[][] =
      		{
      			{ "12",   "234" },
      			{ "-123", "43" },
      			{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
         		{ "93",   "89.2" },
      			{ "279",  "9033"}
      		};
      		
      		
       
            
         //   Panel.add(Reset);        
        //    controlPanel.add(Panel);       
      		
      		

      		// Create a new table instance
      		table = new JTable( dataValues, columnNames );
      		
      		table.setEnabled(false);  		


      			// Add the table to a scrolling pane
      		scrollPane = new JScrollPane( table );
               
            controlPanel.add(scrollPane);
              
              
              
              
              return controlPanel;
        }

        private JPanel makeEarthquakesPanel()
        {
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
            controlPanel.setAlignmentY(Component.TOP_ALIGNMENT);


            	// Configuration settings
            JPanel CS_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            CS_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton CS_Reset = new JButton("Add/Remove Feeds");
            CS_Reset.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                	/* Double lat = Configuration.getDoubleValue(AVKey.INITIAL_LATITUDE); */
                }
            });
            
            CS_Panel.add(CS_Reset);        
            controlPanel.add(CS_Panel);
            
            
            	// Add/Remove Feed (button)            
            JPanel Add_and_Remove_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            Add_and_Remove_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton Add_and_Remove = new JButton("Configuration Settings");
            Add_and_Remove.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){                	
                 //   Double lat = Configuration.getDoubleValue(AVKey.INITIAL_LATITUDE);
                    
                }
            });
            
            Add_and_Remove_Panel.add(Add_and_Remove);        
            controlPanel.add(Add_and_Remove_Panel);
            
            
         		// Search Button
            JPanel Search_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
            Search_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            
            JButton Search = new JButton("Search");
            Search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                	/* Double lat = Configuration.getDoubleValue(AVKey.INITIAL_LATITUDE); */
                }
            });
            
            Search_Panel.add(Search);        
            controlPanel.add(Search_Panel);
            
            
            
            
            
            
          

            // Latest label
            JPanel latestPanel = new JPanel(new GridLayout(0, 1, 0, 0));
            latestPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
     //       this.latestLabel = new JLabel();
     //       this.latestLabel.setPreferredSize(new Dimension(200, 140));
     //       this.latestLabel.setVerticalAlignment(SwingConstants.TOP);
      //      latestPanel.add(this.latestLabel);
          //  controlPanel.add(latestPanel);

            controlPanel.setBorder( new CompoundBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9), new TitledBorder("Settings")));            
            controlPanel.setToolTipText("For thugs only.");
            
            return controlPanel;
        }

    } 

    // --- Main -------------------------------------------------------------------------
    public static void main(String[] args)
    {
        // Adjust configuration values before instantiation
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 0);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 0);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 50e6);
        Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
        Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());
     
        ApplicationTemplate.start("WeatherRSS - Straight Outta Hop Town", AppFrame.class);
        
    }
}
