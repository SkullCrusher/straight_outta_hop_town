package straight_outta_hop_town_pa2;

import java.awt.Color;
import java.util.LinkedList;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polyline;
import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		
		R_Parser Parser = new R_Parser();
		
		Parser.Parse();

		
/*
        JPanel MainPanel = new JPanel();// new LayerPanel(this.wwjPanel.getWwd(), new Dimension(50, 50));

        GridLayout experimentLayout = new GridLayout(0,2);

        // Combobox
        JLabel labelCombo = new JLabel("Select Country");

        // Options in the combobox
       

        // Textfield
    JLabel labelTextField = new JLabel("Enter city");
     // Add controls

    //textField = new JTextField();
    JPanel fieldPanel = new JPanel(experimentLayout);


    fieldPanel.add(labelCombo);
   // fieldPanel.add(comboBox);
    fieldPanel.add(labelTextField);
  //  fieldPanel.add(textField);
    fieldPanel.add(new JButton("Go To city"));
    MainPanel.add(fieldPanel, BorderLayout.NORTH);
*/
//    this.getContentPane().add(MainPanel, BorderLayout.EAST);
		
	
        /*
        JFrame f = new JFrame("JFrame with a JPanel");
        
      	JLabel L = new JLabel("Hello World !");   // Make a JLabel;
      	JPanel P = new JPanel();                  // Make a JPanel;       
       
      	 P.add(L);                   // Add lable L to JPanel P
       
      	 f.getContentPane().add(P);  // Add panel P to JFrame f
       
      	 f.setSize(800,600);
      	 f.setVisible(true);*/

		//create a WorldWind main object
		WorldWindowGLCanvas worldWindCanvas = new WorldWindowGLCanvas();
		worldWindCanvas.setModel(new BasicModel());
		
	 	JPanel P = new JPanel();                  // Make a JPanel; 
	 	P.add(worldWindCanvas);
	 	P.setSize(800, 600);
	 	P.setVisible(true);

		//build Java swing interface
		JFrame frame = new JFrame("World Wind");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(worldWindCanvas);
		//frame.add(P);
		//frame.getContentPane().add(P);
		frame.setSize(1200,600);
		frame.setVisible(true);
		/*
		//create some "Position" to build a polyline
		LinkedList<Position> list = new LinkedList<Position>();
		for(int i = 0 ; i < 90 ; i++) {
			//in this case, points are in geographic coordinates.
			//If you are using cartesian coordinates, you have to convert them to geographic coordinates.
			//Maybe, there are some functions doing that in WWJ API...
			list.add(Position.fromDegrees(i,0.0,i*20000));
		}
		
		//create "Polyline" with list of "Position" and set color / thickness
		Polyline polyline = new Polyline(list);
		polyline.setColor(Color.RED);
		polyline.setLineWidth(3.0);
		
		
		
		//create a layer and add Polyline
		RenderableLayer layer = new RenderableLayer();
		//layer.addRenderable(polyline);
		*/
		
		//add layer to WorldWind
		//worldWindCanvas.getModel().getLayers().add(layer);
		
	}
	
}