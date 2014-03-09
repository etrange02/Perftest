package cslave;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cslave.interfaces.ITestManager;
import cslave.protocols.ldap.LDAPComparator;

/**
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManagerTest {

    /**
     * Specifies if there is a need to automatically stop the testManager
     * after the test. That is usefull to avoid multi call to stop (
     * for stop test-methodes and clean-methods
     */
    private boolean stopTestManagerAfter;
    ITestManager testManager;






    /* *********************************************************************
     * INIT/CLEAN METHODS **************************************************
     * *********************************************************************/

    @Before
    public void before() throws IOException {
	testManager = new TestManager();
	testManager.addComparator(new LDAPComparator());
	stopTestManagerAfter = true;
    }

    @After
    public void after() {
	if(stopTestManagerAfter==true) {
	    testManager.stop();
	}
    }

    
    
    
    

    @Test
    public void test() {
	fail("Not yet implemented");
    }
}
