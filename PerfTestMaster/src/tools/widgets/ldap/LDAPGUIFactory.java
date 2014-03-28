package tools.widgets.ldap;

import gui.panels.InstructionPanel;

import javax.swing.JFrame;

import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.ldap.instructions.LDAPInstructionCreate;
import controls.protocols.ldap.instructions.LDAPInstructionDelete;
import controls.protocols.ldap.instructions.LDAPInstructionRead;
import controls.protocols.ldap.instructions.LDAPInstructionUpdate;
import shared.interfaces.IInstruction;
import tools.GUIFactory;
import tools.widgets.InstructionAdder;
import tools.widgets.InstructionCreator;

public class LDAPGUIFactory extends GUIFactory {

    @Override
    public InstructionAdder createInstructionAdder(
	    JFrame parent, 
	    String title,
	    boolean modal) {

	return new LDAPInstructionAdder(parent, title, modal);
    }

    @Override
    public InstructionCreator createInstructionCreator(
	    JFrame parent,
	    String title, 
	    boolean modal, 
	    IInstruction instruction) throws Exception {


	if(instruction instanceof LDAPInstructionCreate) {
	    return new LDAPInstructionCreateCreator(
		    parent, 
		    title, 
		    modal, 
		    (LDAPInstructionCreate)instruction);
	}
	else if(instruction instanceof LDAPInstructionRead) {

	    LDAPInstructionRead ldapInstructionRead = 
		    (LDAPInstructionRead)instruction;

	    return new LDAPInstructionReadCreator(
		    parent, 
		    title, 
		    modal, 
		    ldapInstructionRead.getSearchBase(), 
		    ldapInstructionRead.getSearchFilter());
	}
	else if(instruction instanceof LDAPInstructionDelete) {
	    return new LDAPInstructionDeleteCreator(
		    parent, 
		    title, 
		    modal, 
		    ((LDAPInstructionDelete)instruction).getToDelete());
	}
	else if(instruction instanceof LDAPInstructionUpdate) {
	    return new LDAPInstructionUpdateCreator(
		    parent, 
		    title,
		    modal, 
		    (LDAPInstructionUpdate)instruction);
	}


	return null;
    }

    @Override
    public InstructionPanel instructionPanel(JFrame frame,
	    ITestPlanManagement testPlanManagement,
	    AbstractMonitoredTest abstractMonitoredTest) {
	// TODO Auto-generated method stub
	return null;
    }
}
