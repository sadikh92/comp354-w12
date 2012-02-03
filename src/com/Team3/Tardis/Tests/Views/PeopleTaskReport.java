package com.Team3.Tardis.Tests.Views;

import static org.junit.Assert.assertEquals;

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
	static final String PEOPLE_FILE = "xml/people.xml";
	static final String ONE_TASK_TEST_FILE = "report/report_test.txt";
	static final String ONE_TASK_ACTUAL_FILE = "report/report.txt";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

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
			FileInputStream reportFileStream = new FileInputStream(ONE_TASK_ACTUAL_FILE);
			InputStreamReader reportInputStream = new InputStreamReader(reportFileStream);
			BufferedReader reportReader = new BufferedReader(reportInputStream);
			
			// Read the file line by line.
			String testLine = testReader.readLine();
			String reportLine = reportReader.readLine();
			while (testLine != null && reportLine != null)
			{
				assertEquals(testLine, reportLine);
				testLine = testReader.readLine();
				reportLine = reportReader.readLine();
			}
			
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
	 * @description Some people do have a task.
	 */
	public void testNoTask()
	{
		
	}
}
