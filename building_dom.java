
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
// import java.sql.Connection;
// import java.sql.Statement;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.ResultSetMetaData;
// import java.sql.DatabaseMetaData;

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
			String filepath="C:\\Users\\Poshan\\Desktop\\building_id\\";
			String file=filepath+"before_change.osm";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			ArrayList<String> listkey = new ArrayList<String>();//array list that contains the keys
			ArrayList<String> listvalue = new ArrayList<String>();// array list that contains the values

			// Node way = doc.getElementsByTagName("way").item(0);
			// NamedNodeMap attributes = way.getAttributes();
			// Node nodeAtrr = attributes.getNamedItem("");

			// Element rootNode = doc.getRootElement();

			// Element way = rootNode.getElementsByTagName("way");
			
			NodeList wayList = doc.getElementsByTagName("way");
			
			// HashSet collection = new HashSet ();
			// for (int p = 0; p<wayList.getLength(); p++){
			// 	//yaha samma correct 6
			// 	System.out.println("yaha aai pugyo");
			// 	// Node wNode1 = wayList.item(p);
			// 	// Element eElement2 = (Element) wNode1;
			// 	Element node = doc.createElement("tagg");
			// 	node.setAttribute("attrib", "attrib_value"); //add an attribute may be district or ward or 
			// }
			


			for (int i=0 ; i<wayList.getLength(); i++){
				Node wNode = wayList.item(i);
				Element eElement1 = (Element) wNode;   //inside a way tree
				String osm_id = eElement1.getAttribute("id");
				NodeList childs = eElement1.getElementsByTagName("tag"); //list of nd and tags
				// System.out.println("\n");
				
			
				

				for (int k = 0 ; k < childs.getLength(); k++){
					
					Node childs_individual = childs.item(k); //either nd or tag node
					Element e1=(Element) childs_individual;
					// System.out.println((childs_individual.getNodeName()));
					listkey.add(e1.getAttribute("k"));
					listvalue.add(e1.getAttribute("v"));
					

			}
			}
			for (int l=0; l<listkey.size(); l++){
				System.out.print(listkey.get(l)+"\t");
				System.out.println(listvalue.get(l));

			}
			// data("poshan");
			
			// System.out.println("Done");
			
		}
		
		catch (Exception e){
				e.printStackTrace();
		}
		data ("poshan");
	}
	
	

	public static void data(String s){
		
		try {
			System.out.println("yaha samma aayo hai");
			Class.forName("org.sqlite.JDBC");  
         	System.out.println("yaha samma pani aayo hai");
         	Connection connection = null;	
         	connection = DriverManager.getConnection("jdbc:sqlite:table_ko_name.db");
			System.out.println("sqlite JDBC Driver Registered!");
			
			// connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			try{
				if (connection != null){
					System.out.println(s+"I love u");
					
				}
			}
		// 	try{
		// 		if (connection != null) {
		// 			System.out.println("You made it, take control your database now!");
		// 			Statement st = connection.createStatement();
		// 			int val = st.executeUpdate("INSERT table1 VALUES("+i+","+"s"+")");
		// 				// int val = st.executeUpdate("INSERT employee VALUES("+i+","+"'Aman'"+")");
		// 				System.out.println("1 row affected");

		// 		} 
		// 		else {
		// 			System.out.println("Failed to make connection!");
		// 		}
		// 		i=i+1;
				catch (Exception e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;
				}
			}
			catch (Exception e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			}

	}
		
}