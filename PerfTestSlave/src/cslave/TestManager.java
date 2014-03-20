/**
 * 
 */
package cslave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.Constants;
import shared.SendableTest;
import shared.Utils;
import shared.interfaces.IInstruction;
import shared.tcp.TCPObjectServer;
import shared.tcp.TCPStringServer;
import cslave.interfaces.ITestParameter;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManager extends Thread {

	private List<Comparator> comparator;
	private TCPStringServer commandTCPConnectionToMaster;
	private TCPObjectServer objectTCPConnectionToMaster;
	private ITestParameter testParameter;
	private Comparator testComparator;
	private Thread testRunner;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEANER ************************************************
	 * *********************************************************************/

	public TestManager() throws IOException {


		this.comparator = new ArrayList<Comparator>();

		commandTCPConnectionToMaster =  new TCPStringServer();
		objectTCPConnectionToMaster = new TCPObjectServer();
		testParameter = null;
		testRunner = null;


		init();
	}

	/**
	 * Initialize server aspects of this TestManager.
	 * @throws IOException
	 */
	private void init() throws IOException {

		commandTCPConnectionToMaster.startStringConnection(
				Constants.SOCKET_COMMAND_PORT);

		objectTCPConnectionToMaster.startObjectConnection(
				Constants.SOCKET_OBJECT_PORT);
	}

	@Override
	public void interrupt() {

		super.interrupt();
		closeConnections();
	}

	/**
	 * Close all connections
	 * @throws IOException
	 */
	private void closeConnections() {

		try {
			
			closeConnectionToMaster();

			if(testRunner != null) {
				testRunner.interrupt();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close connections to master.
	 * @throws IOException
	 */
	private void closeConnectionToMaster() throws IOException {

		if(commandTCPConnectionToMaster != null) {
			commandTCPConnectionToMaster.close();
		}
		if(objectTCPConnectionToMaster != null) {
			objectTCPConnectionToMaster.close();
		}
	}






	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	/**
	 * Returns the TestParameter associated
	 * @return the TestParameter
	 */
	public ITestParameter getTestParameter() {
		return testParameter;
	}

	/**
	 * Modifies the associated TestParameter
	 * @param testParameter a TestParameter
	 */
	public void setTestParameter(ITestParameter testParameter) {
		this.testParameter = testParameter;
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







	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/

	@Override
	public void start() {

		System.out.println("TestManager.start(): started");

		String lastReceivedCommand = null;



		try {

			while(true) {

				try {

					System.out.println("TestManager.start(): wait a cmd");
					lastReceivedCommand = commandTCPConnectionToMaster.read();
					System.out.println("TestManager.start(), lastReceived="+lastReceivedCommand);

					if(lastReceivedCommand == null) {
						closeConnections();
						init();
					}
					else if(lastReceivedCommand.startsWith(Constants.RUN_CMD)) {

						runTest(lastReceivedCommand);
					}
					else if(lastReceivedCommand.startsWith(Constants.STOP_CMD)) {

						stopTest();
					}
					else if(lastReceivedCommand.startsWith(Constants.RESET_CMD)) {

						resetTest(); 
					}
					else if(lastReceivedCommand.startsWith(Constants.DEPLOY_CMD)) {

						deployTest(lastReceivedCommand);
						System.out.println("TestManager.start(): test deployed");
					}
					else if(lastReceivedCommand.startsWith(Constants.RESULT_CMD)) {

						sendResponses();
					} 
					else {
						commandTCPConnectionToMaster.write(
								Constants.KO_RESP+"/\n");
					}
				}
				catch(IOException e) {
					
					closeConnections();
					init();
				}

			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnections();
		}
	}

	/**
	 * Stop the runnning test
	 * @throws IOException
	 */
	private void stopTest() throws IOException {

		testRunner.interrupt();
	}

	/**
	 * Drop all informations about last test.
	 * @throws IOException
	 */
	private void resetTest() throws IOException {

		this.testParameter = null;

		commandTCPConnectionToMaster.write(
				Constants.OK_RESP+"/\n");
	}

	/**
	 * Prepare in order to run a new test.
	 * @param cmd
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void deployTest(String cmd) 
			throws ClassNotFoundException, IOException {

		String[]splittedCmd = cmd.split("/");

		System.out.println("TestManager.deployTest(): "+cmd);

		if(splittedCmd.length == 2) {

			System.out.println("TestManager.deployTest(): size=2");
			String protocolName = splittedCmd[1];

			setTestComparator(protocolName);

			System.out.println("TestManager.deployTest(): gonna set TestComparator");

			if(testComparator != null) {

				System.out.println("TestManager.deployTest():  done for TestComparator");

				commandTCPConnectionToMaster.write(
						Constants.OK_RESP+"/\n"); 

				System.out.println("TestManager.deployTest(): going to read test");
				Object object = objectTCPConnectionToMaster.read();


				testParameter = new TestParameter();

				testParameter.setTest(
						(SendableTest)object);
				System.out.println("TestManager.deployTest(): after read test");

				int i = 1;
				for(IInstruction inst : ((SendableTest)object).getInstructions()) {

					System.out.println(
							"TestManager.deployTest(): inst_"+i+"_request_"+
									Utils.displayBinaryArray(
											inst.getBinaryRequest()));

					System.out.println(
							"TestManager.deployTest(): inst_"+i+"_response_"+
									Utils.displayBinaryArray(
											inst.getBinaryResponse()));
				}

				testParameter.setProtocolName(protocolName);
				testParameter.setTcpConnectionClazz(
						testComparator.getTcpConnectionClazz());

				commandTCPConnectionToMaster.write(
						Constants.OK_RESP+"/\n"); 
			}
			else {
				commandTCPConnectionToMaster.write(
						Constants.KO_RESP+"/\n"); 
			}
		}
		else {
			System.out.println("TestManager.deployTest(): wrong command size");
			commandTCPConnectionToMaster.write(
					Constants.KO_RESP+"/\n"); 
		}
	}

	/**
	 * Run the test.
	 * @param cmd
	 * @throws IOException
	 */
	private void runTest(String cmd) throws IOException {

		String[] splittedCMD = cmd.split("/");

		System.out.println("TestManager.runTest(): "+cmd);

		if(testComparator != null && splittedCMD.length == 3) {


			testParameter.setIPAddress(splittedCMD[1]);
			testParameter.setPort(
					Integer.parseInt(splittedCMD[2]));

			testRunner = new Thread(testParameter) {

				@Override
				public void interrupt() {

					super.interrupt();
					testParameter.stop();
				}
			};
			testRunner.start();

			commandTCPConnectionToMaster.write(
					Constants.OK_RESP+"/\n");
		}
		else {
			commandTCPConnectionToMaster.write(
					Constants.KO_RESP+"/\n");
		}
	}

	/**
	 * Send test responses to the master.
	 * @throws IOException
	 */
	private void sendResponses() throws IOException {

		objectTCPConnectionToMaster.write(
				testComparator.createSendableResponsePack(
						testParameter.getResponsePack()));
	}

	/**
	 * Find and set the comparator that can handle the last deployed test.
	 * @param protocolName
	 */
	private void setTestComparator(String protocolName) {

		Iterator<Comparator> iter = this.comparator.iterator();

		testComparator = null;

		while (iter.hasNext()) {

			Comparator comparatorTmp = iter.next();

			System.out.println("TestManager.setTestComparator(): protocol="+comparatorTmp.getProtocolName());

			if (comparatorTmp.isConcernedComparator(protocolName)) {
				System.out.println("TestManager.setTestComparator(): MATCH!");
				testComparator =  comparatorTmp;
			}
		}
	}

	/**
	 * Adds a comparator to the testManager
	 * @param comparator a protocol comparator
	 */
	public void addComparator(Comparator comparator) {
		Iterator<Comparator> iter = this.comparator.iterator();
		while (iter.hasNext()) {
			if (comparator.isConcernedComparator(iter.next().getProtocolName())) {
				return;
			}
		}
		this.comparator.add(comparator);
	}
}