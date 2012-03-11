package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Util.Logger;

/**
 * @author Bacabar Ndiaye
 * @description This is a test case to verify that the 
 * 				task reader successfully loads non-empty task files.
 *
 */
public class TasksLoad {
	static final String TASKS_FILE = "tests/tasks_one_task.xml";
	
	@Test
	public void test()
	{
		InputValidator validator = new InputValidator();
		TaskReader reader = new TaskReader(validator);
		ArrayList<Task> tasks=null;
		Logger.log("Testing", "Tasks");
		try {
			tasks = reader.loadTasks(TASKS_FILE);
			// The file should contain 2 tasks.
			assertEquals(2, tasks.size());
			
			// Test the 1st task.
			
			Task first = tasks.get(0);
			assertEquals(0, first.getTaskId());
			assertEquals("My first task", first.getTitle());
			assertEquals("This is my first task...", first.getShortDescription());
			assertEquals(1, first.getDuration());
			assertEquals("First task.doc", first.getDeliverable());
			assertEquals("Mon Jan 23 00:00:00 EST 2012", first.getDueDate().toString());//adapted the test to the date format but my 1st choice was "01/23/2012"
			assertEquals(2, first.getPersonId());
			

			// Test the 2nd task.
						
			Task second = tasks.get(1);
			assertEquals(1, second.getTaskId());
			assertEquals("My second task", second.getTitle());
			assertEquals("This is my second task...", second.getShortDescription());
			assertEquals(3, second.getDuration());
			assertEquals("Second task.doc", second.getDeliverable());
			assertEquals("Fri Jan 27 00:00:00 EST 2012", second.getDueDate().toString());
			assertEquals(1, second.getPersonId());

			//Test the subtasks(subtasks not being read yet)
			//ArrayList<Task>emptySubTaskList=second.getSubtasks();
			// The file should contain no subtasks.

			//assertEquals(0, emptySubTaskList.size());
		    }
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
