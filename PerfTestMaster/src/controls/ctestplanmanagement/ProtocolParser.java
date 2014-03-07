/**
 * 
 */
package controls.ctestplanmanagement;

import shared.AbstractInstruction;

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
	 * Useless ?? Returns a new instance of Test which is specified to the implemented
	 * protocol
	 * @return a new test
	 */
	public abstract AbstractMonitoredTest createNewTest();

	/**
	 * Returns a new instance of plan test which is specified to the implemented
	 * protocol
	 * @return a new plan test
	 */
	public abstract AbstractTestPlan createNewPlanTest();

	/**
	 * Returns a new instance of TCPProxy which is specified to the implemented
	 * protocol. It is used to run a white test
	 * @return a new TCPProxy
	 */
	public abstract TCPProxy createNewTCPProxy();

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
	
	public abstract int getDefaultProtocolPort();
}