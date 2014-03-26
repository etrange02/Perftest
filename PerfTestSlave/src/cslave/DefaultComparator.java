/**
 * 
 */
package cslave;

import cslave.interfaces.IResponse;
import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class DefaultComparator extends Comparator {

	@Override
	public Class<? extends ITCPConnectionToTestedServer> getTcpConnectionClazz() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public boolean isConcernedComparator(String protocolName) {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public String getProtocolName() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public boolean isExpectedResponse(IResponse response) {
	    // TODO Auto-generated method stub
	    return false;
	}
}