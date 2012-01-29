package com.Team3.Tardis.Tests.Models.People;

import junit.framework.Test;
import junit.framework.TestSuite;


public class TasksTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(TasksTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(new EmptyTasksFile());
		suite.addTest(new TasksFileNoteFound());
		suite.addTest(new TasksLoad());
		//$JUnit-END$
		return suite;
	}

}
