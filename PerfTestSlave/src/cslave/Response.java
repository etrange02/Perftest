/**
 * 
 */
package cslave;

import cslave.interfaces.IResponse;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Response implements IResponse {

    private int delay;
    private byte[] binaryRequest;
    private byte[] expectedBinaryResponse;
    private byte[] serverBinaryResponse;

    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/
    
    public Response() {
	this.delay = 0;
	this.serverBinaryResponse = new byte[0];
    }

    
    
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    public int getDelay() {
	return delay;
    }

    /**
     * Modifies the time of response received. Should be positive.
     * If negative, we consider that the wanted delay is 0.
     * @param delay the delay
     */
    public void setDelay(int delay) {

	this.delay = delay < 0 ? 0 : delay;
    }

    public byte[] getServerBinaryResponse() {
	return serverBinaryResponse;
    }

    /**
     * Modifies the response given by the server
     * @param serverBinaryResponse the response
     */
    public void setServerBinaryResponse(byte[] serverBinaryResponse) {
	this.serverBinaryResponse = serverBinaryResponse;
    }

    public byte[] getBinaryRequest() {
	return binaryRequest;
    }

    /**
     * @param binaryRequest the request that was given to the server
     */
    public void setBinaryRequest(byte[] binaryRequest) {
	this.binaryRequest = binaryRequest;
    }

    public byte[] getExpectedBinaryResponse() {
	return expectedBinaryResponse;
    }

    /**
     * 
     * @param expectedBinaryResponse
     */
    public void setExpectedBinaryResponse(byte[] expectedBinaryResponse) {
	this.expectedBinaryResponse = expectedBinaryResponse;
    }
}