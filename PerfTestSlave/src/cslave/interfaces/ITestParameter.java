/**
 * 
 */
package cslave.interfaces;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
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
}