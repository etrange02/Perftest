package tools.widgets.ldap;

import gui.ButtonEditor;
import gui.ButtonRenderer;
import gui.PerfTestTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
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
import controls.protocols.ldap.instructions.LDAPInstructionCreate;

public class LDAPInstructionCreateCreator extends InstructionCreator {

    private JTextField dnEntry;
    private JTable basicAttributes;
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
    public LDAPInstructionCreateCreator(
	    JFrame parent, 
	    String title, 
	    boolean modal,
	    LDAPInstructionCreate ldapInstructionCreate) throws NamingException {

	super(parent, title, modal);

	this.frame = parent;
	this.setSize(500, 220);
	this.initPanel();
	setData(ldapInstructionCreate);
    }



    private void initPanel() {

	JPanel addInstructionPanel = new JPanel(new BorderLayout());
	Object[][] basicAttributeData = {};

	JPanel enterDNEntryGrid = new JPanel(new GridLayout(1, 1));
	enterDNEntryGrid.add(new JLabel("DN Entry"));
	this.dnEntry = new JTextField("");
	enterDNEntryGrid.add(dnEntry);

	this.basicAttributes = 
		new JTable(
			new PerfTestTableModel(
				new String[] { 
					LDAPGUIConstants.ATTRIBUTE_ID,
					LDAPGUIConstants.ATTRIBUTE_NAME, 
					LDAPGUIConstants.ATTRIBUTE_VALUE, 
					LDAPGUIConstants.ATTRIBUTE_EDITION
				}, 
				basicAttributeData
				)
			);

	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");

	this.editor = new ButtonEditor(new JCheckBox(), LDAPGUIConstants.ATTRIBUTE_EDIT);
	this.basicAttributes.getColumn(LDAPGUIConstants.ATTRIBUTE_EDITION).setCellRenderer(new ButtonRenderer(LDAPGUIConstants.ATTRIBUTE_EDIT));
	this.basicAttributes.getColumn(LDAPGUIConstants.ATTRIBUTE_EDITION).setCellEditor(this.editor);

	addInstructionPanel.add(
		new JScrollPane(basicAttributes), 
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
	LDAPInstructionCreate ldapInstructionCreate = new LDAPInstructionCreate();
	Object[] attributesNames = 
		((PerfTestTableModel) this.basicAttributes.getModel())
		.getValuesInColumn(1);
	Object[] attributesValues =
		((PerfTestTableModel) this.basicAttributes.getModel())
		.getValuesInColumn(2);

	if(attributesNames!= null && attributesValues != null && attributesNames.length==attributesValues.length) {

	    int nbAttribute = attributesNames.length;

	    ldapInstructionCreate.setDNEntry(dnEntry.getText());

	    for(int i = 0; i < nbAttribute; i++) {

		String attributeName = (String) attributesNames[i];
		String attributeValues = (String) attributesValues[i];
		
		for(String attributeValue : attributeValues.split(",")){

		    ldapInstructionCreate.addAttribute(attributeName, attributeValue);
		}
	    }

	    return ldapInstructionCreate;
	}

	return null;
    }

    private void setData(LDAPInstructionCreate ldapInstructionCreate) 
	    throws NamingException {

	NamingEnumeration<Attribute> basicAttributes =
		ldapInstructionCreate.getBasicAttributes().getAll();


	this.dnEntry.setText(ldapInstructionCreate.getDNEntry());


	while(basicAttributes.hasMore()){

	    Attribute attr = basicAttributes.next();
	    String attrName = attr.getID();
	    String attrValue = "";
	    NamingEnumeration<?> values = attr.getAll();

	    while(values.hasMore()) {

		String value = (String)values.next();
		attrValue += values.hasMore() ? value+"," : value;		
	    }

	    ((PerfTestTableModel) this.basicAttributes.getModel())
	    .addRow(new Object[] { attrName, attrValue } );
	}
    }






    /* *********************************************************************
     * ACTIONS *************************************************************
     * *********************************************************************/

    public void addAction(ActionEvent e)  {

	ActionOnAttribute actionOnAttribute = new
		ActionOnAttribute(
			frame, 
			"Add an attribute", 
			true,
			null,
			null);

	if(actionOnAttribute.showDialog()) {

	    String attributeName = actionOnAttribute.getAttributeName();
	    String attributeValue = actionOnAttribute.getAtributeValue();

	    ((PerfTestTableModel) basicAttributes.getModel())
	    .addRow(new Object[] { attributeName, attributeValue } );
	}
    }

    public void editAction(ActionEvent e) {

	int row = this.editor.getLastRow();
	String attributeNameInit = null;
	String attributeValueInit = null;

	if (row == -1)
	    return;

	attributeNameInit = (String) ((PerfTestTableModel) basicAttributes.getModel()).getValueAt(row, 1);
	attributeValueInit = (String) ((PerfTestTableModel) basicAttributes.getModel()).getValueAt(row, 2);

	ActionOnAttribute actionOnAttribute = new
		ActionOnAttribute(
			frame, 
			"Modify an attribute", 
			true,
			attributeNameInit,
			attributeValueInit);

	if(actionOnAttribute.showDialog()) {

	    ((PerfTestTableModel) basicAttributes.getModel()).setValueAt(
		    actionOnAttribute.getAttributeName(), 
		    row, 
		    1);
	    ((PerfTestTableModel) basicAttributes.getModel()).setValueAt(
		    actionOnAttribute.getAtributeValue(), 
		    row, 
		    2);

	    this.repaint();
	}
    }

    public void deleteAction(ActionEvent e) {
	int[] selectedLines = this.basicAttributes.getSelectedRows();
	
	if (0 == selectedLines.length) {
	    return;
	}

	for (int i = selectedLines.length -1; i >=0 ; --i) {
	    
	    ((PerfTestTableModel) basicAttributes.getModel()).removeRow(selectedLines[i]);
	}
    }
} 
