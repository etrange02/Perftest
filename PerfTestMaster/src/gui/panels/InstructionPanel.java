package gui.panels;

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.PerfTestTableModel;
import gui.interfaces.AbstractMonitoredTestListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import shared.IInstruction;
import tools.GUIConstants;
import tools.GUIFactory;
import tools.widgets.InstructionCreator;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class InstructionPanel extends JPanel implements AbstractMonitoredTestListener {

	private static final long serialVersionUID = -6149233856276646097L;
	private JTable table;
	private ITestPlanManagement testPlanManagement;
	private AbstractMonitoredTest abstractMonitoredTest;
	private JFrame frame;
	private ButtonEditor editor;

	public InstructionPanel(JFrame frame, ITestPlanManagement testPlanManagement, AbstractMonitoredTest abstractMonitoredTest) {
		super();
		this.testPlanManagement = testPlanManagement;
		this.abstractMonitoredTest = abstractMonitoredTest;
		this.frame = frame;
		this.abstractMonitoredTest.addAbstractMonitoredTestListener(this);
		
		this.initPanel();
	}
	
	private void initPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Instructions");
		titlePanel.add(titleLabel);
		titleLabel.setFont(titleLabel.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel instructionsPanel = new JPanel(new BorderLayout());
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
		instructionsPanel.add(northGrid, BorderLayout.NORTH);
		
		Object[][] data = {};
				/*{"Create", GUIConstants.INSTRUCTION_EDIT},
				{"Read", GUIConstants.INSTRUCTION_EDIT}
		};*/
		this.table = new JTable(new PerfTestTableModel(new String[]{GUIConstants.INSTRUCTION_ID, GUIConstants.INSTRUCTION_NAME, GUIConstants.INSTRUCTION_EDITION}, data));
		this.editor = new ButtonEditor(new JCheckBox(), GUIConstants.INSTRUCTION_EDIT);
		this.editor.getJButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editAction(e);
			}
		});
		
		this.table.getColumn(GUIConstants.INSTRUCTION_EDITION).setCellRenderer(new ButtonRenderer(GUIConstants.INSTRUCTION_EDIT));
		this.table.getColumn(GUIConstants.INSTRUCTION_EDITION).setCellEditor(this.editor);
		instructionsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(instructionsPanel, BorderLayout.CENTER);
	}

	private void addAction(ActionEvent e) {
		System.out.println("Action button pressed !");
		
		InstructionCreator creator = GUIFactory.instructionCreator(this.frame, "Instruction creation", true);
		if (creator.showDialog()) {
			IInstruction instruction = this.testPlanManagement.addNewInstruction(this.abstractMonitoredTest, creator.getName());
			if (null != instruction) {
				instruction.setReadableRequest(creator.getRequest());
				System.out.println("newTestPlanMenuItemAction");
			}
		}
	}
	
	private void removeAction(ActionEvent e) {
		System.out.println("Remove button pressed !");
		int[] selectedLines = this.table.getSelectedRows();
		
		if (0 == selectedLines.length) {
			return;
		}
		
		for (int i = selectedLines.length -1; i >=0 ; --i) {
			this.testPlanManagement.removeInstruction(abstractMonitoredTest, selectedLines[i]);
		}
	}
	
	private void editAction(ActionEvent e) {
		System.out.println("edit button pressed");
		int row = this.editor.getLastRow();
		
		if (row == -1)
			return;
		
		InstructionCreator creator = GUIFactory.instructionCreator(this.frame, "Instruction creation", true);
		IInstruction instruction = this.abstractMonitoredTest.getInstructions().get(row);
		creator.setData(instruction.getName(), instruction.getReadableRequest());
		
		if (creator.showDialog()) {
			this.testPlanManagement.editInstruction(this.abstractMonitoredTest, row, creator.getName(), creator.getRequest());
		}
	}

	@Override
	public void updateData() {
		((PerfTestTableModel) this.table.getModel()).clear();

		Iterator<IInstruction> iter = this.abstractMonitoredTest.getInstructions().iterator();
		while (iter.hasNext()) {
			((PerfTestTableModel) this.table.getModel()).addRow(new Object[] {iter.next().getName(), GUIConstants.INSTRUCTION_EDIT});
		}
		this.updateUI();
	}
}
