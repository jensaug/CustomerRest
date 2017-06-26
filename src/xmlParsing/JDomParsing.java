package xmlParsing;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

public class JDomParsing {

	@Test
	public void xmlTest() throws IOException, Exception {
		SAXBuilder saxbuilder = new SAXBuilder();
		File inputFile = new File("c:\\files\\sam.xml");
		Document document = saxbuilder.build(inputFile);
		// Document document = saxbuilder.build(inputFile);
		Element element = document.getRootElement();
		System.out.println(element.getName());
		List<Element> elements = element.getChildren();
		for (Element e : elements) {
			System.out.println(e.getAttributeValue("rollno"));
		}

	}

}
