package package_1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;


public class R_Parser {
	
	public void Parse(){


		    try {

			File fXmlFile = new File("C:/_/straight_outta_hop_town/wa.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("entry");
					
			System.out.println("\n----------------------------");
			
			System.out.println(nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				System.out.println("\n----------------------------");
				Node nNode = nList.item(temp);
						
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Link: " + eElement.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("summary: " + eElement.getElementsByTagName("summary").item(0).getTextContent());
					System.out.println("Event: " + eElement.getElementsByTagName("cap:event").item(0).getTextContent());
					System.out.println("Effective: " + eElement.getElementsByTagName("cap:effective").item(0).getTextContent());
					System.out.println("Expires: " + eElement.getElementsByTagName("cap:expires").item(0).getTextContent());
					System.out.println("Status: " + eElement.getElementsByTagName("cap:status").item(0).getTextContent());
					
					System.out.println("msgType: " + eElement.getElementsByTagName("cap:msgType").item(0).getTextContent());
					System.out.println("Category: " + eElement.getElementsByTagName("cap:category").item(0).getTextContent());
					System.out.println("Urgency: " + eElement.getElementsByTagName("cap:urgency").item(0).getTextContent());
					System.out.println("Severity: " + eElement.getElementsByTagName("cap:severity").item(0).getTextContent());
					System.out.println("Certainty: " + eElement.getElementsByTagName("cap:certainty").item(0).getTextContent());
					System.out.println("areaDesc: " + eElement.getElementsByTagName("cap:areaDesc").item(0).getTextContent());
					System.out.println("Status: " + eElement.getElementsByTagName("cap:status").item(0).getTextContent());
					
					
					
					
					//System.out.println("Staff id : " + eElement.getAttribute("id"));
					/*System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
*/
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  
	}
	
	}