/**
 * 
 */
package cslave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.Constants;
import cslave.interfaces.ITestManager;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestManager extends Thread implements ITestManager {

    private List<Comparator> comparator;
    private CommandTCPConnectionToMaster commandTCPConnectionToMaster;
    private ObjectTCPConnectionToMaster  objectTCPConnectionToMaster;
    private TestParameter testParameter;
    private Thread testRunner;





    
    /* *********************************************************************
     * CONSTRUCTORS/CLEANER ************************************************
     * *********************************************************************/

    public TestManager() throws IOException {


	this.comparator = new ArrayList<Comparator>();

	commandTCPConnectionToMaster = null;
	objectTCPConnectionToMaster = null;
	testParameter = null;
	testRunner = null;


	init();
    }

    public void init() throws IOException {

	if(commandTCPConnectionToMaster != null &&
		commandTCPConnectionToMaster.isClosed() == false) {
	    commandTCPConnectionToMaster.close();
	}
	if(objectTCPConnectionToMaster != null &&
		objectTCPConnectionToMaster.isClosed() == false) {
	    objectTCPConnectionToMaster.close();
	}

	commandTCPConnectionToMaster =  
		new CommandTCPConnectionToMaster();

	objectTCPConnectionToMaster = 
		new ObjectTCPConnectionToMaster();

	resetTest();
    }

    @Override
    public void interrupt() {

	super.interrupt();

	if(commandTCPConnectionToMaster != null &&
		commandTCPConnectionToMaster.isClosed() == false) {
	    commandTCPConnectionToMaster.close();
	}
	if(objectTCPConnectionToMaster != null &&
		objectTCPConnectionToMaster.isClosed() == false) {
	    objectTCPConnectionToMaster.close();
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
     * @return the tcp connection used for receive/send commands
     */
    public CommandTCPConnectionToMaster getCommandTCPConnectionToMaster() {
	return commandTCPConnectionToMaster;
    }

    /**
     * Set the tcp connection used for receive/send commands
     * @param commandTCPConnectionToMaster
     */
    public void setCommandTCPConnectionToMaster(
	    CommandTCPConnectionToMaster commandTCPConnectionToMaster) {
	this.commandTCPConnectionToMaster = commandTCPConnectionToMaster;
    }

    /**
     * @return the tcp connection used for receive/send objects
     */
    public ObjectTCPConnectionToMaster getObjectTCPConnectionToMaster() {
	return objectTCPConnectionToMaster;
    }

    /**
     * Set the tcp connection used for receive/send objects
     * @param commandTCPConnectionToMaster
     */
    public void setObjectTCPConnectionToMaster(
	    ObjectTCPConnectionToMaster objectTCPConnectionToMaster) {
	this.objectTCPConnectionToMaster = objectTCPConnectionToMaster;
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

		if(isInterrupted()) {
		    //finish the execution
		    break;
		}
		else {

		    try {
			if(commandTCPConnectionToMaster.isClosed()) {
			    init();
			}
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }
		}
	    }
	    catch(Exception e) {
		e.printStackTrace();
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
	    Comparator comparator = getComparator(protocolName);

	    if(comparator != null) {
		testParameter.setAbstractTest(objectTCPConnectionToMaster.read());
		testParameter.setProtocolName(protocolName);
		testParameter.setTcpConnectionClazz(
			comparator.getTcpConnectionClazz());

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
	Comparator matchingComparator = 
		getComparator(testParameter.getProtocolName());

	if(matchingComparator != null && splittedCMD.length == 4) {

	    try {

		testParameter.setPort(
			Integer.parseInt(splittedCMD[1]));
		testParameter.setIPAddress(splittedCMD[2]);
		testParameter.setDelay(Integer.parseInt(splittedCMD[3]));


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
	objectTCPConnectionToMaster.write(testParameter.getResponsePack());
    }
    
    private Comparator getComparator(String protocolName) {
	Iterator<Comparator> iter = this.comparator.iterator();
	while (iter.hasNext()) {

	    Comparator comparatorTmp = iter.next();

	    if (comparatorTmp.isConcernedComparator(protocolName)) {
		return comparatorTmp;
	    }
	}

	return null;
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