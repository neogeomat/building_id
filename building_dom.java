
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.String;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.*;

// import java.sql.DriverManager;
// import java.sql.Connection;
// import java.sql.SQLException;

// public ArrayList<osmNode> osmNodes = new ArrayList<osmNode>();
//     //public ArrayList<osmPolygon> osmPolygons = new ArrayList<osmPolygon>();
//  public ArrayList<osmLine> osmLines = new ArrayList<osmLine>(),
//      osmPolygons = new ArrayList<osmLine>();

public class building_dom{
	
	public static void main(String args[]){ 
		try{
			String filepath="C:\\Users\\Poshan\\Desktop\\building_id\\before_change.osm";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			

			// Node way = doc.getElementsByTagName("way").item(0);
			// NamedNodeMap attributes = way.getAttributes();
			// Node nodeAtrr = attributes.getNamedItem("");

			// Element rootNode = doc.getRootElement();

			// Element way = rootNode.getElementsByTagName("way");
			
			NodeList wayList = doc.getElementsByTagName("way");
			
			for (int i=0 ; i<wayList.getLength(); i++){
				Node wNode = wayList.item(i);
				Element eElement1 = (Element) wNode;   //inside a way tree
				String osm_id = eElement1.getAttribute("id");
				NodeList childs = eElement1.getElementsByTagName("tag"); //list of nd and tags
				for (int k =0 ; k < childs.getLength(); k++){
					Node childs_individual = childs.item(k); //either nd or tag node
					Element e1=(Element) childs_individual;
					System.out.println(e1.getAttribute("k"));



					// Element e1=(Element) childs_individual;
					// if (e1.getAttribute("k")=="building"){
					// 	System.out.println("I love you");
					// // System.out.println((childs_individual.getNodeName()));
					// // System.out.println((String)(childs_individual.getLocalName()));
					// // // Element eElement1 = (Element) childs_individual;
					


					// // // Element eElement_test = (Element) childs_individual;
					// // // Node tag_test = eElement_test.getAttribute("tag");
					// // // Element tag_test_element = (Element)tag_test;
					// // // tag_test_element.getAttributes("")
					


					// // // java.lang.String nodeName = childs_individual.getNodeName();
					// // // System.out.println(nodeName);
					// }

					// if ((("#text").equals((String)(childs_individual.getNodeName()))))										
					// // // NodeList tag = doc.getElementsByTagName("tag");//instead try getting child nodeList
				
					// // // System.out.println (tag.getLength());
					
					// // // for (int j= 0; j<tag.getLength(); j++){
					// // // 	Node tNode = tag.item(j);
					// // // 	if (wNode.getNodeType() == Node.ELEMENT_NODE) 
					// {
							
					// 		Element eElement = (Element) childs_individual;
					// 		// Node childs_individual1 = childs_individual.getElementsByTagName("tag");
					// 		// Element eElement = (Element) childs_individual1;
							
					// 		String key = eElement.getAttribute("k");
							
					// 		String value = eElement.getAttribute("v");


					// 		if (key=="building"){


					// 			System.out.println("got a building");

					// 		}
							
							
					// }
				}
				// System.out.println(osm_id+"\n");
				// System.out.println(key+"\t");
				// System.out.println(value+"\t");

			}
			
			System.out.println("Done");
			
		}
		
		catch (Exception e){
				e.printStackTrace();
		}
	}
		
	
// public static void removeWhitespaceNodes(Element e) {
// 	NodeList children = e.getChildNodes();
// 	for (int i = children.getLength() - 1; i >= 0; i--) {
// 		Node child = children.item(i);
// 		if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
// 			e.removeChild(child);
// 		}
// 		else if (child instanceof Element) {
// 			removeWhitespaceNodes((Element) child);
// 		}
// 	}
// }
	// public static void data(String s){
		
	// 	try {
	// 		Class.forName("com.mysql.jdbc.Driver");
	// 		System.out.println("MySQL JDBC Driver Registered!");
	// 		Connection connection = null;			
	// 		connection = DriverManager
	// 		.getConnection("jdbc:mysql://localhost:3306/table1/table1","root", "");//my table name username and password

	// 		try{
	// 			if (connection != null) {
	// 				System.out.println("You made it, take control your database now!");
	// 				Statement st = connection.createStatement();
	// 				int val = st.executeUpdate("INSERT table1 VALUES("+i+","+"s"+")");
	// 					// int val = st.executeUpdate("INSERT employee VALUES("+i+","+"'Aman'"+")");
	// 					System.out.println("1 row affected");

	// 			} 
	// 			else {
	// 				System.out.println("Failed to make connection!");
	// 			}
	// 			i=i+1;
	// 		}
	// 		catch (SQLException e) {
	// 			System.out.println("Connection Failed! Check output console");
	// 			e.printStackTrace();
	// 			return;
	// 		}
	// 	}
	// 	catch (Exception e) {
	// 		System.out.println("Connection Failed! Check output console");
	// 		e.printStackTrace();
	// 		return;
	// 	}
	// }
		
}