/**
 * 
 */
package cslave;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.1
 */
public abstract class TCPConnectionToMaster implements Runnable {

	private TestManager testManager;
	private ServerSocket serverSocket;
	
	
	
	
	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	public TCPConnectionToMaster(int port) throws IOException {
		this.testManager = null;
		this.serverSocket = new ServerSocket(port);
	}

	
	
	
	
	
	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	/**
	 * returns the TestManager associated
	 * @return the TestManager
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * Modifies the associated testManager
	 * @param testManager a TestManager
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}

	/**
	 * returns the ServerSocket associated
	 * @return the ServerSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	
	
	
	
	
	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/
	
	@Override
	public abstract void run();
}