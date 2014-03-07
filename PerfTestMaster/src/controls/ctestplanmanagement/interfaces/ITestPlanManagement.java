/**
 * 
 */
package controls.ctestplanmanagement.interfaces;

import java.util.List;

import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.ProtocolParser;
import controls.ctestplanmanagement.ScalabilityTest;
import controls.ctestplanmanagement.WorkloadTest;
import shared.IInstruction;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ITestPlanManagement {
	
	/**
	 * Creates and adds an instruction to the test named testName
	 * @param testName a test name
	 * @param instructionName an instruction name
	 * @return an instruction. null is returned if the test does not exist
	 */
	public IInstruction addNewInstruction(String testName,
			String instructionName);

	/**
	 * Creates and adds a test plan corresponding to the given protocol. 
	 * @param protocolName
	 * @return a test plan. If the protocol does not exist or a test plan already
	 *  exist, null is returned
	 */
	public ITestPlan addNewTestPlan(String protocolName);

	/**
	 * Creates and adds a Scalability test
	 * @param testName a test name
	 * @return a ScalabilityTest. Null if the name is taken
	 */
	public ScalabilityTest addNewScalabilityTest(String testName);

	/**
	 * Creates and adds a Workload test
	 * @param testName a test name
	 * @return a WorkloadTest. Null if the name is taken
	 */
	public WorkloadTest addNewWorkloadTest(String testName);

	/**
	 * Adds a new ProtocolParser which extends recognised protocols. Add not done
	 * if the protocol name is already in the list.
	 * @param protocolParser a protocol parser
	 */
	public void addProtocolParser(ProtocolParser protocolParser);

	/**
	 * Adds a new server target
	 * @param target a server address
	 */
	public void addTarget(String target);

	/**
	 * Deploys a test into slave application
	 * @param name a test name
	 * @return true on success, false otherwise
	 */
	public boolean deployTest(String name);

	/**
	 * Returns a list of implemented and recognised protocols
	 * @return a list of names
	 */
	public List<String> getAvailableProtocols();

	/**
	 * Opens a plan test saved in a file
	 * @param path a file name
	 * @return true on success, false otherwise
	 */
	public boolean openPlanTest(String path);

	/**
	 * Removes the instructionPosition instruction from the testName
	 * @param testName a test name
	 * @param instructionName an instruction name
	 * @return true on success, false otherwise
	 */
	public boolean removeInstruction(String testName, int instructionPosition);

	/**
	 * Removes a specified server address from the target list
	 * @param target an address
	 */
	public void removeTarget(String target);

	/**
	 * Removes the test named testName from the plan test
	 * @param testName a test name
	 * @return true on success, false otherwise
	 */
	public boolean removeTest(String testName);

	/**
	 * Creates a new file named planTestName in which the plan test will be saved
	 * @param planTestName a planTest name
	 * @return true on success, false otherwise
	 */
	public boolean savePlanTest(String planTestName);

	/**
	 * Modifies the port of the tested protocol
	 * @param port the protocol port
	 */
	public void setPort(int port);
		
	/**
	 * Modifies the associated SlaveManagement
	 * @param slaveManagement a SlaveManagement
	 */
	public void setSlaveManagement(ISlaveManagement slaveManagement);
	
	/**
	 * Returns the current SlaveManagement
	 * @return the associated SlaveManagement
	 */
	public ISlaveManagement getSlaveManagement();
}