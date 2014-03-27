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

import controls.protocols.ldap.instructions.LDAPInstructionRead;
import shared.interfaces.IInstruction;
import tools.widgets.InstructionCreator;

public class LDAPInstructionReadCreator extends InstructionCreator {

    private JTextField searchBase;
    private JTextField searchFilter;
    
    
    public LDAPInstructionReadCreator(
	    JFrame parent, 
	    String title, 
	    boolean modal,
	    String searchBaseInit,
	    String searchFilterInit) {
	
	super(parent, title, modal);
	this.setSize(300, 100);
	initPanel(searchBaseInit, searchFilterInit);
    }
    
    private void initPanel(String searchBaseInit, String searchFilterInit) {
	
	JPanel searchPanel = new JPanel(new BorderLayout());
	
	JPanel searchBaseGrid = new JPanel(new GridLayout(1, 1));
	searchBaseGrid.add(new JLabel("Search base"));
	this.searchBase = new JTextField(searchBaseInit==null? "" : searchBaseInit);
	searchBaseGrid.add(this.searchBase);
	
	JPanel searchFilterGrid = new JPanel(new GridLayout(1, 1));
	searchFilterGrid.add(new JLabel("Search filter"));
	this.searchFilter = new JTextField(searchFilterInit==null? "" : searchFilterInit);
	searchFilterGrid.add(searchFilter);
	
	
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
	
	searchPanel.add(searchBaseGrid, BorderLayout.NORTH);
	searchPanel.add(searchFilterGrid, BorderLayout.SOUTH);
	
	this.add(searchPanel, BorderLayout.NORTH);
	this.add(control, BorderLayout.SOUTH);
    }

    @Override
    public IInstruction getCreatedInstruction() {
	
	LDAPInstructionRead ldapInstructionRead = new LDAPInstructionRead();
	
	ldapInstructionRead.setSearchBase(searchBase.getText());
	ldapInstructionRead.setSearchFilter(searchFilter.getText());
	
	return ldapInstructionRead;
    }
}
