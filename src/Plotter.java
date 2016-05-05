package package_1;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

public class Plotter {
	public class Filters{
		String State;
		String City;
		String BeforeDateTime;
		String AfterDateTime;
		boolean Severity_Severe; 
		boolean Severity_Moderate;
		boolean Severity_Minor;
		boolean Severity_Unknown;
		boolean Urgency_Expected;
		boolean Urgency_Future;
		boolean Urgency_Immediate;
		boolean Urgency_Unknown;
	}
	private static WorldWindow ww;
	private static RenderableLayer layer; 
	private Filters filterParams;
	
	
	public void plot(Alert rssUnit){
		//	PointPlacemark ppm = new PointPlacemark(rssUnit.getPosition());
		Position pos;
		if(!applyFilters(rssUnit)){
		//	System.out.println("no plot");
			return;//no plot
		}
		PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
		PointPlacemark ppm;
		for(int i=0; i<rssUnit.getPosCount();i++){	
            
			pos = rssUnit.getPosition(i);
			ppm = new PointPlacemark(pos);
		   
		    
		    ppm.setLabelText(rssUnit.getEvent());
		    ppm.setValue(AVKey.DISPLAY_NAME, rssUnit.getAreaDesc()+"\n"+rssUnit.getEffTime()+"\n"+"LinkText");
		    ppm.setLineEnabled(false);
		    ppm.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
		    attrs.setScale(0.6);
		    attrs.setLabelOffset(new Offset(0.9d, 0.6d, AVKey.FRACTION, AVKey.FRACTION));
		    ppm.setAttributes(attrs);
		    layer.addRenderable(ppm);
		   
		    ApplicationTemplate.insertAfterPlacenames(ww, layer);
		}
	}
	
	public void clearMap(){
	//	System.out.println("ahoy");
		layer.removeAllRenderables();
	}

	public static RenderableLayer getLayer() {
		return layer;
	}

	public static void setLayer(RenderableLayer layer) {
		Plotter.layer = layer;
	}

	public static WorldWindow getWw() {
		return ww;
	}

	public static void setWw(WorldWindow ww) {
		Plotter.ww = ww;
	}
	Plotter(){
		filterParams = new Filters();
		setFilterParams("","", false,false,false,false,false,false,false,false);
	}
	
	public void resetFilters(){
		setFilterParams("","", false,false,false,false,false,false,false,false);
	}
	
	
	public void setFilterParams(String BeforeDateTime ,
			                       String AfterDateTime, boolean Severity_Severe, boolean Severity_Moderate, 
			                        boolean Severity_Minor, boolean Severity_Unknown, boolean Urgency_Expected, 
			                        boolean Urgency_Future, boolean Urgency_Immediate, boolean Urgency_Unknown){
		filterParams.BeforeDateTime = BeforeDateTime;
		filterParams.AfterDateTime = AfterDateTime;
		filterParams.Severity_Severe = Severity_Severe;
		filterParams.Severity_Moderate = Severity_Moderate;
		filterParams.Severity_Minor = Severity_Minor;
		filterParams.Severity_Unknown = Severity_Unknown;
		filterParams.Urgency_Expected = Urgency_Expected;
		filterParams.Urgency_Future = Urgency_Future;
		filterParams.Urgency_Immediate = Urgency_Immediate;
		filterParams.Urgency_Unknown = Urgency_Unknown;
	}
	
	public Boolean applyFilters(Alert rssUnit){
	//	System.out.println(rssUnit.getSeverity()+"-");
		switch(rssUnit.getSeverity()){
			case "Unknown":
				if(filterParams.Severity_Unknown){
					return false;
				}
				break;
			case "Minor":
				if(filterParams.Severity_Minor){
					return false;
				}
				break;
			case "Moderate":
				if(filterParams.Severity_Moderate){
					return false;
				}
				break;
			case "Severe":
				if(filterParams.Severity_Severe){
					return false;
				}
				break;
			default:
				return false;
		}
		switch(rssUnit.getUrgency()){
			case "Expected":
				if(filterParams.Urgency_Expected){
					return false;
				}
				break;
			case "Future":
				if(filterParams.Urgency_Future){
					return false;
				}
				break;
			case "Immediate":
				if(filterParams.Urgency_Immediate){
					return false;
				}
				break;
			case "Unknown":
				if(filterParams.Urgency_Unknown){
					return false;
				}
		}
		if(!rssUnit.getEffTime().isEmpty()){
//			System.out.println(rssUnit.getEffTime()+" "+rssUnit.getExpTime());
	//		System.out.println(filterParams.BeforeDateTime+" "+filterParams.AfterDateTime);
			if(!filterParams.BeforeDateTime.isEmpty()||!filterParams.AfterDateTime.isEmpty()){
				if((rssUnit.getExpTime().compareTo(filterParams.BeforeDateTime))<=0){
					System.out.println("Too early");
					return false;
				}
				if((rssUnit.getEffTime().compareTo(filterParams.AfterDateTime))>=0){
					System.out.println("Too late");
					return false;
				}
			}
		}
	//	System.out.println("should plot");
		return true;
	}
}
