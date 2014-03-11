package gui.panels;

import gui.PerfTestTableModel;
import gui.interfaces.SlaveListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controls.cslavemanagement.Slave;
import controls.cslavemanagement.interfaces.ISlaveManagement;
import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class SlavesPanel extends JPanel implements SlaveListener {

	private static final long serialVersionUID = 5462235546989652245L;
	private JTable table;
	private ISlaveManagement slaveManagement;
	
	public SlavesPanel(ISlaveManagement slaveManagement) {
		super();
		this.slaveManagement = slaveManagement;
		this.initPanel();
		this.slaveManagement.addSlaveListener(this);
	}
	
	private void initPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel northGrid = new JPanel(new GridLayout(1, 3));
		
		JButton addButton= new JButton(GUIConstants.SLAVES_ADD);
		JButton removeButton = new JButton(GUIConstants.SLAVES_REMOVE);
		JButton autodetectButton = new JButton(GUIConstants.SLAVES_AUTODETECT);
		
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
		autodetectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autodetectAction(e);
			}
		});
		
		northGrid.add(addButton);
		northGrid.add(removeButton);
		northGrid.add(autodetectButton);
		this.add(northGrid, BorderLayout.NORTH);
		
		Object[][] data = {};
				/*{"213.254.132.132", "389"},
				{"123.123.123.123", "389"}
		};*/
		this.table = new JTable(new PerfTestTableModel(new String[]{GUIConstants.SLAVES_ID, GUIConstants.SLAVES_IP_ADDRESS, GUIConstants.SLAVES_PORT}, data));
		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private void addAction(ActionEvent e) {
		System.out.println("Action button presses !");
		//((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {"654.3241.654.6354", "389"});
		String res = JOptionPane.showInputDialog(null, "Slave adding", "Enter an ip address", JOptionPane.PLAIN_MESSAGE);
		this.slaveManagement.addSlave(res);
	}
	
	private void removeAction(ActionEvent e) {
		System.out.println("Remove button presses !");
		
		int[] selectedLines = this.table.getSelectedRows();
		
		if (0 == selectedLines.length) {
			return;
		}
		String[] selectedAdresses = new String[selectedLines.length];
		
		for (int i = 0; i<selectedLines.length; ++i) {
			selectedAdresses[i] = (String) ((PerfTestTableModel) this.table.getModel()).getValueAt(selectedLines[i], 1);
		}
		for (int i = 0; i<selectedAdresses.length; ++i) {
			this.slaveManagement.removeSlave(selectedAdresses[i]);			
		}
		
		/*System.out.println(selectedLines.length);
		for (int i = selectedLines.length; i > 0; i--) {
			System.out.println(selectedLines[i-1]);
			((PerfTestTableModel) this.table.getModel()).removeRow(selectedLines[i-1]);
		}*/
	}
	
	private void autodetectAction(ActionEvent e) {
		System.out.println("Auto detect button presses !");
		String res = JOptionPane.showInputDialog(null, "Slave auto-detection", "Enter a network ip", JOptionPane.PLAIN_MESSAGE);
		this.slaveManagement.detectSlaves(res);
	}

	@Override
	public void updateData() {
		((PerfTestTableModel) this.table.getModel()).clear();

		Iterator<Slave> iter = this.slaveManagement.getSlave().iterator();
		Slave slave = null;
		while (iter.hasNext()) {
			slave = iter.next();
			((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {slave.getAddress(), "Default port"});
		}
		this.updateUI();
	}
}
