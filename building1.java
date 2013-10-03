
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

public class building1{
	static int i=0;
	public static void main(String args[]){ 
		try{
			
			ArrayList <String> buildingidsss =new ArrayList<String>();
			String osmfile="C:\\Users\\Poshan\\Desktop\\building_id\\before_change.osm";
	       //Create a "parser factory" for creating SAX parsers
	    	SAXParserFactory spfac = SAXParserFactory.newInstance();

	       //Now use the parser factory to create a SAXParser object
	    	
	    	SAXParser sp = spfac.newSAXParser();
	       //counting lines
	    	BufferedReader reader = new BufferedReader(new FileReader(osmfile));
			int lines = 0;
			while (reader.readLine()!= null){ 
				lines++;
			}
			reader.close();

			System.out.println(lines);
	       //Finally, tell the parser to parse the input and notify the handler
	       	// sp.parse(osmfile, this);
	       	// cleanNodes(); //removes nodes that exist in ways
    	
			
			DefaultHandler handler1 = new DefaultHandler() {
				
				// public ArrayList<osmbuilding> buildings = new ArrayList<osmbuilding>();
				
				

				// public ArrayList <Double> buildingidsss =new ArrayList<Double>();

				boolean withinWay = false;
			    boolean withinBuilding = false;
			    boolean withinTag = false;
			    // boolean foundFirst = false;
			    // boolean samePoint = false;
			    // double firstId;
			    // boolean deletedData = false;
			    // int totalLines;
			    // int linecount;
				public void startElement(String uri, String localName,
			            String qName, Attributes attributes) throws SAXException {
					
						String buildingid="";
						// System.out.println("Start Element :" + qName);
					
						if (qName.equalsIgnoreCase("way")){
							withinWay = true;
							buildingid=attributes.getValue("id");
							System.out.println("within building ");
							// buildingidsss.add(buildingid);
						}
						

						else if(qName.equalsIgnoreCase("tag")){
							withinTag = true;
							String Key = attributes.getValue("k");
						n	String Value = attributes.getValue("v");
							if (Key == "building"){
									withinBuilding = true;
									System.out.println("within building "+i);
									data(buildingid);
									i=i+1;
							}
						}

						// if (withinBuilding){
	
						// 	// String buildingid = attributes.getValue("id");
						// 	// buildingidsss.add(buildingid);
						// 	System.out.println("building found");
						// 	data(attributes.getValue("id"));
							
						// }
				}
				public void endElement(String uri, String localName, String qName)
            			throws SAXException {
     
					if(qName.equalsIgnoreCase("way")){

						withinWay = false;
					}

					// if(qName.equalsIgnoreCase("tag")){

					// 	withinTag = false;
					// }


     			}
    
					
					

			



				
				// public void endElement(String uri, String localName,
				// String qName) throws SAXException {
		 
				// 	System.out.println("End Element :" + qName);
				
				// }
			
			};
			

			sp.parse("C:\\Users\\Poshan\\Desktop\\building_id\\before_change.osm", handler1);
			

			// for (int i = 0; i < buildingidss.size(); i++){
			//    String item = buildingidss.get(i);
			//    System.out.println("Building_id :" + item);
			// }
		}
	catch (Exception e){
			e.printStackTrace();
	}
	}
		
	
	public static void data(String s){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;			
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/table1/table1","root", "");//my table name username and password

			try{
				if (connection != null) {
					System.out.println("You made it, take control your database now!");
					Statement st = connection.createStatement();
					int val = st.executeUpdate("INSERT table1 VALUES("+i+","+"s"+")");
						// int val = st.executeUpdate("INSERT employee VALUES("+i+","+"'Aman'"+")");
						System.out.println("1 row affected");

				} 
				else {
					System.out.println("Failed to make connection!");
				}
				i=i+1;
			}
			catch (SQLException e) {
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