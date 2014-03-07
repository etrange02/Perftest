package controls.protocols.ldap;

import shared.AbstractInstruction;
import constants.protocols.ldap.LDAPConstants;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.AbstractTestPlan;
import controls.ctestplanmanagement.ProtocolParser;
import controls.ctestplanmanagement.TCPProxy;

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
	public TCPProxy createNewTCPProxy() {
		return new LDAP_TCPProxy();
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
