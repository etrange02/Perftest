/**
 * 
 */
package cslave;

import java.net.InetAddress;
import java.net.Socket;

import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class TCPConnectionToTestedServer 
implements ITCPConnectionToTestedServer {

    private Socket clientSocket;
    





    /* *********************************************************************
     * CONSTRUCTORS/CLEAN **************************************************
     * *********************************************************************/

    public 
    TCPConnectionToTestedServer() {

	clientSocket = null;
    }

    public 
    void init(String hostname, int port) {

	try {

	    clientSocket = 
		    new Socket(
			    InetAddress.getByName(hostname),
			    port);
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
}