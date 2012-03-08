package com.Team3.Tardis.Views.Tests;
import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskUI;

import junit.framework.*;

/*@author Eric Regnier
 * @description tdb
 */
public class TaskIUTests extends TestCase {
	
	static final String PEOPLE_FILE = "xml/people.xml";
	static final String TASKS_FILE = "xml/tasks.xml";

	
	public TaskIUTests(String name)
	{
		super(name);
	}
	
	private void UpdateTest()
	{
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(TASKS_FILE);
			
			TaskUI taskUI = new TaskUI(new TardisShellMock(), tasks, people);
		}
		catch (Exception ex)
		{
			
		}
	}
}
