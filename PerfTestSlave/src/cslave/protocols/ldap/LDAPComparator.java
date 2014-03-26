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

	return Arrays.equals(
		response.getExpectedBinaryResponse(),
		response.getServerBinaryResponse()
		);
    }
}
