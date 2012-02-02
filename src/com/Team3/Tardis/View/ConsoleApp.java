package com.Team3.Tardis.View;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;

public class ConsoleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PeopleReader pr = new PeopleReader(new InputValidator());
		ConsoleOutputter outputter = new ConsoleOutputter();
		System.out.println("-----------");
		System.out.println("Start of App");
		try {
			ArrayList<Person> people = pr.loadPeople("xml/people.xml");
			System.out.println(outputter.outputResults(null, people));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("-----------");
		System.out.println("End of App");
	}
}
