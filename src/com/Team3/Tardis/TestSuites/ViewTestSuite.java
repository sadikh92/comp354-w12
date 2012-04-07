package com.Team3.Tardis.TestSuites;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Source
import com.Team3.Tardis.Views.Tests.*;
/**
 * @author David Campbell
 * @description This is a test suite that runs all UI related tests.
 * @Last modified 7/4/12 12:24
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( { TaskEditorTests.class,
					   TaskIUTests.class,
					   TaskViewTests.class,
					   TreeViewTests.class} )
public class ViewTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(TasksTestSuite.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}
}
