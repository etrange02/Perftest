/**
 * 
 */
package cslave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.AbstractTest;
import shared.Constants;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITestManager;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManager implements ITestManager {

	//datas about TCPConnectionToMaster array
	private static final int NB_TCP_CONNECTIONS_TO_MASTER= 2;
	private static final int CMD_TCP_CONNECTION_ID= 0;
	private static final int OBJECTS_TCP_CONNECTION_ID= 1;
	private TCPConnectionToMaster[] TCPConnectionToMaster;

	private TestParameter testRunner;
	private List<IScenario> scenario;
	private AbstractTest abstractTest;
	private List<TCPConnectionToTestedServer> TCPConnectionToTestedServer;
	private List<Comparator> comparator;

	private String lastReceivedCommand;
	private String nextCommandToSend;






	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	public TestManager() throws IOException {

		this.scenario = new ArrayList<>();
		this.TCPConnectionToTestedServer = new ArrayList<>();
		this.comparator = new ArrayList<>();
		this.TCPConnectionToMaster = 
				new TCPConnectionToMaster[NB_TCP_CONNECTIONS_TO_MASTER];


		TCPConnectionToMaster[CMD_TCP_CONNECTION_ID] = 
				new CommandTCPConnectionToMaster(Constants.SOCKET_COMMAND_PORT);
		TCPConnectionToMaster[CMD_TCP_CONNECTION_ID].setTestManager(this);


		TCPConnectionToMaster[OBJECTS_TCP_CONNECTION_ID] = 
				new ObjectTCPConnectionToMaster(Constants.SOCKET_OBJECT_PORT);
		TCPConnectionToMaster[OBJECTS_TCP_CONNECTION_ID].setTestManager(this);
	}






	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

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
	 * Update the last received command
	 */
	public void setLastReceivedCommand(String lastReceivedCommand) {
		this.lastReceivedCommand = lastReceivedCommand;
	}

	public void addComparator(Comparator comparator) {
		Iterator<Comparator> iter = this.comparator.iterator();
		while (iter.hasNext()) {
			if (comparator.isConcernedComparator(iter.next().getProtocolName())) {
				return;
			}
		}
		this.comparator.add(comparator);
	}

	public String getNextCommandToSend() {
		return nextCommandToSend;
	}





	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/

	/**
	 * @throws InterruptedException 
	 * 
	 */
	private void waitCMD() throws InterruptedException {
		TCPConnectionToMaster[CMD_TCP_CONNECTION_ID].notify();
		wait();
	}

	private void sendResponse(String response) {
		this.nextCommandToSend = response;
		TCPConnectionToMaster[CMD_TCP_CONNECTION_ID].notify();
	}

	/**
	 * @throws InterruptedException 
	 * 
	 */
	private void readTest() throws InterruptedException {
		TCPConnectionToMaster[OBJECTS_TCP_CONNECTION_ID].notify();
		wait();
	}

	/**
	 * 
	 */
	public void reset() {
	}

	public void start() {

		while(true) {

			try {

				waitCMD();

				if(lastReceivedCommand.startsWith(Constants.RUN_CMD)) {

				}
				else if(lastReceivedCommand.startsWith(Constants.STOP_CMD)) {

				}
				else if(lastReceivedCommand.startsWith(Constants.RESET_CMD)) {

				}
				else if(lastReceivedCommand.startsWith(Constants.DEPLOY_CMD)) {

					Boolean correctParam = false;
					String protocolName = lastReceivedCommand.split("/")[1];
					testRunner = new TestParameter();


					if(protocolName.equals(Constants.LDAP)) {
						correctParam = true;
					}

					if(correctParam) {
					
						readTest();
						testRunner.setProtocolName(protocolName);
					}
				}
				else if(lastReceivedCommand.startsWith(Constants.RESULT_CMD)) {

				} 
				else {
					sendResponse(Constants.KO_RESP+"/\n");
				}


			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}