package gui.interfaces.protocols.ldap;

import gui.interfaces.TestPlanPanelListener;

/**
 * Observer pattern. For LDAP panels
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface LDAPTestPlanPanelListener extends TestPlanPanelListener {

	/**
	 * Modifies the login value contained in the corresponding field
	 * @param login a login
	 */
	public void updateLogin(String login);

	/**
	 * Modifies the password value contained in the corresponding field
	 * @param password a password
	 */
	public void updatePassword(String password);

	/**
	 * Modifies the root value contained in the corresponding field
	 * @param root a root
	 */
	public void updateRoot(String root);

}
