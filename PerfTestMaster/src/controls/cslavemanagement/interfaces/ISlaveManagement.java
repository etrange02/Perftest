/**
 * 
 */
package controls.cslavemanagement.interfaces;

import java.util.List;

import controls.cslavemanagement.DataBuffer;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ISlaveManagement {

	/**
	 * Detects slaves applications on network defined by ipAddress
	 * @param ipAddress the network address
	 * @param port the port where slaves are listening
	 */
	public void detectSlaves(String ipAddress);

	/**
	 * Adds a slave defined by ipAddress
	 * @param ipAddress the ip address
	 * @param port the listening port
	 * @return true if slave found, false otherwise
	 */
	public boolean addSlave(String ipAddress);

	/**
	 * Deploys a test on every slaves applications
	 * @param test a test to deploy
	 * @return true on success, false otherwise
	 */
	public boolean sendTest(AbstractTest test);

	/**
	 * Returns the number of slaves applications the master is dealing with
	 * @return the number
	 */
	public int count();

	/**
	 * Runs another slave. Useful in Workload test
	 * @param address of the tested server
	 * @param port of the tested server
	 * @return true on success
	 */
	public boolean runAnotherSlave(String address, int port);

	/**
	 * Runs count slaves. Useful in Scalability test
	 * @param count number of slaves that will run
	 * @param address address of the tested server
	 * @param port port of the tested server
	 * @return true on success, false otherwise
	 */
	public boolean runSlaves(int count, String address, int port);

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
	 * Indicates whether a slave is ready to run the deployed test
	 * @return true if one slave is ready, false otherwise
	 */
	public boolean hasAnotherReadySlave();

	/**
	 * Returns a list of DataBuffer managed by the slave manager.
	 * @return a list
	 */
	public List<DataBuffer> getDataBuffer();

	/**
	 * Returns the current associated TestPlanManagement
	 * @return the TestPlanManagement
	 */
	public ITestPlanManagement getTestPlanManagement();
	
	/**
	 * Modifies the associated TestPlanManagement
	 * @param testPlanManagement a TestPlanManagement
	 */
	public void setTestPlanManagement(ITestPlanManagement testPlanManagement);
}