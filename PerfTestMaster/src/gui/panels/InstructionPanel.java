package gui.panels;

import gui.ButtonTableRenderer;
import gui.PerfTestTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class InstructionPanel extends JPanel {

	private static final long serialVersionUID = -6149233856276646097L;
	private JTable table;

	public InstructionPanel() {
		super();
		this.initPanel();
	}
	
	private void initPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel northGrid = new JPanel(new GridLayout(1, 3));
		
		JButton addButton= new JButton(GUIConstants.INSTRUCTION_ADD);
		JButton removeButton = new JButton(GUIConstants.INSTRUCTION_REMOVE);
		
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
		this.add(northGrid, BorderLayout.NORTH);
		
		Object[][] data = {
				{"Create", GUIConstants.INSTRUCTION_EDIT},
				{"Read", GUIConstants.INSTRUCTION_EDIT}
		};
		this.table = new JTable(new PerfTestTableModel(new String[]{GUIConstants.INSTRUCTION_ID, GUIConstants.INSTRUCTION_TYPE, GUIConstants.INSTRUCTION_EDITION}, data));
		this.table.getColumn(GUIConstants.INSTRUCTION_EDITION).setCellRenderer(new ButtonTableRenderer());
		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private void addAction(ActionEvent e) {
		System.out.println("Action button presses !");
		((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {"Update", new JButton(GUIConstants.INSTRUCTION_EDIT)});
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
