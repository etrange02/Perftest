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
import shared.tcp.TCPObjectServer;
import shared.tcp.TCPStringServer;
import cslave.interfaces.ITestManager;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManager extends Thread implements ITestManager {

    private List<Comparator> comparator;
    private TCPStringServer commandTCPConnectionToMaster;
    private TCPObjectServer objectTCPConnectionToMaster;
    private TestParameter testParameter;
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

    public void init() throws IOException {

	commandTCPConnectionToMaster.close();
	commandTCPConnectionToMaster.startStringConnection(
		Constants.SOCKET_COMMAND_PORT);

	objectTCPConnectionToMaster.close();
	objectTCPConnectionToMaster.startObjectConnection(
		Constants.SOCKET_OBJECT_PORT);

	resetTest();
    }

    @Override
    public void interrupt() {

	super.interrupt();

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

	testRunner.interrupt();
    }






    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    /**
     * Returns the TestParameter associated
     * @return the TestParameter
     */
    public TestParameter getTestParameter() {
	return testParameter;
    }

    /**
     * Modifies the associated TestParameter
     * @param testRunner a TestParameter
     */
    public void setTestParameter(TestParameter testParameter) {
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
		    continue;
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

		    System.out.println("TestManager.start.finally: Clean");
		    
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

    private void stopTest() throws IOException {

	testRunner.interrupt();
    }

    private void resetTest() throws IOException {

	this.testParameter = null;

	commandTCPConnectionToMaster.write(
		Constants.OK_RESP+"/\n");
    }

    private void deployTest(String cmd) 
	    throws ClassNotFoundException, IOException {
	String[]splittedCmd = cmd.split("/");

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

    private void runTest(String cmd) throws IOException {

	String[] splittedCMD = cmd.split("/");

	if(testComparator != null && splittedCMD.length == 3) {

	    try {

		testParameter.setPort(
			Integer.parseInt(splittedCMD[1]));
		testParameter.setIPAddress(splittedCMD[2]);


		testRunner = new Thread(testParameter);
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

    private void sendResponses() throws IOException {

	objectTCPConnectionToMaster.write(
		testComparator.createSendableResponsePack(
			testParameter.getResponsePack()));
    }

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