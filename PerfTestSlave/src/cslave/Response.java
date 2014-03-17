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

    private long sendTimeMillis;
    private long receptionTimeMillis;
    private byte[] expectedBinaryResponse;
    private byte[] serverBinaryResponse;

    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/
    
    public Response() {
	this.serverBinaryResponse = new byte[0];
    }

    
    
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    public void setSendTimeMillis(long sendTimeMillis) {
	this.sendTimeMillis = sendTimeMillis;
    }
    
    public long getSendTimeMillis() {
	return sendTimeMillis;
    }
    
    public void setReceptionTimeMillis(long receptionTimeMillis) {
	this.receptionTimeMillis = receptionTimeMillis;
    }
    
    public long getReceptionTimeMillis() {
	return receptionTimeMillis;
    }

    /**
     * Modifies the response given by the server
     * @param serverBinaryResponse the response
     */
    public void setServerBinaryResponse(byte[] serverBinaryResponse) {
	this.serverBinaryResponse = serverBinaryResponse;
    }
    
    public byte[] getServerBinaryResponse() {
	return serverBinaryResponse;
    }

    /**
     * 
     * @param expectedBinaryResponse
     */
    public void setExpectedBinaryResponse(byte[] expectedBinaryResponse) {
	this.expectedBinaryResponse = expectedBinaryResponse;
    }
    
    public byte[] getExpectedBinaryResponse() {
	return expectedBinaryResponse;
    }
}