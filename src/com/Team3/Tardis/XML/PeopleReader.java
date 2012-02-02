package com.Team3.Tardis.XML;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.Helper.XPathHelper;
import com.Team3.Tardis.logger.Logger;

public class PeopleReader implements IPeopleReader {

	private IInputValidator inputValidator;

	public PeopleReader(IInputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}

	@Override
	public ArrayList<Person> loadPeople(String path) throws Exception {

		Logger.log("loadPeople() - START ");
		Logger.log("docPath = " + path);
		ArrayList<Person> people = new ArrayList<Person>();
		InputStream is = null;

		try {
			is = new FileInputStream(path);
			Logger.log("is = " + is);

			JXPathContext ctx = XPathHelper.getDocumentContext(is);

			if (ctx != null) {

				Iterator<Pointer> peopleIt = ctx
						.iteratePointers("/people/person");

				while (peopleIt.hasNext()) {

					Pointer personPtr = peopleIt.next();					
					Person person = loadPerson(ctx
							.getRelativeContext(personPtr));
					people.add(person);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log("FileNotFoundException " + e.getMessage());
			
		}finally {
			if (is != null) {
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

	private Person loadPerson(JXPathContext personCtx) throws Exception {

		String errorMessage = inputValidator.validatePerson(personCtx);
		if (errorMessage == "") {
			Person person = new Person();

			// required fields
			person.setPersonId(Integer.parseInt(personCtx.getValue("id")
					.toString()));
			person.setFirstName(personCtx.getValue("firstName").toString());
			person.setLastName(personCtx.getValue("lastName").toString());

			// non required fields
			person.setAddress(personCtx.getValue("address") == null ? ""
					: personCtx.getValue("address").toString());
			person.setCity(personCtx.getValue("city") == null ? "" : personCtx
					.getValue("city").toString());
			person.setCountry(personCtx.getValue("country") == null ? ""
					: personCtx.getValue("country").toString());
			person.setPhoneNumber(personCtx.getValue("phoneNumber") == null ? ""
					: personCtx.getValue("phoneNumber").toString());
			person.setPostalCode(personCtx.getValue("postalCode") == null ? ""
					: personCtx.getValue("postalCode").toString());
			person.setProvince(personCtx.getValue("province") == null ? ""
					: personCtx.getValue("province").toString());
			return person;
		} else
			throw new Exception(errorMessage);
	}
}
