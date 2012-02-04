package com.Team3.Tardis.Tests.Views;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.io.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.XML.InputValidator;
import com.Team3.Tardis.XML.PeopleReader;
import com.Team3.Tardis.logger.Logger;


public class PeopleTaskReport 
{
	static final String ONE_TASK_TEST_FILE = "report/report_test.txt";
	static final String NO_TASK_TEST_FILE = "report/report_unassigned.txt";
	static final String REPORT_FILE = "report/report.txt";
	
	@Test
	/*
	 * @description Everyone is assigned at least one task.
	 */
	public void testOneTask()
	{
		try {
			// Report Generation
			// PeopleTaskReport.report().
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(ONE_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(REPORT_FILE);
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
			assertNull(REPORT_FILE + " has more lines than " + ONE_TASK_TEST_FILE, reportLine);
			assertNull(ONE_TASK_TEST_FILE + " has more lines than " + REPORT_FILE, testLine);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		try {
			// Report Generation
			// PeopleTaskReport.report().
			
			// Test file.
			FileInputStream testFileStream = new FileInputStream(NO_TASK_TEST_FILE);
			InputStreamReader testInputStream = new InputStreamReader(testFileStream);
			BufferedReader testReader = new BufferedReader(testInputStream);
			
			// Actual report file.
			FileInputStream reportFileStream = new FileInputStream(REPORT_FILE);
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
			assertNull(REPORT_FILE + " has more lines than " + NO_TASK_TEST_FILE, reportLine);
			assertNull(NO_TASK_TEST_FILE + " has more lines than " + REPORT_FILE, testLine);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
