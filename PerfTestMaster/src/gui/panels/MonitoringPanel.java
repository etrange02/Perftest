package gui.panels;

import gui.interfaces.AbstractMonitoredTestMonitorListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
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
public class MonitoringPanel extends JPanel implements AbstractMonitoredTestMonitorListener {

	private static final long serialVersionUID = -5487965233740014275L;
	private JLabel titleLabel;
	private JPanel core;
	private JFormattedTextField delaySpinner;
	private JFormattedTextField slaveSpinner;
	private JPanel targetsPanel;
	private ITestPlanManagement testPlanManagement;
	private boolean isScalablity;
	private List<JCheckBox> targets;
	private AbstractMonitoredTest monitoredTest;
	private JLabel instructionLabel;

	public MonitoringPanel(String title, ITestPlanManagement testPlanManagement, AbstractMonitoredTest monitoredTest, boolean isScalablity) {
		super();
		this.testPlanManagement = testPlanManagement;
		this.isScalablity = isScalablity;
		this.targets = new ArrayList<JCheckBox>();
		this.monitoredTest = monitoredTest;
		this.initPanel();
		this.titleLabel.setText(title);
		this.monitoredTest.addAbstractMonitoredTestMonitorListener(this);
	}
	
	private void initPanel() {
		
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		this.titleLabel = new JLabel("Default monitoring page");
		titlePanel.add(this.titleLabel);
		this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel leftPanel = new JPanel();
		JPanel verticalPanel =  new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.PAGE_AXIS));
		
		this.delaySpinner = new JFormattedTextField(monitoredTest.getInstructionDelay());
		this.slaveSpinner = new JFormattedTextField("0");
		
		this.delaySpinner.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {			
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				System.out.println("received " + field.getText());
				int res = 0;
				try {
					res = Integer.parseInt(field.getText());
				} catch (NumberFormatException nfe) { res = 0; }
				testPlanManagement.setDelayBetweenInstructions(monitoredTest, res);	
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		this.slaveSpinner.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {			
			}
			public void keyReleased(KeyEvent e) {
				JTextField field = (JTextField) e.getSource();
				System.out.println("received " + field.getText());
				int res = 0;
				try {
					res = Integer.parseInt(field.getText());
				} catch (NumberFormatException nfe) { res = 0; }
				testPlanManagement.setAffectedSlaves(monitoredTest, res);
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		
		this.instructionLabel = new JLabel();
		updateInstructionsBySeconds(1, 0);

		JPanel leftGrid = new JPanel(new GridLayout( (isScalablity ? 2 : 1) , 3));
		leftGrid.add(new JLabel("Delay"));
		leftGrid.add(this.delaySpinner);
		leftGrid.add(new JLabel(" ms"));
		if (this.isScalablity) {
			leftGrid.add(new JLabel("Slaves"));
			leftGrid.add(this.slaveSpinner);
			leftGrid.add(this.instructionLabel);
		}
		verticalPanel.add(leftGrid);
		
		this.targetsPanel = new JPanel();
		targetsPanel.setBorder(BorderFactory.createTitledBorder("Targets"));
		this.targetsPanel.setLayout(new BoxLayout(targetsPanel, BoxLayout.PAGE_AXIS));
		verticalPanel.add(targetsPanel);
		leftPanel.add(verticalPanel);
		
		this.core = new JPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(this.core, BorderLayout.CENTER);
	}
	
	public JPanel getCore() {
		return this.core;
	}
	
	@Override
	public void updateTargets(List<String> targetsList, List<String> selectedTargets) {
		this.targetsPanel.removeAll();
		this.targets.clear();
		
		Iterator<String> iter = targetsList.iterator();
		String targetAddress = "";
		JCheckBox checkBox = null;
		while (iter.hasNext()) {
			targetAddress = iter.next();
			checkBox = new JCheckBox(targetAddress);
			checkBox.setSelected(selectedTargets.contains(targetAddress));
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					targetSelected(e);
				}
			});
			this.targets.add(checkBox);
			this.targetsPanel.add(checkBox);
		}
		this.updateUI();
	}
	
	private void targetSelected(ActionEvent e) {
		List<String> list = new ArrayList<String>();
		Iterator<JCheckBox> iter = this.targets.iterator();
		JCheckBox checkBox = null;
		while (iter.hasNext()) {
			checkBox = iter.next();
			if (checkBox.isSelected()) {
				list.add(checkBox.getText());
			}
		}
		this.testPlanManagement.setSelectedTargets(monitoredTest, list);
	}

	@Override
	public void updateDelay(int delay) {
		this.delaySpinner.setText("" + delay);
		int slaveCount = Integer.parseInt(this.slaveSpinner.getValue().toString());
		this.updateInstructionsBySeconds(delay, slaveCount);
	}

	@Override
	public void updateMaxSlaveCount(int count) {
		this.slaveSpinner.setToolTipText("Max: " + count + " available slaves.");
		this.updateUI();
	}
	
	private void updateInstructionsBySeconds(int delay, int slaveCount) {
		double res = 1000.0/delay*slaveCount;
		this.instructionLabel.setText(" ~" + res + " instructions/s");
		this.updateUI();
	}

	@Override
	public void updateSlaveCount(int count) {
		this.slaveSpinner.setText("" + count);
		int delay = Integer.parseInt(this.delaySpinner.getValue().toString());
		this.updateInstructionsBySeconds(delay, count);
		this.updateUI();
	}
}
