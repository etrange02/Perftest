package controls.protocols.ldap;

import gui.panels.AbstractTestPlanPanel;
import gui.panels.protocols.ldap.LDAPTestPlanPanel;

import java.io.IOException;
import java.util.List;

import shared.AbstractInstruction;
import controls.ctestplanmanagement.AbstractTestPlan;
import controls.ctestplanmanagement.ProtocolParser;
import controls.ctestplanmanagement.TCPProxy;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPProtocolParser extends ProtocolParser {

	@Override
	public String getProtocolName() {
		return shared.protocols.ldap.LDAPConstants.PROTOCOL_NAME;
	}

	@Override
	public AbstractInstruction createNewInstruction() {
		return new LDAPInstruction();
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

	@Override
	public AbstractTestPlanPanel createNewTestPlanPanel(ITestPlanManagement testPlanManagement) {
		return new LDAPTestPlanPanel(testPlanManagement);
	}

}
