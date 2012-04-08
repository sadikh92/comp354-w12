package com.Team3.Tardis.TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.Team3.Tardis.Tests.Models.People.*;
import com.Team3.Tardis.Tests.Models.Tasks.*;
import com.Team3.Tardis.Views.Tests.*;

import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * @author Eric Regnier,Alex Landovskis, David Campbell
 * @description This is a test suite that runs all test cases.
 * @Last modified 7/4/12 12:31
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {	EmptyPersonFile.class,
						PeopleFileNotFound.class,
						PeopleLoad.class,
						PersonTest.class,
						EmptyTasksFile.class,
						TasksFileNotFound.class,
						TasksLoad.class,
						TaskEditorTests.class,
						TaskUITests.class,
						TaskViewTests.class,
						TreeViewTests.class})
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}
}
