/**
 * 
 */
package cslave.interfaces;

import java.io.IOException;

import shared.SendableTest;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public interface ITCPConnectionToTestedServer extends Runnable {

    /**
     * Init a connection to a targeted server and prepare for running a test.
     * @param hostname targeted-server hostname
     * @param port targeted-server port
     * @param test the instruction provider
     * @param scenario the responses storer
     * @throws IOException
     */
    public void init(
    		String hostname, 
    		int port, 
    		SendableTest test, 
    		IScenario scenario)
	    throws IOException;
    
    /**
     * Try to give work to this client
     * @return "true" if the work has been give, "false" otherwise. "false" 
     * means that the client is already working.
     */
    public boolean tryGiveWork();
}