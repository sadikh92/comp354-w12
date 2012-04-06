package com.Team3.Tardis.Tests.Models.People;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;


/*
 * @author David Campell
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
			
			// Four people and five tasks should have been loaded.
			assertEquals(4, people.size());
			assertEquals(5, tasks.size());
			
			// Test the 1st person.
			Person first = people.get(0);
			ArrayList<Task> firstTasks = first.getTasks(tasks);
			
			assertEquals(1, firstTasks.size());
			Task firstTask = firstTasks.get(0);
			
			Date firstDate = new Date("01/23/2012");
			assertEquals(0, firstTask.getTaskId());
			assertEquals("My first task", firstTask.getTitle());
			assertEquals("This is my first task...", firstTask.getShortDescription());
			assertEquals(1, firstTask.getDuration());
			assertEquals("First task.doc", firstTask.getDeliverable());
			assertEquals(firstDate, firstTask.getDueDate());
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
			
			// Test the 3rd person.
			Person third = people.get(2);
			ArrayList<Task> thirdTasks = third.getTasks(tasks);
			
			assertEquals(1, thirdTasks.size());
			Task thirdTask = thirdTasks.get(0);
			
			Date thirdDate = new Date("01/23/2012");
			assertEquals(2, thirdTask.getTaskId());
			assertEquals("My first sub-task", thirdTask.getTitle());
			assertEquals("This is my first sub-task...", thirdTask.getShortDescription());
			assertEquals(1, thirdTask.getDuration());
			assertEquals("First sub-task.doc", thirdTask.getDeliverable());
			assertEquals(thirdDate, thirdTask.getDueDate());
			assertEquals(3, thirdTask.getPersonId());
			assertEquals(third.getPersonId(), thirdTask.getPersonId());
			
			// Test the 4th person.
			Person fourth = people.get(3);
			ArrayList<Task> fourthTasks = fourth.getTasks(tasks);
			
			assertEquals(2, fourthTasks.size());
			Task fourthTask1 = fourthTasks.get(0);
			Task fourthTask2 = fourthTasks.get(1);
			
			Date fourthDate1 = new Date("03/11/2012");
			assertEquals(3, fourthTask1.getTaskId());
			assertEquals("My second sub-task", fourthTask1.getTitle());
			assertEquals("This is my second sub-task...", fourthTask1.getShortDescription());
			assertEquals(1, fourthTask1.getDuration());
			assertEquals("Second sub-task.doc", fourthTask1.getDeliverable());
			assertEquals(fourthDate1, fourthTask1.getDueDate());
			assertEquals(4, fourthTask1.getPersonId());
			assertEquals(fourth.getPersonId(), fourthTask1.getPersonId());
			
			Date fourthDate2 = new Date("03/11/2012");
			assertEquals(4, fourthTask2.getTaskId());
			assertEquals("My first sub-task's sub-task", fourthTask2.getTitle());
			assertEquals("This is my first sub-task's sub-task...", fourthTask2.getShortDescription());
			assertEquals(1, fourthTask2.getDuration());
			assertEquals("Second level sub-task.doc", fourthTask2.getDeliverable());
			assertEquals(fourthDate2, fourthTask2.getDueDate());
			assertEquals(4, fourthTask2.getPersonId());
			assertEquals(fourth.getPersonId(), fourthTask2.getPersonId());
			
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
			assertEquals(4, people.size());
			assertEquals(5, tasks.size());
			
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
