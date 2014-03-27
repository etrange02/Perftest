package tools.widgets.ldap;

import javax.swing.JFrame;

import controls.protocols.ldap.LDAPProtocolParser;
import tools.widgets.InstructionAdder;

public class LDAPInstructionAdder extends InstructionAdder {

    public LDAPInstructionAdder(JFrame parent, String title, boolean modal) {
	super(parent, title, modal);
    }

    @Override
    public String[] getInstructionsType() {
	return new String[]
		{ 
		LDAPProtocolParser.CREATE_TYPE, 
		LDAPProtocolParser.READ_TYPE, 
		LDAPProtocolParser.UPDATE_TYPE, 
		LDAPProtocolParser.DELETE_TYPE 
		}; 
    }
}
