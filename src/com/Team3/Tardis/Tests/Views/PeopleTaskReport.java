package com.Team3.Tardis.Tests.Views;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.io.*;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Views.PeopleTaskView;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;
import com.Team3.Tardis.XML.TaskReader;
import com.Team3.Tardis.logger.Logger;

/**
 * @author Alex Landovskis
 * @description This is a test case to verify that the 
 * 				people table is successfully created.
 *
 */
public class PeopleTaskReport 
{
	static final String ONE_TASK_TEST_FILE = "report/report_test.txt";
	static final String ONE_TASK_ACTUAL_FILE = "report/report_one_task.txt";
	static final String PEOPLE_FILE = "tests/people_one_task.xml";
	static final String ONE_TASK_TASKS_FILE = "tests/tasks_one_task.xml";
	static final String NO_TASK_TEST_FILE = "report/report_unassigned.txt";
	static final String NO_TASK_ACTUAL_FILE = "report/report_no_task.txt";
	static final String NO_TASK_TASKS_FILE = "tests/tasks_unassigned.xml";
	
	
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
			people = peopleReader.loadPeople(PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(ONE_TASK_TASKS_FILE);
			
			// Report Generation
			PeopleTaskView peopleTaskView = new PeopleTaskView();
			peopleTaskView.view(ONE_TASK_ACTUAL_FILE, people, tasks);
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(ONE_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(ONE_TASK_ACTUAL_FILE);
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
			assertNull(ONE_TASK_ACTUAL_FILE + " has more lines than " + ONE_TASK_TEST_FILE, reportLine);
			assertNull(ONE_TASK_TEST_FILE + " has more lines than " + ONE_TASK_ACTUAL_FILE, testLine);	
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
			people = peopleReader.loadPeople(PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(NO_TASK_TASKS_FILE);
			
			// Report Generation
			PeopleTaskView peopleTaskView = new PeopleTaskView();
			peopleTaskView.view(NO_TASK_ACTUAL_FILE, people, tasks);
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(NO_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(NO_TASK_ACTUAL_FILE);
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
			assertNull(NO_TASK_ACTUAL_FILE + " has more lines than " + NO_TASK_TEST_FILE, reportLine);
			assertNull(NO_TASK_TEST_FILE + " has more lines than " + NO_TASK_ACTUAL_FILE, testLine);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
