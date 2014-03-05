package cslave.interfaces;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface IResponse {

	/**
	 * Returns the time between instruction sent and response received
	 * @return the time
	 */
	public int getDelay();

	/**
	 * Returns the response given by the server
	 * @return the binary response
	 */
	public byte[] getServerBinaryResponse();
}