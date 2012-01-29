package com.Team3.Tardis.TestSuites;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Source
import com.Team3.Tardis.Tests.Models.Tasks.*;

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