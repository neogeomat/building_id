
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

import java.util.HashMap;
import java.util.Map;


public class building_dom{
	
	public static void main(String args[]){ 
		try {
			Class.forName("org.sqlite.JDBC");  
         	Connection connection = null;	
         	connection = DriverManager.getConnection("jdbc:sqlite:realtest2_db.db");
         	DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "new_psuedonumber", null); // check if "new_psuedonumber" table is there
			if (!(tables.next())) {
				try{
					if (connection != null){
						Statement st = connection.createStatement();
						String createtable = "CREATE TABLE IF NOT EXISTS psuedonumber " +
						                " (osmid    CHAR(50) NOT NULL," +  	          
						                " district  CHAR(50)," + 
						                " vdc		CHAR(50),"+
						                " ward      INT," +
										" new_id     INT      NOT NULL,"+
										" id_from_field CHAR(15),"+
										" upload_date DATETIME,"+
										" changeset INT)"; 
						// System.out.println(createtable);
						st.executeUpdate(createtable);
						System.out.println("TABLE psuedonumber CREATED");
						st.close();
					}
					else{
						System.out.println("TABLE psuedonumber EXISTS");	
					}
				}
				catch (Exception e) {
					System.out.println("TABLE CREATION FAILED");
					e.printStackTrace();
					return;
				}
			}
	      	
			try{
				// need an interface to choose file
				String filepath="";
				String file=filepath+"before_change.osm";

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(file);
				ArrayList<String> listkey = new ArrayList<String>();//array list that contains the keys
				ArrayList<String> listvalue = new ArrayList<String>();// array list that contains the values	
				
				//execution start
				NodeList wayList = doc.getElementsByTagName("way");  // lists of ways

				for (int i=0 ; i<wayList.getLength(); i++){
					Node wNode = wayList.item(i);
					Element eElement1 = (Element) wNode;   //inside a way tree (<way .......>)
					String osm_id = eElement1.getAttribute("id");
					NodeList tags = eElement1.getElementsByTagName("tag"); //list of nd and tags
					listkey.clear();
					listvalue.clear();
					HashMap kvpair = new HashMap();
					for (int k = 0 ; k < tags.getLength(); k++){
						Node tag_individual = tags.item(k); // single tag node
						Element e1=(Element) tag_individual; //
						// System.out.println((tag_individual.getNodeName()));
						listkey.add(e1.getAttribute("k"));
						// System.out.println(listkey);
						listvalue.add(e1.getAttribute("v"));
						// System.out.println(listvalue);
						kvpair.put(e1.getAttribute("k"),e1.getAttribute("v")); //dict of keys and values
					}

					int c=0;
					for (int l=0; l<listkey.size(); l++){
						if (listkey.get(l).equals("building")){
							c = c+1;
						}
						if (listkey.get(l).equals("kll:district")){
							c = c+1;
						}
						if (listkey.get(l).equals("kll:vdc")){
							c = c+1;
						}
						if (listkey.get(l).equals("kll:ward")){
							c = c+1;
						}
					}
					if(c==4){
						data(osm_id, connection, kvpair);
					}
				}
				System.out.print("No Errors Operation Successful");
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
	
	/* This part writes new id to database. 
	 * First it checks whether the id already exists or not, 
	 * if not exists then it writes to database
	*/
	public static void data(String osmid, Connection a, HashMap kvpair){
		try {
	      	Connection connection = a;	
			Statement st = connection.createStatement();
	      	// check the value of to be given osmID(new one) in the table for the highest value and then give the value+1
		 //    String query =
		 //        "SELECT * " +
		 //        "FROM psuedonumber";
			// ResultSet rs = st.executeQuery(query);
			// ArrayList<Integer> unsortList = new ArrayList<Integer>(); 
			// // ArrayList<String> wayidss = new ArrayList<String>();
			// int new_id = 0;
			// while (rs.next()) {
			// 	int id = rs.getInt("new_id");
			// 	// String osmid = rs.getString("osmid");
			// 	// wayidss.add(osmid);
			// 	unsortList.add(id);							//keeep the values in the new_id in the table already present to the arraylist to determine hghest value
			// }
			// if(unsortList.isEmpty()){
			// 	new_id = 1;

			// }
			// else{
			// 	int c = unsortList.get(0);
			// 	for (int i = 1; i < unsortList.size(); i++){      //sorting the values of oids already present and determinin the highest value
			// 		int b = unsortList.get(i);
			// 		if (b < c){
			// 			c = b;
			// 		}
			// 	}
			// 	new_id = c + 1;
			// }									//highest value + 1
	      	
	      	String check_existence_in_db = "select osmid,id_from_field "+
	      		"from psuedonumber "+
	      		"where osmid = "+osmid;
	      	ResultSet rs2 = st.executeQuery(check_existence_in_db);
	      	// System.out.println("\n");
	      	// System.out.println(kvpair.toString());
	      	// System.out.println("rs2="+rs2.next());
	      	// System.out.println("district="+kvpair.get("kll:district"));
	      	if (!rs2.next() ) {		//this means the osmid is was not found in db
	      		int new_id=0;
	      		String highest_no_query= "select max(new_id) from (select new_id from psuedonumber where district='"+kvpair.get("kll:district")+"' and vdc='"+kvpair.get("kll:vdc")+"' and ward='"+kvpair.get("kll:ward")+"')";
	      		ResultSet highest_no_returned = st.executeQuery(highest_no_query);
	      		System.out.println(highest_no_returned.getInt("max(new_id)"));
	      		new_id = highest_no_returned.getInt("max(new_id)") + 1;
	      		String insert_sql = "INSERT INTO psuedonumber (osmid,district,vdc,ward,new_id) VALUES (" + osmid + ",\'" + kvpair.get("kll:district") + "\',\'" + kvpair.get("kll:vdc") + "\'," + kvpair.get("kll:ward") + ","  + new_id + ")"; 
      			
      			System.out.println(insert_sql);
      			st.executeUpdate(insert_sql);
      			System.out.println("Insert successful for building"+osmid);
      			// connection.commit();
      		// 	st.close();
	      	}
	      	else{
	      		/* update psuedonumber set id_from_field = "04-10-07-02" where osmid = 241110468 */
	      		if(rs2.getString("id_from_field") != kvpair.get("kll:oid")){
	      			String update_id_from_field = "update psuedonumber set id_from_field = \""+kvpair.get("kll:oid")+"\" where osmid = " + osmid;
	      			st.executeUpdate(update_id_from_field);
	      		}
	      		System.out.println("Record exists in db");
	      	}
		}
		catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

	}
		
}