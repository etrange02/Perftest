/**
 * 
 */
package cslave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cslave.interfaces.ITestParameter;
import shared.AbstractTest;
import shared.Constants;
import shared.tcp.TCPObjectServer;
import shared.tcp.TCPStringServer;

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

	try {

	    closeConnections();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Close all connections
     * @throws IOException
     */
    private void closeConnections() throws IOException {

	closeConnectionToMaster();

	if(testRunner != null) {
	    testRunner.interrupt();
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
     * @param testRunner a TestParameter
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

	String lastReceivedCommand = null;

	while(true) {

	    try {

		lastReceivedCommand = commandTCPConnectionToMaster.read();

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

		break;
	    }
	    catch(Exception e) {
		e.printStackTrace();
	    }
	    finally {

		try {

		    if(commandTCPConnectionToMaster != null) {
			commandTCPConnectionToMaster.close();
		    }
		    if(objectTCPConnectionToMaster != null) {
			objectTCPConnectionToMaster.close();
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * Stop the runnning test
     * @throws IOException
     */
    private void stopTest() throws IOException {

	try {
	    testRunner.interrupt();
	}
	catch(Exception e) {
	    commandTCPConnectionToMaster.write(
		    Constants.KO_RESP+"/\n");
	}
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

	try {
	    if(splittedCmd.length == 2) {

		String protocolName = splittedCmd[1];

		setTestComparator(protocolName);

		if(testComparator != null) {
		    testParameter.setAbstractTest(
			    (AbstractTest)objectTCPConnectionToMaster.read());
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
		commandTCPConnectionToMaster.write(
			Constants.KO_RESP+"/\n"); 
	    }
	}
	catch(Exception e) {
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

	try {
	    if(testComparator != null && splittedCMD.length == 3) {

		try {

		    testParameter.setPort(
			    Integer.parseInt(splittedCMD[1]));
		    testParameter.setIPAddress(splittedCMD[2]);


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
		catch (NumberFormatException e) {
		    commandTCPConnectionToMaster.write(
			    Constants.KO_RESP+"/\n"); 
		}
	    }
	    else {
		commandTCPConnectionToMaster.write(
			Constants.KO_RESP+"/\n");
	    }
	}
	catch(Exception e) {
	    commandTCPConnectionToMaster.write(
		    Constants.KO_RESP+"/\n");
	}
    }

    /**
     * Send test responses to the master.
     * @throws IOException
     */
    private void sendResponses() throws IOException {

	try {
	    objectTCPConnectionToMaster.write(
		    testComparator.createSendableResponsePack(
			    testParameter.getResponsePack()));
	}
	catch(Exception e) {
	    commandTCPConnectionToMaster.write(
		    Constants.KO_RESP+"/\n");
	}
    }

    /**
     * Find and set the comparator that can handle the last deployed test.
     * @param protocolName
     */
    private void setTestComparator(String protocolName) {

	Iterator<Comparator> iter = this.comparator.iterator();

	while (iter.hasNext()) {

	    Comparator comparatorTmp = iter.next();

	    if (comparatorTmp.isConcernedComparator(protocolName)) {
		testComparator =  comparatorTmp;
	    }
	}

	testComparator = null;
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