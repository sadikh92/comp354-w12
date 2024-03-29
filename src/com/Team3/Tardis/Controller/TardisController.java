package com.Team3.Tardis.Controller;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;
import com.Team3.Tardis.Models.XML.*;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Util.Logger;
import com.Team3.Tardis.Views.TardisShell;

/**
 * @author Alex Landovskis,Jaffari Rahmatullah,David Campbell,Babacar Ndiaye
 * @description The main controller that runs the TARDIS task manager.
 * @Last modified 4/5/12 19:44
 */
public class TardisController {

	public static final String PEOPLE_FILE = "xml/people.xml";
	public static final String TASKS_FILE = "xml/tasks.xml";
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
			
			//Set status and completion
			for (Task tempTask : tasks)
			{
				tempTask.updateCompletion();			
			}
			
			//Create the interactive GUI
			TardisShell.createAndShowGUI(tasks, people);
			
		} catch (Exception e) {
			Logger.log(TardisController.class.getName(), e.toString());
		}
	}
}