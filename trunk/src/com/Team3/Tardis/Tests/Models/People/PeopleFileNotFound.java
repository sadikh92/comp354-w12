package com.Team3.Tardis.Tests.Models.People;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import org.junit.Before;
import org.junit.BeforeClass;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;

/**
 * @author Alex Landovskis
 * @description This is a test case to verify that the 
 * 				people reader handles the case when the people file is not found.
 *
 */
public class PeopleFileNotFound {

	static final String PEOPLE_NON_EXISTING_FILE = "xml/everyone.xml";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

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
