package cslave.protocols.ldap;

import shared.Constants;
import cslave.Comparator;
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
	return Constants.LDAP;
    }
    
    @Override
    public boolean isConcernedComparator(String protocolName) {
	return Constants.LDAP.compareTo(protocolName)==0;
    }
}
