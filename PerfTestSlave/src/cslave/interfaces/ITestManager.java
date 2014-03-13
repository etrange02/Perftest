/**
 * 
 */
package cslave.interfaces;

import cslave.Comparator;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public interface ITestManager {

	/**
	 * Adds a comparator to the testManager
	 * @param comparator a protocol comparator
	 */
	public void addComparator(Comparator comparator);

	/**
	 * 
	 */
	public void start();
	
	/**
	 * 
	 */
	public void stop();
}