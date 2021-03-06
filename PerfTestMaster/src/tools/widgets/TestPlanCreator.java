package tools.widgets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialog to create a test plan
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public class TestPlanCreator extends JDialog {

	private static final long serialVersionUID = 3262761779816602007L;
	private JComboBox<Object> protocols;
	private boolean sendData;
	
	/**
	 * Constructor
	 * @param parent the parent
	 * @param title a title
	 * @param modal true if it is modal
	 * @param protocolList a list of protocol names
	 */
	public TestPlanCreator(JFrame parent, String title, boolean modal, List<String> protocolList) {
		super(parent, title, modal);
		this.setSize(300, 90);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initPanel(protocolList);
	}
	
	private void initPanel(List<String> protocolList) {
		
		JPanel grid = new JPanel(new GridLayout(1, 1));
		
		grid.add(new JLabel("Select a protocol"));
		this.protocols = new JComboBox<Object>(protocolList.toArray());
		grid.add(this.protocols);
		
		JPanel control = new JPanel();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = true;
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
	 * Returns the name of the selected value, which is a protocol name
	 * @return a protocol name
	 */
	public String getSelectedProtocol() {
		return this.protocols.getSelectedItem().toString();
	}

}
