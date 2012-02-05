package com.Team3.Tardis.TestSuites;

// Libraries.
import junit.framework.Test;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Source.
import com.Team3.Tardis.Tests.Models.People.*;

@RunWith(Suite.class)
@Suite.SuiteClasses( { EmptyPersonFile.class,
					   PeopleFileNotFound.class,
					   PeopleLoad.class,
					   PersonTest.class} )
public class PeopleTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(PeopleTestSuite.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}

}
