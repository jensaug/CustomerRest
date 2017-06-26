package services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Customer;

@Path("/MyCustomers")
public class custResJson {

	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response createCustomer(InputStream is) throws IOException, Exception {
		// Customer customer = new Customer();
		Customer customer = readCustomer(is);
		// readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());

		// Response resp;
		// resp = Response.created(URI.create("/customers/" +
		// customer.getId())).build();

		String msg = "Hi " + ", Customer was created";
		// resp.status(200).entity(msg).build();
		return Response.created(URI.create("/customers/" + customer.getId())).status(200).entity(msg).build();
		// return Response.status(200).entity(msg).build();

		// return Response.created(URI.create("/customers/" +
		// customer.getId())).build();

	}

	protected Customer readCustomer(InputStream is) throws IOException, Exception {

		File inputFile = new File(System.getProperty("user.dir") + "/restXml.xml");
		inputFile.createNewFile();
		FileOutputStream out = new FileOutputStream(inputFile);

		int i;
		char c;

		while ((i = is.read()) != -1) {

			// converts integer to character
			c = (char) i;

			if (c != '\n') {

				out.write(c);
			}
			// prints character
			System.out.print(c);
		}
		out.close();

		InputStream is2 = new FileInputStream(System.getProperty("user.dir") + "/restXml.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is2);
		//
		Element root = doc.getDocumentElement();
		System.out.println(root.getAttribute("id"));
		NodeList nodes_O = root.getChildNodes();
		System.out.println(nodes_O.item(0).getNodeType());

		if (nodes_O.item(0).getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) nodes_O.item(i);
			System.out.println("Its an element node");
		}
		System.out.println(nodes_O.getLength());
		Node custElement = nodes_O.item(0);
		String vam = null;
		for (int i1 = 0; i1 < nodes_O.getLength(); i1++) {

			if (!(nodes_O.item(i1).getTextContent().equals(""))) {
				vam = vam + nodes_O.item(i1).getTextContent();

				System.out.println(nodes_O.item(i1).getTextContent());
			}

		}
		System.out.println(vam);
		Customer cust = new Customer();

		System.out.println(root.getAttribute("id"));
		System.out.println(root.getTagName());

		if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals("")) {
			cust.setId(Integer.valueOf(root.getAttribute("id")));
		}
		NodeList nodes = root.getChildNodes();
		for (int i1 = 0; i1 < nodes.getLength(); i1++) {
			Element element = (Element) nodes.item(i1);
			Node curNode = nodes.item(i1);
			if (curNode.getNodeName().equals("firstname")) {
				cust.setFirstName(curNode.getTextContent());
			} else if (curNode.getNodeName().equals("lastname")) {
				cust.setLastName(element.getTextContent());
			} else if (curNode.getNodeName().equals("street")) {
				cust.setStreet(element.getTextContent());
			} else if (curNode.getNodeName().equals("city")) {
				cust.setCity(element.getTextContent());
			} else if (curNode.getNodeName().equals("state")) {
				cust.setState(element.getTextContent());
			} else if (curNode.getNodeName().equals("zip")) {
				cust.setZip(element.getTextContent());
			} else if (curNode.getNodeName().equals("country")) {
				cust.setCountry(element.getTextContent());
			}

			// if (element.getTagName().equals("first-name")) {
			// cust.setFirstName(element.getTextContent());
			// } else if (element.getTagName().equals("last-name")) {
			// cust.setLastName(element.getTextContent());
			// } else if (element.getTagName().equals("street")) {
			// cust.setStreet(element.getTextContent());
			// } else if (element.getTagName().equals("city")) {
			// cust.setCity(element.getTextContent());
			// } else if (element.getTagName().equals("state")) {
			// cust.setState(element.getTextContent());
			// } else if (element.getTagName().equals("zip")) {
			// cust.setZip(element.getTextContent());
			// } else if (element.getTagName().equals("country")) {
			// cust.setCountry(element.getTextContent());
			// }
		}
		return cust;
		// } catch (Exception e) {
		// throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		// }

	}
}