package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.io.*;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Util.Logger;
import com.Team3.Tardis.Views.PeopleView;

/**
 * @author Alex Landovskis
 * @description This is a test case to verify that the 
 * 				people table is successfully created.
 *
 */
public class PeopleTaskReport 
{	
	@Test
	/*
	 * @description Everyone is assigned at least one task.
	 */
	public void testOneTask()
	{
		ArrayList<Person> people;
		ArrayList<Task> tasks;
		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		
		try {
			// Load people.
			people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(Common.ONE_TASK_TASKS_FILE);
			
			// Report Generation
			PeopleView peopleTaskView = new PeopleView();
			peopleTaskView.view(Common.ONE_TASK_ACTUAL_FILE, people, tasks);
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(Common.ONE_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(Common.ONE_TASK_ACTUAL_FILE);
			InputStreamReader reportInputStream = new InputStreamReader(reportFileStream);
			BufferedReader reportReader = new BufferedReader(reportInputStream);
			
			// Read the file line by line. 
			// Every line could be equal to the corresponding line in the other file.
			String testLine = testReader.readLine();
			String reportLine = reportReader.readLine();
			while (testLine != null && reportLine != null)
			{
				assertEquals(testLine, reportLine);
				testLine = testReader.readLine();
				reportLine = reportReader.readLine();
			}
			// Both files must have the same number of lines.
			assertNull(Common.ONE_TASK_ACTUAL_FILE + " has more lines than " + Common.ONE_TASK_TEST_FILE, reportLine);
			assertNull(Common.ONE_TASK_TEST_FILE + " has more lines than " + Common.ONE_TASK_ACTUAL_FILE, testLine);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * @description Some people have tasks, others do not. Some tasks are unassigned.
	 */
	public void testNoTask()
	{
		ArrayList<Person> people;
		ArrayList<Task> tasks;
		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		
		try {
			// Load people.
			people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(Common.NO_TASK_TASKS_FILE);
			
			// Report Generation
			PeopleView peopleTaskView = new PeopleView();
			peopleTaskView.view(Common.NO_TASK_ACTUAL_FILE, people, tasks);
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(Common.NO_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(Common.NO_TASK_ACTUAL_FILE);
			InputStreamReader reportInputStream = new InputStreamReader(reportFileStream);
			BufferedReader reportReader = new BufferedReader(reportInputStream);
			
			// Read the file line by line. 
			// Every line could be equal to the corresponding line in the other file.
			String testLine = testReader.readLine();
			String reportLine = reportReader.readLine();
			while (testLine != null && reportLine != null)
			{
				assertEquals(testLine, reportLine);
				testLine = testReader.readLine();
				reportLine = reportReader.readLine();
			}
			// Both files must have the same number of lines.
			assertNull(Common.NO_TASK_ACTUAL_FILE + " has more lines than " + Common.NO_TASK_TEST_FILE, reportLine);
			assertNull(Common.NO_TASK_TEST_FILE + " has more lines than " + Common.NO_TASK_ACTUAL_FILE, testLine);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
