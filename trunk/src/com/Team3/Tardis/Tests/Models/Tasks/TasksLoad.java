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
			// The file should contain 4 tasks so that everyone has 1 task assigned to them
			assertEquals(4, tasks.size());
			
			// Test the 1st task.
			Task first = tasks.get(0);
			assertEquals(0, first.getTaskId());
			assertEquals("My first task", first.getTitle());
			assertEquals("This is my first task...", first.getShortDescription());
			assertEquals(1, first.getDuration());
			assertEquals("First task.doc", first.getDeliverable());
			assertEquals("Mon Apr 23 01:00:00 EDT 2012", first.getBeginDate().toString());
			assertEquals("Mon Apr 30 01:00:00 EDT 2012", first.getDueDate().toString());
			assertEquals(2, first.getPersonId());
			assertEquals(null, first.getSuperTask());
			assertEquals(0, first.getCompletionPercentage());
			assertEquals(null, first.getSuccessor());
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
