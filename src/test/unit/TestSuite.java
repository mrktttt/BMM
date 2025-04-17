package test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is the global test suite of unit tests.
 * 
 * It includes two test cases : {@link TestFactory} and {@link TestUserManager}.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestFactory.class, TestUserManager.class})
public class TestSuite {

}
