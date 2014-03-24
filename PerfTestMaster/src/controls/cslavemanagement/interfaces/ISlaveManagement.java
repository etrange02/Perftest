/**
 * 
 */
package controls.cslavemanagement.interfaces;

import gui.interfaces.SlaveListenable;

import java.util.List;

import shared.DataBuffer;
import controls.cslavemanagement.Slave;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ISlaveManagement extends SlaveListenable {

	/**
	 * Detects slaves applications on network defined by ipAddress
	 * @param ipAddress the network address
	 */
	public void detectSlaves(String ipAddress);

	/**
	 * Adds a slave defined by ipAddress
	 * @param ipAddress the ip address
	 * @return true if slave found, false otherwise
	 */
	public boolean addSlave(String ipAddress);

	/**
	 * Deploys a test on slaves applications
	 * @param test a test to deploy
	 * @param protocolName the protocol name
	 * @return true on success, false otherwise
	 */
	public boolean deployTest(
			AbstractMonitoredTest test, 
			String protocolName);

	/**
	 * Returns the number of slaves applications the master is dealing with
	 * @return the number
	 */
	public int count();

	/**
	 * Runs another slave. Useful in deployed Workload test
	 * @param address address of the tested server
	 * @param port port of the tested server
	 * @return true on success
	 */
	public boolean runAnotherSlave(String address, int port);

	/**
	 * Runs count slaves. Useful in deployed Scalability test
	 * @param count number of slaves that will run
	 * @param addresses address of the tested server
	 * @param port port of the tested server
	 * @return true on success, false otherwise
	 */
	public boolean runSlaves(int count, List<String> addresses, int port);

	/**
	 * Makes all slaves in running mode stops
	 * @return true on success, false otherwise
	 */
	public boolean stop();

	/**
	 * Removes the slave named name from the list 
	 * @param name a slave name
	 * @return true in success, false otherwise
	 */
	public boolean removeSlave(String name);

	/**
	 * Indicates whether a slave is ready to run the deployed test. Used with workload tests
	 * @return true if one slave is ready, false otherwise
	 */
	public boolean hasAnotherReadySlave();

	/**
	 * Returns a list of DataBuffer managed by the slave manager.
	 * @return a list
	 */
	public List<DataBuffer> getLastReceivedData();

	/**
	 * Returns the current associated TestPlanManagement
	 * @return the TestPlanManagement
	 */
	public ITestPlanManagement getTestPlanManagement();
	
	/**
	 * Modifies the associated TestPlanManagement
	 * @param testPlanManagement a TestPlanManagement
	 */
	public void setTestPlanManagement(
		ITestPlanManagement testPlanManagement);

	/**
	 * Returns the list of slaves
	 * @return a list
	 */
	public List<Slave> getSlave();
	
	/**
	 * Returns the name of the deployed test
	 * @return a name or an empty string if no test has been deployed
	 */
	public String getDeployedTestName();
	
	/**
	 * Informs all tests that are Scalability kind that the maximum available count
	 * of slaves changed
	 */
	public void updateMonitoringPanelWithMaxSlaveCount();

	/**
	 * Runs slaves for a scalability test
	 */
	public void runSlave();
	
	/**
	 * Runs another slave for a workload test
	 */
	public void runAnotherSlave();
	
	/**
	 * Update all slave listeners
	 */
	public void updateAllSlaveListeners();
}