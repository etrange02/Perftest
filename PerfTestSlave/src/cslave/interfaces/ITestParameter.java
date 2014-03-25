/**
 * 
 */
package cslave.interfaces;

import java.util.List;

import shared.SendableTest;

/**
 * 
 * @author Jean-Luc Amitousa Mankoy jean-luc.amitousa-mankoy@hotmail.fr
 * @version 1.1
 * 
 */
public interface ITestParameter extends Runnable {


    /* *********************************************************************
     * CLEANS METHODS ******************************************************
     * *********************************************************************/
    
    /**
     * Stop to run the test. Must be called by the current thread if it get
     * interrupted.
     */
    public void stop();
    
    

    /* *********************************************************************
     * GETTER/SETTER METHODS ***********************************************
     * *********************************************************************/

    /**
     * Modifies the port to send tests
     * @param port the port to send tests
     */
    public void setPort(int port);

    /**
     * Modifies the address of the server that the application has to test
     * @param IPAddress an address
     */
    public void setIPAddress(String IPAddress);

    /**
     * Modifies the protocol name which is used inside tests
     * @param protocolName
     */
    public void setProtocolName(String protocolName);

    /**
     * Set the test to run
     * @param abstractTest
     */
    public void setTest(SendableTest test);

    /**
     * Set the clazz that going to give use instances. Thoses intances
     * know how communicate with the targeted server.
     * @param tcpConnectionClazz
     */
    public void setTcpConnectionClazz(
	    Class<? extends ITCPConnectionToTestedServer> tcpConnectionClazz);


    /* *********************************************************************
     * IMPORTANTS METHODS **************************************************
     * *********************************************************************/

    /**
     * 
     * @return the list of last target server responses.
     */
    List<IResponse> getNextResponses();
}