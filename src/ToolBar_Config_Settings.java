package package_1;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class ToolBar_Config_Settings extends Observable {
	
	public JTable table = null;
	
    class MyTableModel extends AbstractTableModel {

    	
    	public ArrayList<String> columnNames = new ArrayList();
    	
    	public ArrayList<Object[]> data = new ArrayList<Object[]>();

		
		public MyTableModel(){
			
				// Set the column names.			
			columnNames.add(new String("Feed name"));
			columnNames.add(new String("Refresh time"));
			columnNames.add(new String("Enabled"));
			
				// Load in the currently added RSS feeds.			
			Data_Manager temp = new Data_Manager();
			
				// Add each RSS feed.
			for(int i = 0; i < temp.RSS_FEEDS.size(); i++){
				
					// This is brutal.
				Object[] t1 = {
						temp.StateCodeToState(temp.RSS_FEEDS.get(i).RSS_FEED),   
						temp.RSS_FEEDS.get(i).Refresh_Delay,  
						temp.RSS_FEEDS.get(i).Enabled};
				
				data.add(t1);
			}			
		}
		
        public int getColumnCount() {
        	return columnNames.size();
        }

        public int getRowCount() {        	
        	return data.size();
        }

        public String getColumnName(int col) {
        	 return columnNames.get(col);
        }

        public Object getValueAt(int row, int col) {
        	
        	Object temp[] = data.get(row);
        	
        	return temp[col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {            
        	((Object[])data.get(row))[col] = value;
            
            fireTableCellUpdated(row, col);
        }    
    }

	public ToolBar_Config_Settings(){ windowOpen = false; }
	
		// If the window is currently open.
	private boolean windowOpen;
	
		// Any cleanup that needs to happen plus allow a new one be opened.
	private void OnCloseEvent(){ windowOpen = false; }

	
	public void Create() {
		
		if(windowOpen){ return;} 
				
		JFrame frame = new JFrame("Configuration Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//JTable table = new JTable(new MyTableModel());
		table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        
               
        
        table.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
               System.out.println(e.getColumn());
                             
           			// Add directly to the data manager.
       			setChanged();
       			notifyObservers(table); 
            }
          });
        
        
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		frame.add(scrollPane);
	
								
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	setChanged();
       			notifyObservers(this); 
		    	
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
