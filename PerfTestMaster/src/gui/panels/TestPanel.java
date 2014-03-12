package gui.panels;

import gui.interfaces.TestListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.GUIConstants;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPanel extends JPanel implements TestListener {

	private static final long serialVersionUID = -288280364414876606L;
	private JLabel titleLabel;
	private JLabel typeLabel;
	private JLabel lastRunLabel;
	private JTextField nameField;
	private AbstractMonitoredTest abstractMonitoredTest;
	private ITestPlanManagement testPlanManagement;

	public TestPanel(String title, String name, String type, ITestPlanManagement testPlanManagement, AbstractMonitoredTest abstractMonitoredTest) {
		super();
		this.abstractMonitoredTest = abstractMonitoredTest;
		this.testPlanManagement = testPlanManagement;
		
		this.initPanel(title, name, type);		
		this.abstractMonitoredTest.addTestListener(this);
	}
	
	private void initPanel(String title, String name, String type) {
		this.setLayout(new BorderLayout());
		
		this.titleLabel = new JLabel(title);
		this.nameField = new JTextField(name);
		this.typeLabel = new JLabel(type);
		this.lastRunLabel = new JLabel("Unknown");
		
		this.nameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				testPlanManagement.renameTest(abstractMonitoredTest, ((JTextField) e.getSource()).getText());
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(this.titleLabel);
		this.add(titlePanel, BorderLayout.NORTH);
		this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel horiz = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(3, 2));
		grid.add(new JLabel("Name"));
		grid.add(this.nameField);
		grid.add(new JLabel("Type"));
		grid.add(this.typeLabel);
		grid.add(new JLabel("Last run"));
		grid.add(this.lastRunLabel);
		horiz.add(grid, BorderLayout.NORTH);
		this.add(horiz, BorderLayout.CENTER);
	}
	
	public void setTitle(String title) {
		this.titleLabel.setText(title);
	}
	
	public void setName(String name) {
		this.nameField.setText(name);
	}
	
	public void setLastRun(String lastRun) {
		this.lastRunLabel.setText(lastRun);
	}

	@Override
	public void addScalabilityTestListener(
			AbstractMonitoredTest abstractMonitoredTest) {
		
	}

	@Override
	public void addWorkloadTestListener(
			AbstractMonitoredTest abstractMonitoredTest) {
		
	}

	@Override
	public void renameTest(String newName) {
		this.setName(newName);
		this.updateUI();
	}
}
