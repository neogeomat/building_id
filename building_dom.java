
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
			// System.out.println("yaha samma aayo hai");
			Class.forName("org.sqlite.JDBC");  
         	// System.out.println("yaha samma pani aayo hai");
         	Connection connection = null;	
         	connection = DriverManager.getConnection("jdbc:sqlite:table_ko_name4.db");
			// System.out.println("sqlite JDBC Driver Registered!");
			
			// connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			// try{
			// 	if (connection != null){
			// 		// System.out.println(s+"is the building");
			// 		Statement st = connection.createStatement();
			// 		String sql = "CREATE TABLE database " +
	  //               "(osmID INT PRIMARY KEY     NOT NULL," +
	  //               " wayID          TEXT    NOT NULL, " +  
	  //               " district       CHAR(50), " + 
	  //               " vdc			CHAR(50), "+
	  //               " ward         INT)"; 
			// 		st.executeUpdate(sql);
			// 		st.close();
					
					
			// 	}
			// }

			// catch (Exception e) {
			// 	System.out.println("Connection Failed! Check output console");
			// 	e.printStackTrace();
			// 	return;
			// }
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
							data(osm_id, connection);
						}
				
				
					}
				
				}
				// System.out.print(listkey.size());
			}
			catch (Exception e){
				e.printStackTrace();
			}


		}
		catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		
	}
	
	

	public static void data(String s, Connection a){
		
		try {
			//instead check if (database) and table exists or not at first and then make a connection here and create a table and database connection here for the first tyme and for 
			//nothing for the ones except for first time tables will already be there and then write in database so every time it checks whether database and table exists or not 
			//and creates one if not  and if yes only writes the passed valuess to the database.

	      	Connection connection = a;	
         	// connection = DriverManager.getConnection("jdbc:sqlite:table_ko_name2.db");
			Statement st = connection.createStatement();
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "database", null); // check if "database" table is there
			if (!(tables.next())) {
				try{
					if (connection != null){
						// System.out.println(s+"is the building");
						// Statement st = connection.createStatement();
						// String sql1 = "CREATE TABLE database " +
		    //             "(oID INT PRIMARY KEY     NOT NULL," +
		    //             " wayID          CHAR(50)    NOT NULL, " +  
		    //             " district       CHAR(50), " + 
		    //             " vdc			CHAR(50), "+
		    //             " ward         INT)"; 
						String sql1 = "CREATE TABLE database " +
		                "(oID INT      NOT NULL," +
		                " wayID          CHAR(50)    NOT NULL, " +  
		                " district       CHAR(50), " + 
		                " vdc			CHAR(50), "+
		                " ward         INT)"; 
						st.executeUpdate(sql1);
						st.close();
						
						
					}
				}
				catch (Exception e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;
				}
			}
	      	

	      	// check the value of to be given osmID(new one) in the table for the highest value and then give the value+1
		    String query =
		        "SELECT * " +
		        "FROM database";
			ResultSet rs = st.executeQuery(query);
			ArrayList<Integer> unsortList = new ArrayList<Integer>(); 
			ArrayList<String> wayidss = new ArrayList<String>();
			int oid = 0;
			while (rs.next()) {
				int id = rs.getInt("oID");
				String wid = rs.getString("wayID");
				wayidss.add(wid);
				unsortList.add(id);							//keeep the values in the oid in the table already present to the arraylist to determine hghest value
			}
			if(unsortList.isEmpty()){
				oid = 1;

			}
			else{
				int c = unsortList.get(0);
				for (int i = 1; i < unsortList.size(); i++){      //sorting the values of oids already present and determinin the highest value
					int b = unsortList.get(i);
					if (b < c){
						c = b;
					}
				}
				oid = c + 1;
			}									//highest value + 1
	      	

	      	//check if any of the items in the arraylist is equal to string passed from the function 
	      	///////////////////////////////////////////////////////////////////////////Working
	      	//to check whether the building's wayid is already registered or nots..............
	      	for (int q = 0; q < wayidss.size(); q++){
	      		if wayidss.get(q) == s{
	      			//insert // suggestion is call the insertion function pass string s connection or declare
	      		}
	      	}
			

			String wid = s;

	      	String sql = "INSERT INTO database (oID,wayID,district,vdc,ward) " +
           	"VALUES (" + oid + "," + wid + ", 'Kath', 'ktm', 2 );"; 
      		st.executeUpdate(sql);
      		// connection.commit();
      		st.close();
	      	
	      	
		}
		

		catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

	}
		
}