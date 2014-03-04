/**
 * 
 */
package cslave;

import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class Comparator {

	private int successNumber;
	private int missNumber;
	
	public Comparator() {
		this.successNumber = 0;
		this.missNumber = 0;
	}

	public boolean compare(Scenario scenario, AbstractTest abstractTest) {
		return false;
	}

	/**
	 * Returns the number of successes
	 * @return
	 */
	public int getSuccess() {
		return this.successNumber;
	}

	/**
	 * Returns the number of misses
	 * @return
	 */
	public int getMiss() {
		return this.missNumber;
	}

	/**
	 * Processes the comparison between pre-calculated responses and responses given by the server
	 * @param binaryResponse the pre-calculated response
	 * @param serverBinaryResponse the response given by the server
	 */
	protected abstract void processCompare(byte[] binaryResponse, byte[] serverBinaryResponse);

	/**
	 * Tests if the given protocol name is the one implemented by the comparator
	 * @param protocolName a protocol name
	 * @return true if it is implemented, false otherwise
	 */
	public abstract boolean isConcernedComparator(String protocolName);

	/**
	 * Returns a TCPConnectionToTestedServer which corresponds to the implemented protocol
	 * @return a new TCPConnectionToTestedServer
	 */
	public abstract TCPConnectionToTestedServer createNewTCPConnectionToTestedServer();
}