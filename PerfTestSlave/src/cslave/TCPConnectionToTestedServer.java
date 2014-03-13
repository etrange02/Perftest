/**
 * 
 */
package cslave;

import java.net.InetAddress;
import java.net.Socket;

import shared.ITest;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class TCPConnectionToTestedServer 
implements ITCPConnectionToTestedServer {

    private ITest test;
    private IScenario scenario;
    private Socket clientSocket;






    /* *********************************************************************
     * CONSTRUCTORS/CLEAN **************************************************
     * *********************************************************************/

    public 
    TCPConnectionToTestedServer() {

	test = null;
	scenario = null;
    }

    public 
    void init(String hostname, int port, ITest test, IScenario scenario) {

	try {

	    clientSocket = 
		    new Socket(
			    InetAddress.getByName(hostname),
			    port);
	    
	    this.test = test;
	    this.scenario = scenario;
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }






    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    protected Socket getClientSocket() {
	return clientSocket;
    }
    
    protected ITest getTest() {
	return test;
    }
    
    protected IScenario getScenario() {
	return scenario;
    }
}