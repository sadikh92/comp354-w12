package com.Team3.Tardis.Tests.Models.Tasks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;

/*
 * @author Babacar Ndiaye
 * @description Test the case where the task file is empty.
 */
public class EmptyTasksFile {
	static final String EMPTY_TASK_FILE = "xml/empty_task.xml";
	static final String NO_TASK_FILE = "xml/empty_task_xml_tags.xml";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test()
	{
		TaskReader reader = new TaskReader(new InputValidator());
		ArrayList<Task> taskList = null;
		try {
			taskList = reader.loadTasks(EMPTY_TASK_FILE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, taskList.size());
	}
}
