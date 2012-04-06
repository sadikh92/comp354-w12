package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;

/**
 * @author Eric Regnier
 * @Description Tests the TaskEditor (task edit popup).
 * @Last modified 3/11/12 13:09
 */
public class TaskEditorTests {

	@Test
	/**
	 * @Description We tests the edit, but without actually editing any data.
	 */
	public void testEditWithNoEditing() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			//before values
			Task before = tasks.get(0);
			long id = before.getTaskId();
			String title = before.getTitle();
			String desc = before.getShortDescription();
			int duration = before.getDuration();
			String deliverable = before.getDeliverable();
			Date dueDate = before.getDueDate();
			long personId = before.getPersonId();
			Task superTask = before.getSuperTask();
			int completionPercentage = before.getCompletionPercentage();
			Task successor = before.getSuccessor();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);	
			
			//just call update. Nothing actually got edited
			taskEdit.updateTask();			
			
			Task after = tasks.get(0);
			assertEquals(id, after.getTaskId());
			assertEquals(title, after.getTitle());
			assertEquals(desc, after.getShortDescription());
			assertEquals(duration, after.getDuration());
			assertEquals(deliverable, after.getDeliverable());
			assertEquals(dueDate.getMonth(), after.getDueDate().getMonth());
			assertEquals(dueDate.getDate(), after.getDueDate().getDate());
			assertEquals(dueDate.getYear(), after.getDueDate().getYear());
			assertEquals(personId, after.getPersonId());
			assertEquals(superTask, after.getSuperTask());
			assertEquals(completionPercentage, after.getCompletionPercentage());
			assertEquals(successor, after.getSuccessor());
			//assertNull(after.getSuperTask());

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	@Test
	/**
	 * @Description Test edit with a task that contains no super task.
	 */
	public void testEditWithNoSuperTask() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			//save the ID to make sure they stay the same after editing
			long taskAt0Id = tasks.get(0).getTaskId();
			
			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people, 0);
			
			//edit the task
			taskEdit.setTitle("Test title");
			Date testDate = new Date();			
			taskEdit.setDueDate(testDate);
			taskEdit.setDuration(10);
			taskEdit.setDeliverable("Test deliverable");
			taskEdit.setDescription("Test desc");
			taskEdit.setPerson(people.get(0).getPersonId());
			taskEdit.updateTask();			
			
			//check the values
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
	
	/**
	 * @Description Test edit with a task that contains a super task.
	 */
	public void testEditWithSuperTask() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			//save the before values to make sure they stay the same after editing
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
			
			//check if everything are equal
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
	/**
	 * @Description Test the add task.
	 */
	public void testAdd() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);

			TaskEditorWrapper taskEdit = new TaskEditorWrapper(new TardisShellMock(), tasks, people);
			
			//sets the data for the new task
			taskEdit.setTitle("Test title");
			Date testDate = new Date();			
			taskEdit.setDueDate(testDate);
			taskEdit.setDuration(10);
			taskEdit.setDeliverable("Test deliverable");
			taskEdit.setDescription("Test desc");
			taskEdit.setPerson(people.get(0).getPersonId());
			taskEdit.updateTask();			
			
			//the last task is the one that just got added
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
