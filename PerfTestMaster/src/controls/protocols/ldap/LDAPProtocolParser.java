package controls.protocols.ldap;

import java.io.IOException;
import java.util.List;

import shared.AbstractInstruction;
import constants.protocols.ldap.LDAPConstants;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.AbstractTestPlan;
import controls.ctestplanmanagement.ProtocolParser;
import controls.ctestplanmanagement.TCPProxy;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPProtocolParser extends ProtocolParser {

	@Override
	public String getProtocolName() {
		return "LDAP";
	}

	@Override
	public AbstractInstruction createNewInstruction() {
		return new LDAPInstruction();
	}

	/**
	 * Useless ????
	 */
	@Override
	public AbstractMonitoredTest createNewTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractTestPlan createNewPlanTest() {
		return new LDAPPlanTest();
	}

	@Override
	public TCPProxy createNewTCPProxy(
			String hostname, int port,
			List<AbstractInstruction>instructions) throws IOException {
		
		return new LDAP_TCPProxy(hostname,port,instructions);
	}

	@Override
	public AbstractTestPlan readJSONFileTestPlan(Object values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractInstruction readJSONStringInstruction(Object values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDefaultProtocolPort() {
		return LDAPConstants.LDAP_DEFAULT_PORT;
	}

}
