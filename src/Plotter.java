package package_1;

import java.awt.Color;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

public class Plotter {
	
	public void plot(Alert rssUnit,WorldWindow ww){
		//	PointPlacemark ppm = new PointPlacemark(rssUnit.getPosition());
		Position pos;

		PointPlacemark ppm;
		for(int i=0; i<rssUnit.getPosCount();i++){	
			pos = rssUnit.getPosition(i);
			ppm = new PointPlacemark(pos);
		    RenderableLayer layer = new RenderableLayer();
		    
		    ppm.setLabelText("Placemark A");
		    ppm.setValue(AVKey.DISPLAY_NAME, "Clamp to ground, Label, Semi-transparent, Audio icon");
		    ppm.setLineEnabled(false);
		    ppm.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
		   // pp.setEnableLabelPicking(true); // enable label picking for this placemark
		    PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
		    attrs.setImageAddress("gov/nasa/worldwindx/examples/images/audioicon-64.png");
		    attrs.setImageColor(new Color(1f, 1f, 1f, 0.6f));
		    attrs.setScale(0.6);
//		    attrs.setImageOffset(new Offset(19d, 8d, AVKey.PIXELS, AVKey.PIXELS));
		    attrs.setLabelOffset(new Offset(0.9d, 0.6d, AVKey.FRACTION, AVKey.FRACTION));
		    ppm.setAttributes(attrs);
		    layer.addRenderable(ppm);
		   
		    ApplicationTemplate.insertAfterPlacenames(ww, layer);
			
		}
	}
}
