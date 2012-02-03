package com.Team3.Tardis.Controller;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.*;
import com.Team3.Tardis.logger.Logger;

public class TardisController {

	static final String PEOPLE_FILE = "xml/people.xml";
	static final String TASKS_FILE = "xml/tasks.xml";
	/**
	 * @param args: The system arguments.
	 */
	public static void main(String[] args) {

		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		ArrayList<Person> people;
		ArrayList<Task> tasks;
		
		try {
			// Load people.
			people = peopleReader.loadPeople(PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(TASKS_FILE);
			
			// Prepare report.
		} catch (Exception e) {
			Logger.log(TardisController.class.getName(), e.toString());
		}		
	}
}
