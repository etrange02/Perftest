package cslave.protocols.ldap;

import cslave.Comparator;
import cslave.TCPConnectionToTestedServer;

public class LDAPComparator extends Comparator {

	@Override
	protected void processCompare(byte[] binaryResponse, byte[] serverBinaryResponse) {
		// TODO Auto-generated method stub

	}

	public boolean isConcernedComparator(String protocolName) {
		return "LDAP".equals(protocolName);
	}

	@Override
	public TCPConnectionToTestedServer createNewTCPConnectionToTestedServer() {
		// TODO Auto-generated method stub
		return null;
	}

}
