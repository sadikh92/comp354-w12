package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.junit.Test;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskView;

/**
 * @author Eric Regnier
 * @Description Tests the task report.
 */
public class TaskViewTests {

	@Test
	public void testViewMethod() {		
		
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			TaskView taskView = new TaskView();
			
			//the method to test
			taskView.view(Common.REPORT_TASK_ACTUAL, people, tasks);
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(Common.REPORT_TASK_TEST);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(Common.REPORT_TASK_ACTUAL);
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
			assertNull(Common.REPORT_TASK_ACTUAL + " has more lines than " + Common.REPORT_TASK_ACTUAL, reportLine);
			assertNull(Common.REPORT_TASK_ACTUAL + " has more lines than " + Common.REPORT_TASK_ACTUAL, testLine);
			
		} catch (Exception e) {
			fail("exception occured");
		}		
	}
}
