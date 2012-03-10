package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskEditor;

public class TaskEditorTests {

	@Test
	public void TestUpdateTask() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);
			// edit
			taskEdit.setTitle("Test");
			taskEdit.updateTask();
			assertEquals(tasks.get(0).getTitle(), "Test");

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
}
