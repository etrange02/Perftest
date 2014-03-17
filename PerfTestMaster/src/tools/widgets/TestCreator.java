package tools.widgets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Dialog to create a test
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public class TestCreator extends JDialog {

	private static final long serialVersionUID = 2255049912687221187L;
	private JRadioButton scalabilityRadioButton;
	private JRadioButton workloadRadioButton;
	private boolean sendData;
	private JTextField testName;
	
	/**
	 * Constructor
	 * @param parent the parent
	 * @param title a title
	 * @param modal true if it is modal
	 */
	public TestCreator(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(300, 160);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initPanel();
	}
	
	private void initPanel() {
		JPanel grid = new JPanel(new GridLayout(2, 2));
		
		JPanel radioGrid = new JPanel();
		radioGrid.setLayout(new BoxLayout(radioGrid, BoxLayout.Y_AXIS));
		scalabilityRadioButton = new JRadioButton("Scalability");
		workloadRadioButton = new JRadioButton("Workload");
		scalabilityRadioButton.setSelected(true);
		radioGrid.add(scalabilityRadioButton);
		radioGrid.add(workloadRadioButton);
		ButtonGroup group = new ButtonGroup();
		group.add(scalabilityRadioButton);
		group.add(workloadRadioButton);
		
		grid.add(new JLabel("Select a kind"));
		grid.add(radioGrid);
		grid.add(new JLabel("Enter a test plan name"));		
		this.testName = new JTextField();
		grid.add(this.testName);		
		
		JPanel control = new JPanel();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = !testName.getText().isEmpty();
				setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		control.add(okButton);
		control.add(cancelButton);
		this.add(grid, BorderLayout.CENTER);
		this.add(control, BorderLayout.SOUTH);
	}

	/**
	 * Show the dialog
	 * @return true if the user clicks on OK
	 */
	public boolean showDialog() {
		this.sendData = false;
		this.setVisible(true);
		return this.sendData;
	}

	/**
	 * Returns true if the user selects the kind Scalability, false for a workload kind
	 * @return true or false according to the test
	 */
	public boolean isScalabilityTest() {
		return this.scalabilityRadioButton.isSelected();
	}

	/**
	 * Returns the name of the test
	 * @return a test name
	 */
	public String getTestName() {
		return this.testName.getText();
	}
}
