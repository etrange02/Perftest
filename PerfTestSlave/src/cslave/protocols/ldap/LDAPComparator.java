package cslave.protocols.ldap;

import java.util.Arrays;

import shared.protocols.ldap.LDAPConstants;
import cslave.Comparator;
import cslave.interfaces.IResponse;
import cslave.interfaces.ITCPConnectionToTestedServer;


/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public class LDAPComparator extends Comparator {

    @Override
    public Class<? extends ITCPConnectionToTestedServer> getTcpConnectionClazz() {
	return LDAPTCPConnectionToServer.class;
    }

    @Override
    public String getProtocolName() {
	return LDAPConstants.PROTOCOL_NAME;
    }

    @Override
    public boolean isExpectedResponse(IResponse response) {

	byte[] expected = response.getExpectedBinaryResponse();
	byte[] actual = response.getServerBinaryResponse();

	//TODO It seem that the answer can finish by connection 
	//informations that we don't care. Check that we really don't
	//care about them.

	for(int i = actual.length; i > 0; i--){

	    if(Arrays.equals(
		    expected, 
		    Arrays.copyOf(actual, i))) {
		return true;
	    }
	}

	return false;
    }
}
