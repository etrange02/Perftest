/**
 * 
 */
package controls.ctestplanmanagement;

import gui.panels.AbstractTestPlanPanel;

import java.io.IOException;
import java.util.List;

import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.AbstractClientForBlankTest;
import shared.AbstractInstruction;
import shared.interfaces.IInstruction;
import shared.interfaces.ITest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class ProtocolParser {

	/**
	 * Indicates whether the implemented protocol is the given protocol name
	 * @param protocolName a protocol name
	 * @return true on success, false otherwise
	 */
	public boolean isImplementedProtocol(String protocolName) {
		return this.getProtocolName().equals(protocolName);
	}

	/**
	 * Returns the implemented protocol name
	 * @return a protocol name
	 */
	public abstract String getProtocolName();

	/**
	 * Returns a new instance of Instruction which is specified to the implemented
	 * protocol
	 * @return a new instruction
	 */
	public abstract AbstractInstruction createNewInstruction();

	/**
	 * Returns a new instance of plan test which is specified to the implemented
	 * protocol
	 * @return a new plan test
	 */
	public abstract AbstractTestPlan createNewPlanTest();

	/**
	 * Returns a new instance of TCPProxy which is specified to the implemented
	 * protocol. It is used to run a white test
	 * @param hostname the hostname where find the tested server.
	 * @param port the port to use to discuss with the tested server.
	 * @param instructions the list of isntructions to complete.
	 * @return an adapted proxy
	 */
	public abstract TCPProxy createNewTCPProxy(
			String hostname, int port,
			List<IInstruction>instructions) throws IOException;
	
	/**
	 * Create a client that going to run the test for blanck tests.
	 * @param testPlan the testPlan associated with the test
	 * @param hostname the targeted-server hostname
	 * @param test
	 * @return the created client
	 */
	public abstract AbstractClientForBlankTest createNewClientForBlankTest(
			ITestPlan testPlan, String hostname, ITest test);

	/**
	 * Returns a TestPlan which has been decoded from a JSON object and which 
	 * implements the implemented protocol
	 * @param values a JSON object
	 * @return a TestPlan on success, null otherwise
	 */
	public abstract AbstractTestPlan readJSONFileTestPlan(Object values);

	/**
	 * Returns a test which has been decoded from a JSON object
	 * @param values a JSON object
	 * @return a test on success, null otherwise
	 */
	public AbstractMonitoredTest readJSONStringTest(Object values) {
		return null;
	}

	/**
	 * Returns an instruction which has been decoded from a JSON object and which
	 * implements the implemented protocol
	 * @param values a JSON Object
	 * @return an Instruction on success, null otherwise
	 */
	public abstract AbstractInstruction readJSONStringInstruction(Object values);

	/**
	 * Returns the default port associated to the implemented protocol
	 * @return a port
	 */
	public abstract int getDefaultProtocolPort();
	
	/**
	 * GUI - Returns a new panel which draw specific data of the test plan
	 * @param testPlanManagement an ITestPlanManagement
	 * @return a panel
	 */
	public abstract AbstractTestPlanPanel createNewTestPlanPanel(ITestPlanManagement testPlanManagement);
}