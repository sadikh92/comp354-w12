package com.Team3.Tardis.TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.Team3.Tardis.Tests.Models.People.EmptyPersonFile;
import com.Team3.Tardis.Tests.Models.People.PeopleFileNotFound;
import com.Team3.Tardis.Tests.Models.People.PeopleLoad;
import com.Team3.Tardis.Tests.Models.People.PersonTest;
import com.Team3.Tardis.Tests.Models.Tasks.EmptyTasksFile;
import com.Team3.Tardis.Tests.Models.Tasks.TasksFileNotFound;
import com.Team3.Tardis.Tests.Models.Tasks.TasksLoad;
import com.Team3.Tardis.Views.Tests.PeopleTaskReport;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Alex Landovskis
 * @description This is a test suite that runs all test cases.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { EmptyPersonFile.class,
					   PeopleFileNotFound.class,
					   PeopleLoad.class,
					   PersonTest.class,
					   EmptyTasksFile.class,
					   TasksFileNotFound.class,
					   TasksLoad.class,
					   PeopleTaskReport.class } )
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}
