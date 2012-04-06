package com.Team3.Tardis.Tests.Models.People;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Util.InputValidator;

import static org.junit.Assert.*;
/**
 * @author Eric Regnier,Alex Landovskis,Jaffari Rahmatullah
 * @Description This is a test case to verify that the 
 * 				people reader handles empty files correctly.
 * @Last modified 3/5/12 10:51
 */

public class EmptyPersonFile {

	static final String EMPTY_PEOPLE_FILE = "xml/empty_people.xml";
	static final String NO_PEOPLE_FILE = "xml/empty_people_xml_tags.xml";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	/*
	 * @description Run the test. The proper behavior is to
	 * return an empty ArrayList if there are no people.
	 */
	public void testEmpty() {
		PeopleReader reader = new PeopleReader(new InputValidator());
		ArrayList<Person> people = null;
		try {
			people = reader.loadPeople(EMPTY_PEOPLE_FILE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, people.size());
	}
	
	@Test
	/*
	 * @description Run the test. The proper behavior is to
	 * return an empty ArrayList if there are no people.
	 */
	public void testMinimalXML() {
		InputValidator validator = new InputValidator();
		PeopleReader reader = new PeopleReader(validator);
		//ArrayList<Person> people = reader.loadPeople(EMPTY_PEOPLE_FILE);
		ArrayList<Person> people;
		try {
			people = reader.loadPeople(NO_PEOPLE_FILE);
			assertEquals(0, people.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
