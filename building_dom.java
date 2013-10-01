
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


public class building_dom{
	
	public static void main(String args[]){ 
		try {
			System.out.println("yaha samma aayo hai");
			Class.forName("org.sqlite.JDBC");  
         	// System.out.println("yaha samma pani aayo hai");
         	Connection connection = null;	
         	connection = DriverManager.getConnection("jdbc:sqlite:table_ko_name2.db");
			// System.out.println("sqlite JDBC Driver Registered!");
			
			// connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			try{
				if (connection != null){
					// System.out.println(s+"is the building");
					Statement st = connection.createStatement();
					String sql = "CREATE TABLE database " +
	                "(osmID INT PRIMARY KEY     NOT NULL," +
	                " wayID          TEXT    NOT NULL, " +  
	                " district       CHAR(50), " + 
	                " vdc			CHAR(50), "+
	                " ward         INT)"; 
					st.executeUpdate(sql);
					st.close();
					
					
				}
			}

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
		

		try{
			String filepath="C:\\Users\\Poshan\\Desktop\\building_id\\";
			String file=filepath+"before_change.osm";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			ArrayList<String> listkey = new ArrayList<String>();//array list that contains the keys
			ArrayList<String> listvalue = new ArrayList<String>();// array list that contains the values	
			NodeList wayList = doc.getElementsByTagName("way");  //1. Nodelists of way
			


			for (int i=0 ; i<wayList.getLength(); i++){
				Node wNode = wayList.item(i);
				Element eElement1 = (Element) wNode;   //inside a way tree
				String osm_id = eElement1.getAttribute("id");
				NodeList childs = eElement1.getElementsByTagName("tag"); //list of nd and tags
				// System.out.println("\n");
				// data(osm_id);
				listkey.clear();
				listvalue.clear();
				for (int k = 0 ; k < childs.getLength(); k++){
					
					Node childs_individual = childs.item(k); // single tag node
					Element e1=(Element) childs_individual; //
					// System.out.println((childs_individual.getNodeName()));

					listkey.add(e1.getAttribute("k"));
					listvalue.add(e1.getAttribute("v"));

				}

				for (int l=0; l<listkey.size(); l++){
				// System.out.println("yaha samma aayo ni tah");
				// System.out.print(listkey.get(l)+"\n");

					
					if (listkey.get(l).equals("building")){
						// System.out.println("the answer that could never be found"+l);
						data(osm_id);
					}
			
			
				}
			
			}
			
			// System.out.print(listkey.size());
		}
		catch (Exception e){
				e.printStackTrace();
		}

		
		

		
	}
	
	

	public static void data(String s){
		
		try {
			//write code to insert osmid to dbase
	      	Connection connection = null;	
         	connection = DriverManager.getConnection("jdbc:sqlite:table_ko_name2.db");
			Statement st = connection.createStatement();
	      	String sql = "INSERT INTO database (osmID,wayID,district,vdc,ward) " +
           	"VALUES (1, s, 'Kath', 'ktm', 2 );"; 
      		st.executeUpdate(sql);
      		st.close();
	      	connection.commit();
	      	
		}
		catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

	}
		
}