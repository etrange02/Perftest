/**
 * 
 */
package controls.protocols.ldap;

import gui.interfaces.TestPlanPanelListener;
import gui.interfaces.protocols.ldap.LDAPTestPlanPanelListenable;
import gui.interfaces.protocols.ldap.LDAPTestPlanPanelListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		if (null == key || key.isEmpty() || null == value)
			return;
		
		switch (key) {
			case "ROOT":
				this.setRoot(value.toString());
				break;
			case "LOGIN":
				this.setLogin(value.toString());
				break;
			case "PASSWORD":
				this.setPassword(value.toString());
				break;
		}
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		if (null == root)
			return;
		this.root = root;
		
		Iterator<LDAPTestPlanPanelListener> iter = this.ldapTestPlanPanelListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateRoot(root);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (null == login)
			return;
		this.login = login;
		
		Iterator<LDAPTestPlanPanelListener> iter = this.ldapTestPlanPanelListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateLogin(login);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (null == password)
			return;
		this.password = password;
		
		Iterator<LDAPTestPlanPanelListener> iter = this.ldapTestPlanPanelListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updatePassword(password);
		}
	}

}