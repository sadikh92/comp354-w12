import junit.framework.Test;
import junit.framework.TestSuite;


public class PeopleTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(PeopleTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(new EmptyPersonFile());
		suite.addTest(new PeopleFileNoteFound());
		suite.addTest(new PeopleLoad());
		//$JUnit-END$
		return suite;
	}

}
