/**
 * 
 */
package controls.cslavemanagement.interfaces;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ISlave {

	/**
	 * Returns the name of the Slave
	 * @return the name
	 */
	public String getName();

	/**
	 * Returns the address of the slave
	 * @return the address
	 */
	public String getAddress();

	/**
	 * Indicates if the selected test has been deployed on this slave
	 * @return true if deployed, false otherwise
	 */
	public boolean isDeployed();
	
	/**
	 * Indicates if the selected test is running on this slave
	 * @return true if running, false otherwise
	 */
	public boolean isRunning();
}