package com.Team3.Tardis.XML;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.Helper.XPathHelper;
import com.Team3.Tardis.logger.Logger;

public class PeopleReader implements IPeopleReader {

	private IInputValidator inputValidator;

	public PeopleReader(IInputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}

	@Override
	public ArrayList<Person> loadPeople(String path) {

		Logger.log("loadPeople() - START ");
		Logger.log("docPath = " + path);
		ArrayList<Person> people = new ArrayList<Person>();
		InputStream is = null;

		try {
			is = new FileInputStream(path);

			Logger.log("is = " + is);
			org.w3c.dom.Document doc = XPathHelper.getDocument(is);

			String query = "//people/person";
			Logger.log("query = " + query);
			NodeList nl = XPathHelper.getNodeList(doc, query);
			Logger.log("nl.getLength() = " + nl.getLength());

			for (int i = 0; i < nl.getLength(); i++) {

				Node personNode = nl.item(i);
				String errorMessage = inputValidator.validatePerson(personNode);
				if (errorMessage == "") // input is correct
				{
					Person p = loadPerson(nl.item(i));
					people.add(p);
				}
				else
				{
					throw new InputMismatchException(errorMessage);
				}
			}

			if (is != null)
				is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log("FILENOTFOUND EXCEPTION " + e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Logger.log(" MalformedURLException " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("IOException " + e.getMessage());
		}

		Logger.log("loadPeople() - END ");
		return people;
	}
	
	// new logic to read start
	public ArrayList<Person> loadPeople_NEW_LOGIC(String path) {

		Logger.log("loadPeople() - START ");
		Logger.log("docPath = " + path);
		ArrayList<Person> people = new ArrayList<Person>();
		InputStream is = null;

		try {
			is = new FileInputStream(path);
			Logger.log("is = " + is);
			
			JXPathContext ctx = XPathHelper.getDocumentContext(is);

			Iterator<Pointer> peopleIt = ctx.iteratePointers("/people/person");
			while(peopleIt.hasNext()){

				Person person = new Person();
				Pointer personPtr = peopleIt.next();
				JXPathContext personCtx = ctx.getRelativeContext(personPtr);
				Object id = personCtx.getValue("id");
				Object firstName = personCtx.getValue("firstName");
				Object lastName = personCtx.getValue("lastName");
				
				person.setPersonId(Integer.parseInt((id == null)?"-1":(String)id));
				person.setFirstName(((firstName == null)?"":(String)firstName));
				person.setLastName(((lastName == null)?"":(String)lastName));
				
				Logger.log(person.getPersonId() + " : " + person.getFirstName() + " " + person.getLastName());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log("FileNotFoundException " + e.getMessage());
		}
		finally {
			if(is != null) {
				try {
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Logger.log(" MalformedURLException " + e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Logger.log("IOException " + e.getMessage());
				}
			}
		}

		Logger.log("loadPeople() - END ");
		return people;
	}
	// new logic to read end

	private Person loadPerson(Node personNode) {

		Person person = new Person();

		Node n = personNode.getChildNodes().item(1);
		if (n != null && n.getFirstChild() != null)
			person.setPersonId(Integer.parseInt(n.getFirstChild()
					.getNodeValue()));

		n = personNode.getChildNodes().item(3);
		if (n != null && n.getFirstChild() != null)
			person.setFirstName(n.getFirstChild().getNodeValue());

		n = personNode.getChildNodes().item(5);
		if (n != null && n.getFirstChild() != null)
			person.setLastName(n.getFirstChild().getNodeValue());

		return person;
	}
}
