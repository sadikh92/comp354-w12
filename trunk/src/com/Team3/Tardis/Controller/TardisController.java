package com.Team3.Tardis.Controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		// TODO Auto-generated method stub

		InputValidator validator = new InputValidator();
		PeopleReader reader = new PeopleReader(validator);
		ArrayList<Person> people = null;
		
		try {
			people = reader.loadPeople(PEOPLE_FILE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.log(e.toString());
			//e.printStackTrace();
		}
		
	}

}
