/**
 * 
 */
package cslave.interfaces;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0 
 * 
 * @author Jean-Luc Amitousa Mankoy jean-luc.amitousa-mankoy@hotmail.fr
 * @version 1.1
 * Add delay
 */
public interface ITestParameter {

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
	 * Returns the port
	 * @return the port
	 */
	public int getPort();

	/**
	 * Returns the address
	 * @return the address
	 */
	public String getIPAddress();

	/**
	 * Modifies the protocol name which is used inside tests
	 * @param protocolName
	 */
	public void setProtocolName(String protocolName);

	/**
	 * Returns the protocol name
	 * @return the protocol name
	 */
	public String getProtocolName();
	
	/**
	 * Modifies the delay between two instructions
	 * @param delay
	 */
	public void setDelay(int delay);
	
	/**
	 *  Returns the delay between two instructions
	 * @return  the delay between two instructions
	 */
	public int getDelay();
}