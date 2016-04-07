package package_1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
	
	public void Parse(String areaCode){		

			String html = request("http://alerts.weather.gov/cap/"+areaCode+".php?x=1");
			//System.out.println(html);
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
						String googleRequestUrl = "https://maps.googleapis.com/maps/api/geocode/xml?address="+encoded+","+areaCode+",USA";
						String googleResult = request(googleRequestUrl);
						//System.out.println(googleResult);
					//	System.out.println(googleResult);
						doc_2 = dBuilder.parse(new InputSource(new ByteArrayInputStream(googleResult.getBytes("utf-8"))));
						doc_2.getDocumentElement().normalize();
						if(doc_2.getElementsByTagName("lat").item(0)==null){
							continue;
						}
						Double lat = Double.parseDouble(doc_2.getElementsByTagName("lat").item(0).getTextContent());
						Double lng = Double.parseDouble(doc_2.getElementsByTagName("lng").item(0).getTextContent());
					//	Angle lat_a = new Angle();
						Position pos = new Position(Angle.fromDegrees(lat), Angle.fromDegrees(lng), 0);
						newAlert.pushPosition(pos);
					}
					plotter.plot(newAlert);
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
}



	