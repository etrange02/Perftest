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

    /**
     * Handle the connection part of the initialization
     * @param hostname the targeted-server hostname
     * @param port the targeted-server port
     */
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

    /**
     * @return The socket resulting from the connection to the targeted server.
     */
    protected Socket getClientSocket() {
	return clientSocket;
    }
}