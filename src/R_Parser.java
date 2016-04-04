package package_1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class R_Parser {
	
	public void Parse(String areaCode, WorldWindow ww){		
		

			String html = request("http://alerts.weather.gov/cap/"+areaCode+".php?x=1");
			//System.out.println(html);
			String stateName = stateLookup(areaCode);
			Alert newAlert = new Alert();
		    Plotter plotter = new Plotter();
	//	    Position pos = new Position(null, null,0);
			
		//    System.out.println(html);
		    
			//File fXmlFile = new File("C:/_/straight_outta_hop_town/wa.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			try{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(html.getBytes("utf-8"))));
					
			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("entry");
					
		//	System.out.println("\n----------------------------");
			
		//	System.out.println(nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				//System.out.println("\n----------------------------");
				Node nNode = nList.item(temp);
						
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if(eElement.getElementsByTagName("id").item(0).getTextContent() != null){
						newAlert.setLinkUrl(eElement.getElementsByTagName("id").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("summary").item(0).getTextContent() != null){
						newAlert.setSummary(eElement.getElementsByTagName("summary").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:event").item(0) != null){
						newAlert.setEvent(eElement.getElementsByTagName("cap:event").item(0).getTextContent());
					} else {
						continue;
					}
					if(eElement.getElementsByTagName("cap:effective").item(0).getTextContent() != null){
						newAlert.setEffTime(eElement.getElementsByTagName("cap:effective").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:expires").item(0).getTextContent() != null){
						newAlert.setExpTime(eElement.getElementsByTagName("cap:expires").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:status").item(0).getTextContent() != null){
						newAlert.setStat(eElement.getElementsByTagName("cap:status").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:msgType").item(0).getTextContent() != null){
						newAlert.setMsgType(eElement.getElementsByTagName("cap:msgType").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:category").item(0).getTextContent() != null){
						newAlert.setCategory(eElement.getElementsByTagName("cap:category").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:urgency").item(0).getTextContent() != null){
						newAlert.setUrgency(eElement.getElementsByTagName("cap:urgency").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:severity").item(0).getTextContent() != null){
						newAlert.setSeverity(eElement.getElementsByTagName("cap:severity").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:certainty").item(0).getTextContent() != null){
						newAlert.setCertainty(eElement.getElementsByTagName("cap:certainty").item(0).getTextContent());
					}
					if(eElement.getElementsByTagName("cap:areaDesc").item(0).getTextContent() != null){
						newAlert.setAreaDesc(eElement.getElementsByTagName("cap:areaDesc").item(0).getTextContent());
					}
					String geoCode = (eElement.getElementsByTagName("value").item(1).getTextContent());
					

					String []array = geoCode.split(" ");
					for(int i=0; i<array.length ; i++){
						//System.out.println(array[i]);
						//need to get the name for the county/zone
						String result = request("http://alerts.weather.gov/cap/wwaatmget.php?x="+array[i]+"&y=1");
						//we want the title element
						Document doc_2 = dBuilder.parse(new InputSource(new ByteArrayInputStream(result.getBytes("utf-8"))));	
						doc_2.getDocumentElement().normalize();
						String areaTitle = doc_2.getElementsByTagName("title").item(0).getTextContent();
						//fun with strings
						String []titlePieces = areaTitle.split(array[i]);
						titlePieces = titlePieces[0].split("for");
						areaTitle = titlePieces[1].substring(1);
						areaTitle = areaTitle.substring(0, (areaTitle.length()-2));
					//	System.out.println(areaTitle);
						String encoded = URLEncoder.encode(areaTitle, "UTF-8");
					//	Strimg stateName =
						String googleRequestUrl = "https://maps.googleapis.com/maps/api/geocode/xml?address="+encoded+","+stateName+",USA";
						if(stateName.equals("ALL")){
							googleRequestUrl = "https://maps.googleapis.com/maps/api/geocode/xml?address="+encoded+",USA";
						}
						String googleResult = request(googleRequestUrl);
						//System.out.println(googleResult);
					//	System.out.println(googleResult);
						doc_2 = dBuilder.parse(new InputSource(new ByteArrayInputStream(googleResult.getBytes("utf-8"))));
						doc_2.getDocumentElement().normalize();
						Double lat = Double.parseDouble(doc_2.getElementsByTagName("lat").item(0).getTextContent());
						Double lng = Double.parseDouble(doc_2.getElementsByTagName("lng").item(0).getTextContent());
					//	Angle lat_a = new Angle();
						Position pos = new Position(Angle.fromDegrees(lat), Angle.fromDegrees(lng), 0);
						newAlert.pushPosition(pos);
					}
					
					plotter.plot(newAlert, ww);
				}
			}} catch(Exception e){
				e.printStackTrace();
			}
	}    
	
	String request(String reqUrl){
		String urlParam = "application\\x-www-form-urlencoded";
		HttpURLConnection connection;
	    try {
	    //build the connection and make the request
		//String urlString = reqUrl;
		//urlString = urlString.replace(arg0, );
		URL url = new URL(reqUrl);
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", 
				urlParam);
		 connection.setRequestProperty("Content-Length", 
			        Integer.toString(urlParam.getBytes().length));
		connection.setRequestProperty("Content-Language", "en-US");
		
	    connection.setUseCaches(false);
	    connection.setDoOutput(true);
	    
	    //send request
	    DataOutputStream wr = new DataOutputStream (
	            connection.getOutputStream());
	        wr.writeBytes("http://alerts.weather.gov/cap/capatomproduct.xsl");
	        wr.close();
	    
	    //get the response
	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
	    String line;
	    while((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
	    return response.toString();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		
	}
	    return null;
	}
	
	public String stateLookup(String ac){
		if(ac.equals("us")){
			return "ALL";
		} else if (ac.equals("al")){
			return "Alabama";
		} else if (ac.equals("ak")){
			return "Alaska";
		} else if (ac.equals("as")){
			return "America Samoa";
		} else if (ac.equals("az")){
			return "Arizona";
		} else if (ac.equals("ar")) {
			return "Arkansas";
		} else if (ac.equals("ca")) {
			return "California";
		} else if (ac.equals("co")) {
			return "Colorado";
		} else if (ac.equals("ct")) {
			return "Connecticut";
		} else if (ac.equals("de")) {
			return "Delaware";
		} else if (ac.equals("dc")) {
			return "Disctrict of Columbia";
		} else if (ac.equals("fl")) {
			return "Florida";
		} else if (ac.equals("ga")) {
			return "Georgia";
		} else if (ac.equals("gu")) {
			return "Guam";
		} else if (ac.equals("hi")) {
			return "Hawaii";
		} else if (ac.equals("id")) {
			return "Idaho";
		} else if (ac.equals("il")) {
			return "Illinois";
		} else if (ac.equals("in")) {
			return "Indiana";
		} else if (ac.equals("ia")) {
			return "Iowa";
		} else if (ac.equals("ks")){
			return "Kansas";
		} else if (ac.equals("ky")) {
			return "Kentucky";
		} else if (ac.equals("la")) {
			return "Louisiana";
		} else if (ac.equals("me")) {
			return "Maine";
		} else if (ac.equals("mp")) {
			return "Marianas";
		} else if (ac.equals("md")) {
			return "Maryland";
		} else if (ac.equals("ma")) {
			return "Massachusetts";
		} else if (ac.equals("mi")) {
			return "Michigan";
		} else if (ac.equals("um")) {
			return "Midway Island";
		} else if (ac.equals("mn")) {
			return "Minnesota";
		} else if (ac.equals("ms")) {
			return "Mississippi";
		} else if (ac.equals("mo")) {
			return "Missouri";
		} else if (ac.equals("mt")) {
			return "Montana";
		} else if (ac.equals("nv")) {
			return "Nevada";
		} else if (ac.equals("ne")) {
			return "Nebraska";
		} else if (ac.equals("nh")) {
			return "New Hampshire";
		} else if (ac.equals("nj")) {
			return "New Jersey";
		} else if (ac.equals("nm")) {
			return "New Mexico";
		} else if (ac.equals("ny")) {
			return "New York";
		} else if (ac.equals("nc")) {
			return "North Carolina";
		} else if (ac.equals("nd")) {
			return "North Dakota";
		} else if (ac.equals("oh")) {
			return "Ohio";
		} else if (ac.equals("ok")) {
			return "Oklahoma";
		} else if (ac.equals("or")) {
			return "Oregon";
		} else if (ac.equals("pa")) {
			return "Pennsylvania";
		} else if (ac.equals("pr")) {
			return "Puerto Rico";
		} else if (ac.equals("ri")) {
			return "Rhode Island";
		} else if (ac.equals("sc")) {
			return "South Carolina";
		} else if (ac.equals("sd")) {
			return "South Dakota";
		} else if (ac.equals("tn")) {
			return "Tennessee";
		} else if (ac.equals("tx")) {
			return "Texas";
		} else if (ac.equals("ut")) {
			return "Utah";
		} else if (ac.equals("vt")) {
			return "Vermont";
		} else if (ac.equals("vi")) {
			return "Virgin Islands";
		} else if (ac.equals("va")) {
			return "Virginia";
		} else if (ac.equals("wa")) {
			return "Washington";
		} else if (ac.equals("wv")) {
			return "West Virginia";
		} else if (ac.equals("wi")) {
			return "Wisconsin";
		} else if (ac.equals("wy")){
			return "Wyoming";
		}		
		return "";//stateName
	}
	
}



	