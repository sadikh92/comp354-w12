package com.Team3.Tardis.Tests.Models.People;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.InputValidator;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;


/*
 * @description Test the methods of the Person class.
 */
public class PersonTest {
	static final String PEOPLE_FILE = "xml/people.xml";
	static final String TASKS_FILE = "xml/tasks.xml";
	
	@Test
	/*
	 * @description People should have the tasks they are assigned returned.
	 */
	public void test()
	{
		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		ArrayList<Person> people = null;
		ArrayList<Task> tasks = null;
		
		try {
			// Load the people and tasks.
			people = peopleReader.loadPeople(PEOPLE_FILE);
			tasks = taskReader.loadTasks(TASKS_FILE);
			
			// Two people and two tasks should have been loaded.
			assertEquals(2, people.size());
			assertEquals(2, tasks.size());
			
			// Test the 1st person.
			Person first = people.get(0);
			ArrayList<Task> firstTasks = first.getTasks(tasks);
			
			assertEquals(1, firstTasks.size());
			Task firstTask = firstTasks.get(0);
			
			Date testDate = new Date("01/23/2012");
			assertEquals(0, firstTask.getTaskId());
			assertEquals("My first task", firstTask.getTitle());
			assertEquals("This is my first task...", firstTask.getShortDescription());
			assertEquals(1, firstTask.getDuration());
			assertEquals("First task.doc", firstTask.getDeliverable());
			assertEquals(testDate, firstTask.getDueDate());
			assertEquals(2, firstTask.getPersonId());
			assertEquals(first.getPersonId(), firstTask.getPersonId());
			
			// Test the 2nd person.
			Person second = people.get(1);
			ArrayList<Task> secondTasks = second.getTasks(tasks);
			
			assertEquals(1, secondTasks.size());
			Task secondTask = secondTasks.get(0);
			
			Date secondDate = new Date("01/27/2012");
			assertEquals(1, secondTask.getTaskId());
			assertEquals("My second task", secondTask.getTitle());
			assertEquals("This is my second task...", secondTask.getShortDescription());
			assertEquals(3, secondTask.getDuration());
			assertEquals("Second task.doc", secondTask.getDeliverable());
			assertEquals(secondDate, secondTask.getDueDate());
			assertEquals(1, secondTask.getPersonId());
			assertEquals(second.getPersonId(), secondTask.getPersonId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * @description The getTasks method on the Person should return no tasks if the task is assigned to a non-existent person.
	 */
	public void testUnassigned()
	{
		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		ArrayList<Person> people = null;
		ArrayList<Task> tasks = null;
		
		try {
			// Load the people and tasks.
			people = peopleReader.loadPeople(PEOPLE_FILE);
			tasks = taskReader.loadTasks(TASKS_FILE);
			
			// Two people and two tasks should have been loaded.
			assertEquals(2, people.size());
			assertEquals(2, tasks.size());
			
			// Test the 1st person.
			Person first = people.get(0);
			ArrayList<Task> firstTasks = first.getTasks(tasks);
			
			assertEquals(1, firstTasks.size());
			Task firstTask = firstTasks.get(0);
			
			// Test the 2nd person.
			Person second = people.get(1);
			ArrayList<Task> secondTasks = second.getTasks(tasks);
			
			assertEquals(1, secondTasks.size());
			Task secondTask = secondTasks.get(0);
			
			Date secondDate = new Date("01/27/2012");
			assertEquals(1, secondTask.getTaskId());
			assertEquals("My second task", secondTask.getTitle());
			assertEquals("This is my second task...", secondTask.getShortDescription());
			assertEquals(3, secondTask.getDuration());
			assertEquals("Second task.doc", secondTask.getDeliverable());
			assertEquals(secondDate, secondTask.getDueDate());
			assertEquals(1, secondTask.getPersonId());
			assertEquals(second.getPersonId(), secondTask.getPersonId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
