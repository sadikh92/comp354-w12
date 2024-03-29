package com.Team3.Tardis.Tests.Models.People;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Util.InputValidator;
/**
 * @author Alex Landovskis,Jaffari Rahmatullah
 * @Description This is a test case to verify that the 
 * 				people reader handles the case when the people file is not found.
 * @Last modified 3/5/12 10:51
 */


public class PeopleFileNotFound {

	static final String PEOPLE_NON_EXISTING_FILE = "xml/everyone.xml";

	@Test
	/*
	 * @description Run the test. The proper behavior is to
	 * return an empty ArrayList.
	 */
	public void test()
	{
		InputValidator validator = new InputValidator();
		PeopleReader reader = new PeopleReader(validator);
		ArrayList<Person> people;
		try {
			people = reader.loadPeople(PEOPLE_NON_EXISTING_FILE);
			assertEquals(0, people.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
