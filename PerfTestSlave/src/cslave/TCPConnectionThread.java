package cslave;

import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */

public class TCPConnectionThread extends Thread {
    
    private ITCPConnectionToTestedServer tcpConnectionToTestedServer;
 
    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTOR METHODS *************************************************
     * *********************************************************************/
    
    public TCPConnectionThread(ITCPConnectionToTestedServer connection) {
	super(connection);
	this.tcpConnectionToTestedServer = connection;
    }
    
    
    
    
    
    
    /* *********************************************************************
     * GETTER/SETTER METHODS ***********************************************
     * *********************************************************************/
    
    public ITCPConnectionToTestedServer getITCPConnectionToTestedServer() {
	return tcpConnectionToTestedServer;
    }
}
