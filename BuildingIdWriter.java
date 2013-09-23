
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

public class BuildingIdWriter{
	public static void main(String args[]){
		System.out.println("hell first one");
		try{
			String filepath="C:\\Users\\Poshan\\Desktop\\building id\\before_change.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			Node osm = doc.getFirstChild();
			Node way = doc.getElementsByTagName("way").item(0);
			// NamedNodeMap attributes = way.getAttributes();
			// Node nodeAtrr = attributes.getNamedItem("");

			// Element rootNode = doc.getRootElement();

			// Element way = rootNode.getElementsByTagName("way");
			NodeList tag = way.getChildNodes();

			Element 
			
			System.out.println (tag.getLength());
			for (int i= 0; i<tag.getLength(); i++){
				if ("tag".equals(tag.getNodeName())){
					NamedNodeMap attr = way.getAttributes();
					Node key = attr.getNamedItem("k");
					System.out.println(key);
					Node value = attr.getNamedItem("v");
					System.out.println(value);


				}
			}
			System.out.println("Done");
		}
	
		catch (Exception pce) {
			System.out.println("hell");
			pce.printStackTrace();
	   	} 
	   

	}
}