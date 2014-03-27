package tools.widgets.ldap;

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.PerfTestTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.ModificationItem;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import shared.interfaces.IInstruction;
import tools.widgets.InstructionCreator;
import controls.protocols.ldap.instructions.LDAPInstructionUpdate;

public class LDAPInstructionUpdateCreator extends InstructionCreator {

    private JTextField dnEntry;
    private JTable modificationsItemsTable;
    private ButtonEditor editor;
    private JFrame frame;




    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZERS *******************************************
     * *********************************************************************/

    /**
     * Constructor
     * @param parent the parent
     * @param title a title
     * @param modal true if it is modal
     * @throws NamingException 
     */
    public LDAPInstructionUpdateCreator(
	    JFrame parent, 
	    String title, 
	    boolean modal,
	    LDAPInstructionUpdate ldapInstructionUpdate) throws NamingException {

	super(parent, title, modal);

	this.frame = parent;
	this.setSize(500, 220);
	this.initPanel();
	setData(ldapInstructionUpdate);
    }



    private void initPanel() {

	JPanel addInstructionPanel = new JPanel(new BorderLayout());
	Object[][] basicAttributeData = {};

	JPanel enterDNEntryGrid = new JPanel(new GridLayout(1, 1));
	enterDNEntryGrid.add(new JLabel("DN Entry"));
	this.dnEntry = new JTextField("");
	enterDNEntryGrid.add(dnEntry);

	this.modificationsItemsTable = 
		new JTable(
			new PerfTestTableModel(
				new String[] { 
					LDAPGUIConstants.ATTRIBUTE_ID,
					LDAPGUIConstants.ATTRIBUTE_NAME, 
					LDAPGUIConstants.ATTRIBUTE_VALUE, 
					LDAPGUIConstants.ATTRIBUTE_MODIFICATION_TYPE,
					LDAPGUIConstants.ATTRIBUTE_EDITION
				}, 
				basicAttributeData
				)
			);

	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");

	this.editor = new ButtonEditor(new JCheckBox(), LDAPGUIConstants.ATTRIBUTE_EDIT);
	this.modificationsItemsTable.getColumn(LDAPGUIConstants.ATTRIBUTE_EDITION).setCellRenderer(new ButtonRenderer(LDAPGUIConstants.ATTRIBUTE_EDIT));
	this.modificationsItemsTable.getColumn(LDAPGUIConstants.ATTRIBUTE_EDITION).setCellEditor(this.editor);

	addInstructionPanel.add(
		new JScrollPane(modificationsItemsTable), 
		BorderLayout.CENTER
		);

	JPanel addAtributteGrid = new JPanel(new GridLayout(1, 3));
	addAtributteGrid.add(addButton);
	addAtributteGrid.add(deleteButton);

	addButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		addAction(e);
	    }
	});
	this.editor.getJButton().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		editAction(e);
	    }
	});
	deleteButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		deleteAction(e);
	    }
	});

	addInstructionPanel.add(addAtributteGrid, BorderLayout.NORTH);



	JPanel control = new JPanel();
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");

	okButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setSendData(true);
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

	this.add(enterDNEntryGrid, BorderLayout.NORTH);
	this.add(addInstructionPanel, BorderLayout.CENTER);
	this.add(control, BorderLayout.SOUTH);
    }


    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    @Override
    public IInstruction getCreatedInstruction() {

	LDAPInstructionUpdate ldapInstructionUpdate = new LDAPInstructionUpdate();

	Object[] attributesNames = 
		((PerfTestTableModel) this.modificationsItemsTable.getModel())
		.getValuesInColumn(1);
	Object[] attributesValues =
		((PerfTestTableModel) this.modificationsItemsTable.getModel())
		.getValuesInColumn(2);
	Object[] modificationsTypes =
		((PerfTestTableModel) this.modificationsItemsTable.getModel())
		.getValuesInColumn(3);

	if(attributesNames!= null && 
		attributesValues != null && 
		modificationsTypes != null &&
		attributesNames.length==attributesValues.length && 
		attributesValues.length==modificationsTypes.length) {

	    int nbModifiedAttribute = attributesNames.length;

	    ldapInstructionUpdate.setDNEntry(dnEntry.getText());

	    for(int i = 0; i < nbModifiedAttribute; i++) {

		String attributeName = (String) attributesNames[i];
		String attributeValues = (String) attributesValues[i];
		String modificationType = (String) modificationsTypes[i];

		if(LDAPInstructionUpdate.ADD_MODIFICATION.compareTo(modificationType)==0) {

		    ldapInstructionUpdate.addAttribute(attributeName, attributeValues); //TODO must split attributeValues ?!
		}
		else if(LDAPInstructionUpdate.REPLACE_MODIFICATION.compareTo(modificationType)==0) {
		    ldapInstructionUpdate.modifyAttribute(attributeName, attributeValues); //TODO must split attributeValues ?!
		}
		else if(LDAPInstructionUpdate.REMOVE_MODIFICATION.compareTo(modificationType)==0) {
		    ldapInstructionUpdate.removeAtribute(attributeName);
		}
	    }

	    return ldapInstructionUpdate;
	}

	return null;
    }

    private void setData(LDAPInstructionUpdate ldapInstructionUpdate) 
	    throws NamingException {

	ModificationItem[] modificationsItems = 
		ldapInstructionUpdate.getModificationItems();
	List<String> modifsTypes = ldapInstructionUpdate.getModificationsTypes();
	int modifTypeId = 0;

	this.dnEntry.setText(ldapInstructionUpdate.getDNEntry());


	for(ModificationItem mi : modificationsItems) {

	    Attribute attr = mi.getAttribute();

	    String attrName = attr.getID();
	    String attrValue = "";
	    String modifType = modifsTypes.get(modifTypeId++);
	    NamingEnumeration<?> values = attr.getAll();

	    while(values.hasMore()) {

		String value = (String)values.next();
		attrValue += values.hasMore() ? value+"," : value;
	    }

	    ((PerfTestTableModel) this.modificationsItemsTable.getModel())
	    .addRow(new Object[] { attrName, attrValue, modifType } );
	}
    }






    /* *********************************************************************
     * ACTIONS *************************************************************
     * *********************************************************************/

    public void addAction(ActionEvent e)  {

	ModificationAdder modificationAdder =
		new ModificationAdder(this.frame, "Add an modification", true);

	if(modificationAdder.showDialog()) {
	    ((PerfTestTableModel) modificationsItemsTable.getModel())
	    .addRow(new Object[] { "", "", modificationAdder.getSelectedModificationType() } );
	}
    }

    public void editAction(ActionEvent e) {

	int row = this.editor.getLastRow();
	String attributeNameInit = null;
	String attributeValueInit = null;

	if (row == -1)
	    return;

	attributeNameInit = (String) ((PerfTestTableModel) modificationsItemsTable.getModel()).getValueAt(row, 1);
	attributeValueInit = (String) ((PerfTestTableModel) modificationsItemsTable.getModel()).getValueAt(row, 2);

	ActionOnAttribute actionOnAttribute = new
		ActionOnAttribute(
			frame, 
			"Modify an instruction", 
			true,
			attributeNameInit,
			attributeValueInit);

	if(actionOnAttribute.showDialog()) {

	    ((PerfTestTableModel) modificationsItemsTable.getModel()).setValueAt(
		    actionOnAttribute.getAttributeName(), 
		    row, 
		    1);
	    ((PerfTestTableModel) modificationsItemsTable.getModel()).setValueAt(
		    actionOnAttribute.getAtributeValue(), 
		    row, 
		    2);

	    this.repaint();
	}
    }

    public void deleteAction(ActionEvent e) {
	int[] selectedLines = this.modificationsItemsTable.getSelectedRows();

	if (0 == selectedLines.length) {
	    return;
	}

	for (int i = selectedLines.length -1; i >=0 ; --i) {

	    ((PerfTestTableModel) modificationsItemsTable.getModel()).removeRow(selectedLines[i]);
	}
    }
}
