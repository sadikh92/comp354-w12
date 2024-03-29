package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
/**
 * @author Alex Landovskis,Jaffari Rahmatullah
 * @Description Test the case where the task file is not found. 
 * @Last modified 3/5/12 10:52
 */

public class TasksFileNotFound {

	static final String TASKS_NON_EXISTING_FILE = "xml/alltasks.xml";
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	/*
	 * @description Run the test. The proper behavior is to
	 * return an empty ArrayList.
	 */
	public void test()
	{
		InputValidator validator = new InputValidator();
		TaskReader reader = new TaskReader(validator);
		ArrayList<Task> tasks;
		try {
			tasks = reader.loadTasks(TASKS_NON_EXISTING_FILE);
			assertEquals(0, tasks.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
