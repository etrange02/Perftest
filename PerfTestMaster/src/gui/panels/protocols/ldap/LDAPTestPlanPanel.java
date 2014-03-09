package gui.panels.protocols.ldap;

import gui.PerfTestTableModel;
import gui.panels.AbstractTestPlanPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import constants.protocols.ldap.LDAPConstants;
import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPTestPlanPanel extends AbstractTestPlanPanel {

	private static final long serialVersionUID = -2136761593193064428L;
	private JTable table;
	private JTextField loginTestField;
	private JTextField passwordTextField;
	private JTextField portTextField;

	public LDAPTestPlanPanel() {
		super();
		this.initPanel();
	}
	
	private void initPanel() {
		JPanel grid = new JPanel(new GridLayout(3, 2));
		
		this.loginTestField = new JTextField();
		this.passwordTextField = new JTextField();
		try {
			this.portTextField = new JFormattedTextField(new MaskFormatter("#####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		grid.add(new JLabel(LDAPConstants.LDAP_TESTPLAN_LOGIN));
		grid.add(this.loginTestField);
		grid.add(new JLabel(LDAPConstants.LDAP_TESTPLAN_PASSWORD));
		grid.add(this.passwordTextField);
		grid.add(new JLabel(LDAPConstants.LDAP_TESTPLAN_PORT));
		grid.add(this.portTextField);
		
		JPanel interfacesPanel = new JPanel(new BorderLayout());
		JPanel northGrid = new JPanel(new GridLayout(1, 2));
		
		JButton addButton= new JButton(GUIConstants.TEST_PLAN_ADD);
		JButton removeButton = new JButton(GUIConstants.TEST_PLAN_REMOVE);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAction(e);
			}
		});
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAction(e);
			}
		});
		
		northGrid.add(addButton);
		northGrid.add(removeButton);
		interfacesPanel.add(northGrid, BorderLayout.NORTH);
		
		Object[][] data = {
				{"213.254.132.132"},
				{"123.123.123.123"}
		};
		this.table = new JTable(new PerfTestTableModel(new String[]{GUIConstants.TEST_PLAN_ID, GUIConstants.TEST_PLAN_ADDRESS}, data));
		interfacesPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		this.getCore().setLayout(new BoxLayout(this.getCore(), BoxLayout.Y_AXIS));
		this.getCore().add(grid);
		this.getCore().add(interfacesPanel);
	}

	private void addAction(ActionEvent e) {
		System.out.println("Action button presses !");
		((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {"654.3241.654.6354"});
	}
	
	private void removeAction(ActionEvent e) {
		System.out.println("Remove button presses !");
		
		int[] selectedLines = this.table.getSelectedRows();
		
		System.out.println(selectedLines.length);
		for (int i = selectedLines.length; i > 0; i--) {
			System.out.println(selectedLines[i-1]);
			((PerfTestTableModel) this.table.getModel()).removeRow(selectedLines[i-1]);
		}
	}
}
