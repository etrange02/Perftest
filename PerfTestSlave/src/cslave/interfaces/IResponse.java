package cslave.interfaces;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public interface IResponse {

	/**
	 * Returns the time between instruction sent and response received
	 * @return the time
	 */
	public int getDelay();
	
	/**
	 * Returns the response expected by the server
	 * @return the expected binary response
	 */
	public byte[] getExpectedBinaryResponse();
	
	/**
	 * Returns the response given by the server
	 * @return the binary response
	 */
	public byte[] getServerBinaryResponse();
}