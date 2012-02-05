package com.Team3.Tardis.Tests.Models.People;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;

/**
 * @author Alex Landovskis
 * @description This is a test case to verify that the 
 * 				people reader successfully loads non-empty people files.
 *
 */
public class PeopleLoad {

	static final String PEOPLE_FILE = "tests/people_one_task.xml";
	
	@Test
	/*
	 * @description An ArrayList should be returned containing the people in the file.
	 */
	public void testMultiple()
	{
		InputValidator validator = new InputValidator();
		PeopleReader reader = new PeopleReader(validator);
		ArrayList<Person> people = null;
		
		try {
			people = reader.loadPeople(PEOPLE_FILE);
			// The file should contain 2 people.
			assertEquals(2, people.size());
			
			// Test the 1st person.
			Person first = people.get(0);
			assertEquals(2, first.getPersonId());
			assertEquals("Mark", first.getFirstName());
			assertEquals("Smith", first.getLastName());
			assertEquals("514-744-3333", first.getPhoneNumber());
			assertEquals("2370 Gold Street", first.getAddress());
			assertEquals("Saint-Laurent", first.getCity());
			assertEquals("H4M 1S3", first.getPostalCode());
			assertEquals("Quebec", first.getProvince());
			assertEquals("Canada", first.getCountry());
			
			// Test the 2nd person.
			Person second = people.get(1);
			assertEquals(1, second.getPersonId());
			assertEquals("Joe", second.getFirstName());
			assertEquals("Miller", second.getLastName());
			assertEquals("514-333-1444", second.getPhoneNumber());
			assertEquals("1215 Alexis-Nihon", second.getAddress());
			assertEquals("Saint-Laurent", second.getCity());
			assertEquals("H4R 1Z3", second.getPostalCode());
			assertEquals("Quebec", second.getProvince());
			assertEquals("Canada", second.getCountry());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
