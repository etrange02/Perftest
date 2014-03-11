package gui.interfaces.protocols.ldap;

import gui.interfaces.TestPlanPanelListener;

public interface LDAPTestPlanPanelListener extends TestPlanPanelListener {
	
	public void updateLogin(String login);
	
	public void updatePassword(String password);
	
	public void updateRoot(String root);

}
