package com.Team3.Tardis.XML;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.Helper.XPathHelper;
import com.Team3.Tardis.logger.Logger;

public class PeopleReader implements IPeopleReader {

	@Override
	public ArrayList<Person> loadPeople(String path) {

		Logger.log("loadPeople() - START ");
		Logger.log("docPath = " + path);
		ArrayList<Person> people = new ArrayList<Person>();
		InputStream is =  null;
		
		try {
			is = new FileInputStream(path);

			Logger.log("is = " + is);			
			org.w3c.dom.Document doc = XPathHelper.getDocument(is);
			
			String query = "//people/person";
			Logger.log("query = " + query);
			NodeList nl = XPathHelper.getNodeList(doc, query);
			Logger.log("nl.getLength() = " + nl.getLength());
			for(int i=0; i< nl.getLength(); i++)
			{
				Person person = new Person();
				
				Node n = nl.item(i).getChildNodes().item(1);
				if(n != null && n.getFirstChild() != null)
					person.setPersonId(Integer.parseInt(n.getFirstChild().getNodeValue()));
					
				n = nl.item(i).getChildNodes().item(3);
				if(n != null && n.getFirstChild() != null)
					person.setFirstName(n.getFirstChild().getNodeValue());
					
				n = nl.item(i).getChildNodes().item(5);
				if(n != null && n.getFirstChild() != null)
					person.setLastName(n.getFirstChild().getNodeValue());

				people.add(person);
			}
			
			if(is != null)
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
}
