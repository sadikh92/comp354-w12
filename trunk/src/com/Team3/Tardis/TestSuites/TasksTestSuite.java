package com.Team3.Tardis.TestSuites;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Source
import com.Team3.Tardis.Tests.Models.Tasks.*;
import com.Team3.Tardis.Views.Tests.TaskEditorTests;
import com.Team3.Tardis.Views.Tests.TaskViewTests;

/**
 * @author Alex Landovskis
 * @description This is a test suite that runs all Task test cases.
 * @version 2. Updated by Eric Regnier
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { EmptyTasksFile.class,
					   TasksFileNotFound.class,
					   TasksLoad.class,
					   TaskEditorTests.class,
					   TaskViewTests.class} )
public class TasksTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(TasksTestSuite.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}