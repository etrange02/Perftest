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
	private byte[] serverBinaryResponse;
	
	public Response() {
		this.delay = 0;
		this.serverBinaryResponse = new byte[0];
	}

	public int getDelay() {
		return delay;
	}

	/**
	 * Modifies the time of response received
	 * @param delay the delay
	 */
	public void setDelay(int delay) {
		this.delay = delay;
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
}