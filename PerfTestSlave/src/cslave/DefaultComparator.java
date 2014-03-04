/**
 * 
 */
package cslave;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class DefaultComparator extends Comparator {

	protected void processCompare(byte[] binaryResponse, byte[] serverBinaryResponse) {
	}

	public boolean isConcernedComparator(String protocolName) {
		return false;
	}

	public TCPConnectionToTestedServer createNewTCPConnectionToTestedServer() {
		return null;
	}
}