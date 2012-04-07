package com.Team3.Tardis.TestSuites;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Source
import com.Team3.Tardis.Tests.Models.Tasks.*;
/**
 * @author Eric Regnier,Alex Landovskis, David Campbell
 * @description This is a test suite that runs all Task test cases.
 * @Last modified 7/4/12 12:25
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( { EmptyTasksFile.class,
					   TasksFileNotFound.class,
					   TasksLoad.class} )
public class TasksTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(TasksTestSuite.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}