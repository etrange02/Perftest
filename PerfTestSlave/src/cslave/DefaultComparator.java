/**
 * 
 */
package cslave;

import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class DefaultComparator extends Comparator {

	protected void processCompare(
			byte[] binaryResponse, byte[] serverBinaryResponse) {
	}

	public TCPConnectionToTestedServer createNewTCPConnectionToTestedServer() {
		return null;
	}

	public String getProtocolName() {
		return "";
	}

	@Override
	public Class<? extends ITCPConnectionToTestedServer> getTcpConnectionClazz() {
	    // TODO Auto-generated method stub
	    return null;
	}
}