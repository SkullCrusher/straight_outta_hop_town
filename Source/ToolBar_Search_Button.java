package package_1;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

public class ToolBar_Search_Button extends Observable {
	
	
	public boolean Reset = false;
	
	public JTextField textField1; // State
	public JTextField textField2; // City
	
		// Before DateTime
	JSpinner BeforeDateTime;
		// After DateTime
	JSpinner AfterDateTime;
	 	
	public JCheckBox Severity_Severe;
	public JCheckBox Severity_Moderate;
	public JCheckBox Severity_Minor;
	public JCheckBox Severity_Unknown;
	
	public JCheckBox Urgency_Expected;
	public JCheckBox Urgency_Future;
	public JCheckBox Urgency_Immediate;
	public JCheckBox Urgency_Unknown;

	


	
	public ToolBar_Search_Button(){
		windowOpen = false;
	}
	
	// If the window is currently open.
	private boolean windowOpen;
	
		// Any cleanup that needs to happen plus allow a new one be opened.
	private void OnCloseEvent(){
		windowOpen = false;
	}
	
	private void Search(){
		Reset = false;
		
		setChanged();
		notifyObservers(this); 
	}
	
	private void Reset(){
		Reset = true;
		
		setChanged();
		notifyObservers(this); 
	}
	
	public void Create() {
		
		if(windowOpen){ return; }
		
		JFrame frame = new JFrame("Search/Filter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//------------------------------------------------
		
		
		JPanel controls = new JPanel();
		
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		controls.setAlignmentY(Component.TOP_ALIGNMENT);


          	// Add/Remove Feed (button) - Calls the tool bar element to open a JFrame of Add/Remove Feed.
        JPanel CS_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
        CS_Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
          
		
							
		JComboBox Select_Add = new JComboBox();
		JComboBox Select_Remove = new JComboBox();
		
			
		
		//CS_Panel.add(Select_Add);
		
		JButton Add    = new JButton("Submit");
		JButton Remove = new JButton("Reset");
        
    	// The function to be called when the button is pressed.
		Add.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent event){                	
			  // AddRSS(Select_Add.getSelectedIndex());
			   Search();
			   
			   OnCloseEvent();
			   frame.dispose();			   			 
		   }
		});
		
		Remove.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent event){			   
				   Reset();
				   
				   OnCloseEvent();
				   frame.dispose();			   			 
			   }
			});
		
		
		textField1 = new JTextField("", 1); // State
		textField2 = new JTextField("", 2); // City
		 
		JLabel label2 = new JLabel("State");
		JLabel label3 = new JLabel("City");
		
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12); 
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model2 = new SpinnerDateModel();
        model2.setValue(calendar.getTime());

        BeforeDateTime = new JSpinner(model2);
		

		Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 12);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);

        SpinnerDateModel model3 = new SpinnerDateModel();
        model3.setValue(calendar2.getTime());

        AfterDateTime = new JSpinner(model3);
		
      
		
		JLabel label4 = new JLabel("Start Date/Time");
		JLabel label5 = new JLabel("End Date/Time");
		
		CS_Panel.add(label4);
		CS_Panel.add(BeforeDateTime);
		CS_Panel.add(label5);
		CS_Panel.add(AfterDateTime);
		
		
		
	
		CS_Panel.add(label2);
		CS_Panel.add(textField1);
		CS_Panel.add(label3);
		CS_Panel.add(textField2);
		

		
	

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());

		JSpinner spinner = new JSpinner(model);
		

		
			// Severity.	
		JLabel label9 = new JLabel("Severity");
		CS_Panel.add(label9);
		
		Severity_Severe = new JCheckBox("Severe");
		CS_Panel.add(Severity_Severe);
		Severity_Moderate = new JCheckBox("Moderate");
		CS_Panel.add(Severity_Moderate);
		Severity_Minor = new JCheckBox("Minor");
		CS_Panel.add(Severity_Minor);
		Severity_Unknown = new JCheckBox("Unknown");
		CS_Panel.add(Severity_Unknown);
		
			// Urgency
		JLabel label10 = new JLabel("Urgency");
		CS_Panel.add(label10);
		
		Urgency_Expected = new JCheckBox("Expected");
		CS_Panel.add(Urgency_Expected);
		Urgency_Future = new JCheckBox("Future");
		CS_Panel.add(Urgency_Future);
		Urgency_Immediate = new JCheckBox("Immediate");
		CS_Panel.add(Urgency_Immediate);
		Urgency_Unknown = new JCheckBox("Unknown");
		CS_Panel.add(Urgency_Unknown);
		
		controls.add(CS_Panel);
		

		JLabel lab = new JLabel("State", JLabel.RIGHT);
	    lab.setLabelFor(textField1);
		
		CS_Panel.add(Add);
		CS_Panel.add(Remove);	
		    
		frame.add(controls);
		

		//------------------------------------------------
		
		
				
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	OnCloseEvent();
		    }
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setSize(300,600);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
				
		
		frame.setVisible(true);
				
		windowOpen = true;
	}
}
