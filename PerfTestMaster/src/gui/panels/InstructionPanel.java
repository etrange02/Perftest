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

import shared.interfaces.IInstruction;
import tools.GUIConstants;
import tools.GUIFactory;
import tools.widgets.InstructionAdder;
import tools.widgets.InstructionCreator;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.ldap.instructions.LDAPInstructionCreate;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.1
 */
public class InstructionPanel extends JPanel implements AbstractMonitoredTestListener {

    private static final long serialVersionUID = -6149233856276646097L;
    private JTable table;
    private ITestPlanManagement testPlanManagement;
    private AbstractMonitoredTest abstractMonitoredTest;
    private JFrame frame;
    private ButtonEditor editor;
    private GUIFactory factory;





    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZER ********************************************
     * *********************************************************************/

    public InstructionPanel(JFrame frame, ITestPlanManagement testPlanManagement, AbstractMonitoredTest abstractMonitoredTest) {
	super();
	this.testPlanManagement = testPlanManagement;
	this.abstractMonitoredTest = abstractMonitoredTest;
	this.frame = frame;
	this.abstractMonitoredTest.addAbstractMonitoredTestListener(this);
	this.factory = GUIFactory.getGUIFactory(testPlanManagement.getUsedProtocolParser().getProtocolName());

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
		try {
		    addAction(e);
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
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
		try {
		    editAction(e);
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
	    }
	});

	this.table.getColumn(GUIConstants.INSTRUCTION_EDITION).setCellRenderer(new ButtonRenderer(GUIConstants.INSTRUCTION_EDIT));
	this.table.getColumn(GUIConstants.INSTRUCTION_EDITION).setCellEditor(this.editor);
	instructionsPanel.add(new JScrollPane(table), BorderLayout.CENTER);

	this.add(titlePanel, BorderLayout.NORTH);
	this.add(instructionsPanel, BorderLayout.CENTER);
    }






    /* *********************************************************************
     * ACTIONS *************************************************************
     * *********************************************************************/

    private void addAction(ActionEvent e) throws Exception {
	System.out.println("Action button pressed !");

	InstructionAdder adder = 
		factory.createInstructionAdder(
			this.frame,
			"Add a new instruction",
			true
			);

	if(adder.showDialog()) {

	    IInstruction instruction = 
		    this.testPlanManagement.addNewInstruction(
			    this.abstractMonitoredTest, 
			    adder.getInstructionName(),
			    adder.getSelectedInstructionType()
			    );
	    if (null != instruction) {
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

    private void editAction(ActionEvent e) throws Exception {
	System.out.println("edit button pressed");
	int row = this.editor.getLastRow();

	if (row == -1)
	    return;

	IInstruction oldInstruction = this.abstractMonitoredTest.getInstructions().get(row);
	InstructionCreator creator = 
		factory.createInstructionCreator(
			this.frame,
			"Edit an instruction",
			true,
			oldInstruction);

	if(creator.showDialog()) {

	    IInstruction newInstruction = creator.getCreatedInstruction();

	    newInstruction.setName(oldInstruction.getName());
	    
	    this.testPlanManagement.replaceInstruction(
		    this.abstractMonitoredTest, 
		    row, 
		    newInstruction);
	}
    }






    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/

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
