/**
 * 
 */
package controls.protocols.ldap;

import java.util.ArrayList;
import java.util.List;

import gui.interfaces.TestPlanPanelListener;
import gui.interfaces.protocols.ldap.LDAPTestPlanPanelListenable;
import gui.interfaces.protocols.ldap.LDAPTestPlanPanelListener;
import controls.ctestplanmanagement.AbstractTestPlan;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPPlanTest extends AbstractTestPlan implements LDAPTestPlanPanelListenable {
	
	private List<LDAPTestPlanPanelListener> ldapTestPlanPanelListeners;
	private String root;
	private String login;
	private String password;
	
	public LDAPPlanTest () {
		this.ldapTestPlanPanelListeners = new ArrayList<LDAPTestPlanPanelListener>();
	}

	public String writeJSONString() {
		return null;
	}

	@Override
	public void addTestPlanPanelListener(
			TestPlanPanelListener testPlanPanelListener) {
		if (testPlanPanelListener instanceof LDAPTestPlanPanelListener)
			this.ldapTestPlanPanelListeners.add((LDAPTestPlanPanelListener) testPlanPanelListener);
	}

	@Override
	public void removeTestPlanPanelListener(
			TestPlanPanelListener testPlanPanelListener) {
		this.ldapTestPlanPanelListeners.remove(testPlanPanelListener);
	}

	@Override
	public void set(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}