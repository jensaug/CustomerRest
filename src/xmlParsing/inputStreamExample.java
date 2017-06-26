package xmlParsing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class inputStreamExample {

	@Test
	public void result() throws ParserConfigurationException, SAXException, IOException {

		InputStream is = new FileInputStream("c:\\files\\sam.xml");
		// use the factory to create a documentbuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		// create a new document from input stream
		// FileInputStream fis = new FileInputStream("Student.xml");
		Document doc = builder.parse(is);

		// get the first element
		Element element = doc.getDocumentElement();

		// get all child nodes
		NodeList nodes = element.getChildNodes();

		// print the text content of each child
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println("" + nodes.item(i).getTextContent());
		}
	}
}
