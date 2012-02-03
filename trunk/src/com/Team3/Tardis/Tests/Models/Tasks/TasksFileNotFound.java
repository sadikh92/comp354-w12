package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.TaskReader;


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
