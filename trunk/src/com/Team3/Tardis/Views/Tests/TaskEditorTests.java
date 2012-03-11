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


/**
 * @author Eric Regnier
 */
public class TaskEditorTests {

	@Test
	public void TestEditWithNoEditing() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			String title = tasks.get(0).getTitle();
			String deliverable = tasks.get(0).getDeliverable();
			long personId = tasks.get(0).getPersonId();
			String desc = tasks.get(0).getShortDescription();
			long id = tasks.get(0).getTaskId();
			Date dueDate =  tasks.get(0).getDueDate();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);	

			taskEdit.updateTask();			
			
			Task t = tasks.get(0);
			assertEquals(title, t.getTitle());
			assertEquals(deliverable, t.getDeliverable());
			assertEquals(personId, t.getPersonId());
			assertEquals(desc, t.getShortDescription());
			assertEquals(id, t.getTaskId());
			assertEquals(dueDate.getMonth(), t.getDueDate().getMonth());
			assertEquals(dueDate.getDate(), t.getDueDate().getDate());
			assertEquals(dueDate.getYear(), t.getDueDate().getYear());
			assertNull(t.getSuperTask());

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
			
			Task t = tasks.get(0);
			assertEquals("Test title", t.getTitle());
			assertEquals("Test deliverable", t.getDeliverable());
			assertEquals(tasks.get(0).getPersonId(), people.get(0).getPersonId());
			assertEquals("Test desc", t.getShortDescription());
			assertEquals(taskAt0Id, t.getTaskId());
			assertEquals(testDate.getMonth(), t.getDueDate().getMonth());
			assertEquals(testDate.getDate(), t.getDueDate().getDate());
			assertEquals(testDate.getYear(), t.getDueDate().getYear());
			assertNull(t.getSuperTask());
		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	public void TestEditWithSuperTask() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			long taskAt0Id = tasks.get(2).getTaskId();
			long taskAt0Super = tasks.get(0).getSuperTask().getTaskId();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 2);
			
			taskEdit.setTitle("Test title");
			Date testDate = new Date();
			
			taskEdit.setDueDate(testDate);
			taskEdit.setDuration(10);
			taskEdit.setDeliverable("Test deliverable");
			taskEdit.setDescription("Test desc");
			taskEdit.setPerson(people.get(0).getPersonId());
			taskEdit.updateTask();			
			
			Task t = tasks.get(0);
			assertEquals("Test title", t.getTitle());
			assertEquals("Test deliverable", t.getDeliverable());
			assertEquals(tasks.get(0).getPersonId(), people.get(0).getPersonId());
			assertEquals("Test desc", t.getShortDescription());
			assertEquals(taskAt0Id, t.getTaskId());
			
			assertEquals(testDate.getMonth(), t.getDueDate().getMonth());
			assertEquals(testDate.getDate(), t.getDueDate().getDate());
			assertEquals(testDate.getYear(), t.getDueDate().getYear());
			assertEquals(taskAt0Super, t.getSuperTask().getTaskId());

		} catch (Exception ex) {
			fail("exception occured");
		}
	}

	@Test
	public void TestAdd() {
		try {
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people);
			
			taskEdit.setTitle("Test title");
			Date testDate = new Date();
			
			taskEdit.setDueDate(testDate);
			taskEdit.setDuration(10);
			taskEdit.setDeliverable("Test deliverable");
			taskEdit.setDescription("Test desc");
			taskEdit.setPerson(people.get(0).getPersonId());
			taskEdit.updateTask();			
			
			Task t = tasks.get(tasks.size() - 1);
			assertEquals("Test title", t.getTitle());
			assertEquals("Test deliverable", t.getDeliverable());
			assertEquals( people.get(0).getPersonId(), t.getPersonId());
			assertEquals("Test desc", t.getShortDescription());
			assertNotSame(0, t.getTaskId());
			assertEquals(testDate.getMonth(), t.getDueDate().getMonth());
			assertEquals(testDate.getDate(), t.getDueDate().getDate());
			assertEquals(testDate.getYear(), t.getDueDate().getYear());
			assertEquals(t.getSuperTask().getTaskId(), tasks.get(0).getTaskId());
		} catch (Exception ex) {
			fail("exception occured");
		}
	}
}
