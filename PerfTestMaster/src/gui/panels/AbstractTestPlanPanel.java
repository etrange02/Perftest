package gui.panels;

import gui.PerfTestTableModel;
import gui.interfaces.TestPlanPanelListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import constants.protocols.ldap.LDAPConstants;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTestPlanPanel extends JPanel implements TestPlanPanelListener {

	private static final long serialVersionUID = 6858271818355416805L;
	private JLabel titleLabel;
	private ITestPlanManagement testPlanManagement;
	private JTable table;
	private JTextField portTextField;
	private JPanel grid;
	private GridLayout gridLayout;
	
	public AbstractTestPlanPanel(ITestPlanManagement testPlanManagement) {
		super();
		this.testPlanManagement = testPlanManagement;
		this.initPanel();
	}
	
	public void setTitle(String title) {
		this.titleLabel.setText(title);
	}
	
	private void initPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		this.titleLabel = new JLabel(GUIConstants.ABSTRACTTESTPLANPANEL_DEFAULT_TITLE);
		titlePanel.add(this.titleLabel);
		this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel properties = new JPanel();
		properties.setLayout(new BoxLayout(properties, BoxLayout.Y_AXIS));
		
		this.gridLayout = new GridLayout(1, 2);
		this.grid = new JPanel(this.gridLayout);		

		this.portTextField = new JFormattedTextField("0");//new MaskFormatter("###"));
		this.portTextField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {			
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				System.out.println("received " + field.getText());
				int res = 0;
				try {
					res = Integer.parseInt(field.getText());
				} catch (NumberFormatException nfe) { res = 0; }
				testPlanManagement.setPort(res);	
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		
		this.grid.add(new JLabel(LDAPConstants.LDAP_TESTPLAN_PORT));
		this.grid.add(this.portTextField);
		
		
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
		
		Object[][] data = {};
		this.table = new JTable(new PerfTestTableModel(new String[]{GUIConstants.TEST_PLAN_ID, GUIConstants.TEST_PLAN_ADDRESS}, data));
		interfacesPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		properties.add(this.grid);
		properties.add(interfacesPanel);
				
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(properties, BorderLayout.CENTER);
	}
	
	protected ITestPlanManagement getTestPlanManagement() {
		return this.testPlanManagement;
	}
	
	protected void addPropertyLine(JLabel value, JTextField field) {
		this.gridLayout.setRows(this.gridLayout.getRows() + 1);
		this.grid.add(value);
		this.grid.add(field);
	}

	private void addAction(ActionEvent e) {
		System.out.println("Action button pressed !");
		String target = JOptionPane.showInputDialog(null, "Target add", "Adds a target", JOptionPane.PLAIN_MESSAGE);
		if (null == target || target.isEmpty())
			return;
		this.getTestPlanManagement().addTarget(target);
	}
	
	private void removeAction(ActionEvent e) {
		System.out.println("Remove button pressed !");
		
		int[] selectedLines = this.table.getSelectedRows();
		
		if (0 == selectedLines.length) {
			return;
		}
		String[] selectedTargets = new String[selectedLines.length];
		
		for (int i = 0; i<selectedLines.length; ++i) {
			selectedTargets[i] = (String) ((PerfTestTableModel) this.table.getModel()).getValueAt(selectedLines[i], 1);
		}
		for (int i = 0; i<selectedTargets.length; ++i) {
			this.getTestPlanManagement().removeTarget(selectedTargets[i]);			
		}
	}

	@Override
	public void updatePort(String port) {
		this.portTextField.setText(port);
	}

	@Override
	public void updateNetworkInterfaces() {
		((PerfTestTableModel) this.table.getModel()).clear();

		Iterator<String> iter = this.getTestPlanManagement().getTestPlan().getTargets().iterator();
		while (iter.hasNext()) {
			((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {iter.next()});
		}
		this.updateUI();
	}

}
