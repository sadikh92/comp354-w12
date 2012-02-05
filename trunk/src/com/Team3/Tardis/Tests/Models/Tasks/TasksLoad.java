package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;
import com.Team3.Tardis.XML.TaskReader;
import com.Team3.Tardis.logger.Logger;


public class TasksLoad {
	static final String TASKS_FILE = "xml/tasks.xml";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test()
	{
		InputValidator validator = new InputValidator();
		TaskReader reader = new TaskReader(validator);
		ArrayList<Task> tasks;
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
			assertEquals(0, first.getPersonId());
			//Test the subtasks(subtasks not being read yet)
			ArrayList<Task> subTaskList=first.getSubtasks();
			   // The file should contain 2 subtasks.
			    assertEquals(2, subTaskList.size());
			       // Test the 1st subtask.
						Task sub1 = subTaskList.get(0);
						assertEquals(0, sub1.getTaskId());
						assertEquals("My first sub-task", sub1.getTitle());
						assertEquals("This is my first sub-task...", sub1.getShortDescription());
						assertEquals(1, sub1.getDuration());
						assertEquals("First sub-task.doc", sub1.getDeliverable());
						assertEquals("Mon Jan 23 00:00:00 EST 2012", sub1.getDueDate().toString());
						// Test the 2nd subtask.
						Task sub2 = subTaskList.get(1);
						assertEquals(0, sub2.getTaskId());
						assertEquals("My second sub-task", sub2.getTitle());
						assertEquals("This is my second sub-task...", sub2.getShortDescription());
						assertEquals(1, sub2.getDuration());
						assertEquals("Second sub-task.doc", sub2.getDeliverable());
						assertEquals("Mon Jan 23 00:00:00 EST 2012", sub2.getDueDate());
						
			
						
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
			ArrayList<Task>emptySubTaskList=second.getSubtasks();
			   // The file should contain no subtasks.
			    assertEquals(0, emptySubTaskList.size());
			       
		    }
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
