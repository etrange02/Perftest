package tools.widgets.ldap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controls.protocols.ldap.instructions.LDAPInstructionUpdate;

public class ModificationAdder extends JDialog {

    private JComboBox<Object> modificationType;
    private boolean sendData;

    

    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZERS *******************************************
     * *********************************************************************/
    
    public ModificationAdder(JFrame parent, String title, boolean modal) {

	super(parent,title,modal);

	this.setSize(300, 90);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	initPanel();
    }

    private void initPanel() {

	JPanel selectModificationTypeGrid = new JPanel(new GridLayout(1, 1));

	selectModificationTypeGrid.add(new JLabel("Select an modification type"));
	this.modificationType = new JComboBox<Object>(
		new String[]
			{
			LDAPInstructionUpdate.ADD_MODIFICATION,
			LDAPInstructionUpdate.REPLACE_MODIFICATION,
			LDAPInstructionUpdate.REMOVE_MODIFICATION,
			}
		);
	selectModificationTypeGrid.add(this.modificationType);


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

	
	this.add(selectModificationTypeGrid, BorderLayout.CENTER);
	this.add(control, BorderLayout.SOUTH);
    }
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    public String getSelectedModificationType() {
	return this.modificationType.getSelectedItem().toString();
    }
    
    
    
    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/

    /**
     * Show the dialog
     * @return true if the user clicks on OK
     */
    public boolean showDialog() {
	this.sendData = false;
	this.setVisible(true);
	return this.sendData;
    }
}
