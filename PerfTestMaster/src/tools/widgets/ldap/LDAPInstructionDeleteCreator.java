package tools.widgets.ldap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controls.protocols.ldap.instructions.LDAPInstructionDelete;
import shared.interfaces.IInstruction;
import tools.widgets.InstructionCreator;

public class LDAPInstructionDeleteCreator extends InstructionCreator {

    private JTextField toDelete;
    
    public LDAPInstructionDeleteCreator(
	    JFrame parent, 
	    String title,
	    boolean modal,
	    String toDeleteInit) {
	
	super(parent, title, modal);
	this.setSize(300, 80);
	initPanel(toDeleteInit);

    }

    private void initPanel(String toDeleteInit) {

	JPanel toDeleteGrid = new JPanel(new GridLayout(1, 1));
	toDeleteGrid.add(new JLabel("To delete"));
	this.toDelete = new JTextField(toDeleteInit==null? "" : toDeleteInit);
	toDeleteGrid.add(this.toDelete);
	
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
	
	this.add(toDeleteGrid, BorderLayout.NORTH);
	this.add(control, BorderLayout.SOUTH);
    }

    @Override
    public IInstruction getCreatedInstruction() {
	LDAPInstructionDelete ldapInstructionDelete = 
		new LDAPInstructionDelete();
	
	ldapInstructionDelete.setToDelete(toDelete.getText());
	
	return ldapInstructionDelete;
    }
}
