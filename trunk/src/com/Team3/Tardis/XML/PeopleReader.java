package com.Team3.Tardis.XML;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
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

/**
 * @description Reads people from a file.
 *
 */
public class PeopleReader implements IPeopleReader {

	private IInputValidator inputValidator;

	public PeopleReader(IInputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}

	/**
	 * @description Reads the people from the file.
	 * @param path The path to the people file.
	 *
	 */
	@Override
	public ArrayList<Person> loadPeople(String path) throws Exception {

		Logger.log(PeopleReader.class.getName(), "loadPeople() - START ");
		Logger.log(PeopleReader.class.getName(), "docPath = " + path);
		ArrayList<Person> people = new ArrayList<Person>();
		InputStream is = null;
		File file = null;
		
		try {
		
			file = new File(path);
			
			// The file does not exist.
			if (!file.exists()) {
				System.out.println(path + " does not exist.");
				return people;
			}
		
			// The file exists.
			is = new FileInputStream(path);
			Logger.log(PeopleReader.class.getName(), "is = " + is);
			
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
			Logger.log(PeopleReader.class.getName(), "FileNotFoundException " + e.getMessage());
			
		}finally {
			if (is != null) {
				try {
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Logger.log(PeopleReader.class.getName(), "MalformedURLException " + e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Logger.log(PeopleReader.class.getName(), "IOException " + e.getMessage());
				}
			}
		}

		Logger.log(PeopleReader.class.getName(), "loadPeople() - END ");
		return people;
	}

	/**
	 * @description Reads a person from the file.
	 * @param personCtx The XML context containing one person.
	 *
	 */
	private Person loadPerson(JXPathContext personCtx) throws Exception {
		
		Logger.log(PeopleReader.class.getName(), "loadPerson() - START ");
		
		String errorMessage = inputValidator.validatePerson(personCtx);
		
		if (errorMessage.equals("")) { // No errors occurred.
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

			Logger.log(PeopleReader.class.getName(), "loadPerson() - END ");
			return person;
		} else { // Errors occurred.
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - errorMessage = " + errorMessage);
			throw new Exception(errorMessage);
		}
	}
}