package cslave.protocols.ldap;

import shared.Constants;
import cslave.Comparator;
import cslave.interfaces.ITCPConnectionToTestedServer;

public class LDAPComparator extends Comparator {

	@Override
	protected void processCompare(byte[] binaryResponse, byte[] serverBinaryResponse) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getProtocolName() {
		return Constants.LDAP;
	}

	@Override
	public Class<? extends ITCPConnectionToTestedServer> getTcpConnectionClazz() {
	    // TODO Auto-generated method stub
	    return null;
	}

}
