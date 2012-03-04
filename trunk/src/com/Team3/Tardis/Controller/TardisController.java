package com.Team3.Tardis.Controller;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;
import com.Team3.Tardis.Views.PeopleView;
import com.Team3.Tardis.XML.*;
import com.Team3.Tardis.logger.Logger;

/**
 * @author Alex Landovskis
 * @description The main controller that runs the TARDIS task manager.
 *
 */
public class TardisController {

	static final String PEOPLE_FILE = "xml/people.xml";
	static final String TASKS_FILE = "xml/tasks.xml";
	static final String VIEW_FILE = "report/report.txt";
	/**
	 * @param args: The system arguments.
	 */
	public static void main(String[] args) {

		// Load the people.
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
			PeopleView peopleTaskView = new PeopleView();
			peopleTaskView.view(VIEW_FILE, people, tasks);
			
			System.out.println(VIEW_FILE + " has been created.");
			
		} catch (Exception e) {
			Logger.log(TardisController.class.getName(), e.toString());
		}		
	}
}
