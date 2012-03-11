package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskEditor;

public class TaskEditorTests {

	public void setup()
	{
		
	}
	
	public void TestEditWithNoEditing() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			String title = tasks.get(0).getTitle();
		//	long taskAt0Super = tasks.get(0).getSuperTask().getTaskId();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);
	

			taskEdit.updateTask();			
			
			assertEquals(tasks.get(0).getTitle(), "Test title");
			assertEquals(tasks.get(0).getDeliverable(), "Test deliverable");
			assertEquals(tasks.get(0).getPersonId(), people.get(0).getPersonId());
			assertEquals(tasks.get(0).getShortDescription(), "Test desc");
	//		assertEquals(tasks.get(0).getTaskId(), taskAt0Id);
	//		assertEquals(tasks.get(0).getDueDate(), testDate);
			assertNull(tasks.get(0).getSuperTask());
		//	assertEquals(tasks.get(0).getSuperTask().getTaskId(), taskAt0Super);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	@Test
	public void TestEditWithNoSuperTask() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			long taskAt0Id = tasks.get(0).getTaskId();
		//	long taskAt0Super = tasks.get(0).getSuperTask().getTaskId();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);
			
			taskEdit.setTitle("Test title");
			Date testDate = new Date();
			
			taskEdit.setDueDate(testDate);
			taskEdit.setDuration(10);
			taskEdit.setDeliverable("Test deliverable");
			taskEdit.setDescription("Test desc");
			taskEdit.setPerson(people.get(0).getPersonId());
			taskEdit.updateTask();			
			
			assertEquals(tasks.get(0).getTitle(), "Test title");
			assertEquals(tasks.get(0).getDeliverable(), "Test deliverable");
			assertEquals(tasks.get(0).getPersonId(), people.get(0).getPersonId());
			assertEquals(tasks.get(0).getShortDescription(), "Test desc");
			assertEquals(tasks.get(0).getTaskId(), taskAt0Id);
			assertEquals(tasks.get(0).getDueDate(), testDate);
			assertNull(tasks.get(0).getSuperTask());
		//	assertEquals(tasks.get(0).getSuperTask().getTaskId(), taskAt0Super);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
}
