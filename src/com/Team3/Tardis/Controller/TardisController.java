package com.Team3.Tardis.Controller;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;
import com.Team3.Tardis.logger.Logger;

public class TardisController {

	static final String PEOPLE_FILE = "xml/people.xml";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		InputValidator validator = new InputValidator();
		PeopleReader reader = new PeopleReader(validator);
		ArrayList<Person> people;
		
		try {
			people = reader.loadPeople(PEOPLE_FILE);
		} catch (Exception e) {
			Logger.log(TardisController.class.getName(), e.toString());
		}		
	}
}
