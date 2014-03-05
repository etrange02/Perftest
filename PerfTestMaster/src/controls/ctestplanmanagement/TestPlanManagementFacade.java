/**
 * 
 */
package controls.ctestplanmanagement;

import java.util.ArrayList;
import java.util.List;

import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.IInstruction;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPlanManagementFacade implements ITestPlanManagement {

	private AbstractTestPlan testPlan;
	private List<ProtocolParser> protocolParser;
	private ProtocolParser usedProtocolParser;
	private TCPProxy TCPProxy;
	
	public TestPlanManagementFacade() {
		this.protocolParser = new ArrayList<ProtocolParser>();
	}

	/**
	 * Returns the associated test plan
	 * @return a test plan or null
	 */
	public AbstractTestPlan getTestPlan() {
		return testPlan;
	}

	/**
	 * Modifies the associated test plan
	 * @param testPlan a test plan
	 */
	public void setTestPlan(AbstractTestPlan testPlan) {
		this.testPlan = testPlan;
	}
	
	/**
	 * Returns the list of protocols parsers
	 * @return a list
	 */
	public List<ProtocolParser> getProtocolParser() {
		return this.protocolParser;
	}

	/**
	 * Modifies the list of protocols parsers
	 * @param protocolParser a list
	 */
	public void setProtocolParser(List<ProtocolParser> protocolParser) {
		this.protocolParser = protocolParser;
	}

	/**
	 * returns the current used protocol parser
	 * @return a protocol parser or null
	 */
	public ProtocolParser getUsedProtocolParser() {
		return usedProtocolParser;
	}

	/**
	 * Modifies the used protocol parser
	 * @param usedProtocolParser an existing protocol parser. Do nothing if null
	 */
	public void setUsedProtocolParser(ProtocolParser usedProtocolParser) {
		this.usedProtocolParser = usedProtocolParser;
	}

	/**
	 * Useless ?? Returns the TCPProxy implemented by the selected protocol
	 * @return a TCPProxy or null
	 */
	public TCPProxy getTCPProxy() {
		return TCPProxy;
	}

	/**
	 * Useless ?? Modifies the TCPProxy
	 * @param TCPProxy a TCPProxy
	 */
	public void setTCPProxy(TCPProxy TCPProxy) {
		this.TCPProxy = TCPProxy;
	}

	public IInstruction addNewInstruction(String testName, String instructionName) {
		return null;
	}

	public ITestPlan addNewTestPlan(String protocolName) {
		return null;
	}

	public ScalabilityTest addNewScalabilityTest(String testName) {
		return null;
	}

	public WorkloadTest addNewWorkloadTest(String testName) {
		return null;
	}

	public void addProtocolParser(ProtocolParser protocolParser) {
	}

	public void addTarget(String target) {
	}

	public boolean deployTest(String name) {
		return false;
	}

	public List<String> getAvailableProtocols() {
		return null;
	}

	public boolean openPlanTest(String path) {
		return false;
	}

	public boolean removeInstruction(String testName, String instructionName) {
		return false;
	}

	public void removeTarget(String target) {
	}
	
	public boolean removeTest(String testName) {
		return false;
	}

	public boolean savePlanTest(String planTestName) {
		return false;
	}

	public void setPort(int port) {
	}
}