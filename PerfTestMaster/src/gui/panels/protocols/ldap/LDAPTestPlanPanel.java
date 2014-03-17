package gui.panels.protocols.ldap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.interfaces.protocols.ldap.LDAPTestPlanPanelListener;
import gui.panels.AbstractTestPlanPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.ldap.LDAPConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPTestPlanPanel extends AbstractTestPlanPanel implements LDAPTestPlanPanelListener {

	private static final long serialVersionUID = -2136761593193064428L;
	private JTextField rootField;
	private JTextField loginField;
	private JTextField passwordField;

	public LDAPTestPlanPanel(ITestPlanManagement testPlanManagement) {
		super(testPlanManagement);
		this.setTitle("LDAP Test plan");
		this.initPanel();
		this.getTestPlanManagement().addTestPlanPanelListener(this);
	}
	
	private void initPanel() {
		
		this.rootField = new JTextField();
		this.loginField = new JTextField();
		this.passwordField = new JTextField();
		
		this.rootField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				getTestPlanManagement().testPlanGenericSet("ROOT", field.getText());
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		this.loginField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				getTestPlanManagement().testPlanGenericSet("LOGIN", field.getText());
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		this.passwordField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				getTestPlanManagement().testPlanGenericSet("PASSWORD", field.getText());
			}
			public void keyPressed(KeyEvent e) {
			}
		});

		this.addPropertyLine(new JLabel("Root"), this.rootField);
		this.addPropertyLine(new JLabel(LDAPConstants.LDAP_TESTPLAN_LOGIN), this.loginField);
		this.addPropertyLine(new JLabel(LDAPConstants.LDAP_TESTPLAN_PASSWORD), this.passwordField);
		
		this.updateUI();
	}

	@Override
	public void updateLogin(String login) {
		this.loginField.setText(login);
	}

	@Override
	public void updatePassword(String password) {
		this.passwordField.setText(password);
	}

	@Override
	public void updateRoot(String root) {
		this.rootField.setText(root);
	}
}
