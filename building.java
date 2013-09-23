public class building{
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// public ArrayList<osmNode> osmNodes = new ArrayList<osmNode>();
//     //public ArrayList<osmPolygon> osmPolygons = new ArrayList<osmPolygon>();
//  public ArrayList<osmLine> osmLines = new ArrayList<osmLine>(),
//      osmPolygons = new ArrayList<osmLine>();
String osmfile="C:\\Users\\Poshan\\Desktop\\building id\\before_change.osm";
BufferedReader reader = new BufferedReader(new FileReader(osmfile));
int lines = 0;
while (reader.readLine() != null) lines++;
reader.close();
System.out.println (lines);


}