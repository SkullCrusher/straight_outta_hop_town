package straight_outta_hop_town_pa2;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ToolBar_Search_Button {

	public ToolBar_Search_Button(){
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
		
		JFrame frame = new JFrame("Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
