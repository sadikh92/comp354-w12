package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Util.Logger;

/**
 * @author Eric Regnier,Alex Landovskis,Jaffari Rahmatullah,David Campbell,Babacar Ndiaye
 * @description This is a test case to verify that the 
 * 				task reader successfully loads non-empty task files.
 * @Last modified 4/5/12 20:01
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
			// The file should contain 4 tasks.
			assertEquals(4, tasks.size());
			
			// Test the 1st task.
			
			Task first = tasks.get(0);
			assertEquals(0, first.getTaskId());
			assertEquals("My first task", first.getTitle());
			assertEquals("This is my first task...", first.getShortDescription());
			assertEquals(1, first.getDuration());
			assertEquals("First task.doc", first.getDeliverable());
			assertEquals("Mon Jan 23 00:00:00 EST 2012", first.getDueDate().toString());
			assertEquals(2, first.getPersonId());
			assertEquals(null, first.getSuperTask());
			assertEquals(0, first.getCompletionPercentage());
			assertEquals(null, first.getSuccessor());
						
			Task second = tasks.get(1);
			assertEquals(1, second.getTaskId());
			assertEquals("My second task", second.getTitle());
			assertEquals("This is my second task...", second.getShortDescription());
			assertEquals(3, second.getDuration());
			assertEquals("Second task.doc", second.getDeliverable());
			assertEquals("Fri Jan 27 00:00:00 EST 2012", second.getDueDate().toString());
			assertEquals(1, second.getPersonId());
			assertEquals(null, second.getSuperTask());
			assertEquals(0, second.getCompletionPercentage());
			assertEquals(0, second.getSuccessor().getTaskId());
			
			
			Task third = tasks.get(2);
			assertEquals(2, third.getTaskId());
			assertEquals("My first sub-task", third.getTitle());
			assertEquals("This is my first sub-task...", third.getShortDescription());
			assertEquals(1, third.getDuration());
			assertEquals("First sub-task.doc", third.getDeliverable());
			assertEquals("Mon Jan 23 00:00:00 EST 2012", third.getDueDate().toString());
			assertEquals(3, third.getPersonId());
			assertEquals(0, third.getSuperTask().getTaskId());
			assertEquals(0, third.getCompletionPercentage());
			assertEquals(0, third.getSuccessor().getTaskId());
			
			Task fourth = tasks.get(3);
			assertEquals(3, fourth.getTaskId());
			assertEquals("My second sub-task", fourth.getTitle());
			assertEquals("This is my second sub-task...", fourth.getShortDescription());
			assertEquals(1, fourth.getDuration());
			assertEquals("Second sub-task.doc", fourth.getDeliverable());
			assertEquals("Sun Mar 11 00:00:00 EST 2012", fourth.getDueDate().toString());
			assertEquals(4, fourth.getPersonId());
			assertEquals(1, fourth.getSuperTask().getTaskId());
			assertEquals(0, fourth.getCompletionPercentage());
			assertEquals(1, fourth.getSuccessor().getTaskId());
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
