/**
 * 
 */
package cslave;

import java.util.ArrayList;
import java.util.List;

import cslave.interfaces.IScenario;
import cslave.interfaces.ITestManager;
import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManager implements ITestManager {
	
	private TestParameter testRunner;
	private TCPConnectionToMaster[] TCPConnectionToMaster;
	private List<IScenario> scenario;
	private AbstractTest abstractTest;
	private List<TCPConnectionToTestedServer> TCPConnectionToTestedServer;
	private List<Comparator> comparator;
	
	public TestManager() {
		this.TCPConnectionToMaster = new TCPConnectionToMaster[2];
		this.scenario = new ArrayList<>();
		this.TCPConnectionToTestedServer = new ArrayList<>();
		this.comparator = new ArrayList<>();
	}

	/**
	 * Returns the TestParameter associated
	 * @return the TestParameter
	 */
	public TestParameter getTestRunner() {
		return testRunner;
	}

	/**
	 * Modifies the associated TestParameter
	 * @param testRunner a TestParameter
	 */
	public void setTestRunner(TestParameter testRunner) {
		this.testRunner = testRunner;
	}
	
	/**
	 * Returns an array of TCPConnectionToMaster
	 * @return an array
	 */
	public TCPConnectionToMaster[] getTCPConnectionToMaster() {
		return TCPConnectionToMaster;
	}

	/**
	 * Modifies the array of TCPConnectionToMaster
	 * @param TCPConnectionToMaster
	 */
	public void setTCPConnectionToMaster( TCPConnectionToMaster[] TCPConnectionToMaster) {
		this.TCPConnectionToMaster = TCPConnectionToMaster;
	}
	
	/**
	 * Returns a list of scenarios
	 * @return a list
	 */
	public List<IScenario> getScenario() {
		return scenario;
	}

	/**
	 * Modifies the list of scenarios
	 * @param scenario a list
	 */
	public void setScenario(List<IScenario> scenario) {
		this.scenario = scenario;
	}

	/**
	 * Returns the associated test
	 * @return
	 */
	public AbstractTest getAbstractTest() {
		return abstractTest;
	}

	/**
	 * Modifies the associated test
	 * @param abstractTest a test
	 */
	public void setAbstractTest(AbstractTest abstractTest) {
		this.abstractTest = abstractTest;
	}

	/**
	 * Returns the list of TCPConnectionToTestedServer handled by the test manager
	 * @return a list
	 */
	public List<TCPConnectionToTestedServer> getTCPConnectionToTestedServer() {
		return TCPConnectionToTestedServer;
	}

	/**
	 * Modifies the list of TCPConnectionToTestedServer
	 * @param TCPConnectionToTestedServer a new list
	 */
	public void setTCPConnectionToTestedServer(List<TCPConnectionToTestedServer> TCPConnectionToTestedServer) {
		this.TCPConnectionToTestedServer = TCPConnectionToTestedServer;
	}

	/**
	 * Returns the list of available comparators
	 * @return a list
	 */
	public List<Comparator> getComparator() {
		return comparator;
	}

	/**
	 * Modifies the list of comparators
	 * @param comparator a new list
	 */
	public void setComparator(List<Comparator> comparator) {
		this.comparator = comparator;
	}

	/**
	 * 
	 */
	public void waitCMD() {
	}

	/**
	 * 
	 */
	public void readTest() {
	}

	/**
	 * 
	 */
	public void reset() {
	}

	public void addComparator(Comparator comparator) {
	}

	public void start() {
	}
}