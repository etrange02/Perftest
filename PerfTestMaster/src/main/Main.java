package main;

import controls.cslavemanagement.SlaveManagementFacade;
import controls.ctestplanmanagement.TestPlanManagementFacade;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.ldap.LDAPProtocolParser;
import gui.Frame;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Main {

	public static void main (String[] args) {
		ITestPlanManagement testPlanManagement = new TestPlanManagementFacade();
		testPlanManagement.setSlaveManagement(new SlaveManagementFacade());
		testPlanManagement.addProtocolParser(new LDAPProtocolParser());
		new Frame();
	}
}
