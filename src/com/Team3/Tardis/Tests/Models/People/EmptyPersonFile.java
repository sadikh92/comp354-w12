package com.Team3.Tardis.Tests.Models.People;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;
import static org.junit.Assert.*;

/**
 * 
 */

/**
 * @author j_landov, 
 *
 */
public class EmptyPersonFile {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test() {
		String path = "xml/empty_people.xml";
		PeopleReader reader = new PeopleReader(new InputValidator());
		//ArrayList<Person> people = reader.loadPeople(path);
		ArrayList<Person> people = null;
		try {
			people = reader.loadPeople(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, people.size());
	}

}
