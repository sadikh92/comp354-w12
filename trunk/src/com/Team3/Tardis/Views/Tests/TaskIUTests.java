package com.Team3.Tardis.Views.Tests;
import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskEditor;
import junit.framework.*;

/*@author Eric Regnier
 * @description tdb
 */
public class TaskIUTests extends TestCase {


	
	public TaskIUTests(String name)
	{
		super(name);
	}
	
	private void TestDelete()
	{
		
	}
	
	private void TestAdd()
	{
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskEditor taskEdit = new TaskEditor(new TardisShellMock(), tasks, people, 0);
			
			
		}
		catch (Exception ex)
		{
			
		}
	}
}
