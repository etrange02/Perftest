package tools;

import gui.panels.InstructionPanel;

import java.util.List;

import javax.swing.JFrame;

import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.interfaces.IInstruction;
import tools.widgets.InstructionAdder;
import tools.widgets.InstructionCreator;
import tools.widgets.TestCreator;
import tools.widgets.TestPlanCreator;
import tools.widgets.ldap.LDAPGUIFactory;

public abstract class GUIFactory {

    
    /* *********************************************************************
     * COMMONS METHODS *****************************************************
     * *********************************************************************/
    
    public static GUIFactory getGUIFactory(String protocolName) {
	
	if(shared.protocols.ldap.LDAPConstants.PROTOCOL_NAME.compareTo(
		protocolName)==0) {
	    
	    return new LDAPGUIFactory();
	}
	
	return null;
    }
    
    public static TestPlanCreator testPlanCreator(JFrame parent, String title, boolean modal, List<String> protocolList) {
	return new TestPlanCreator(parent, title, modal, protocolList);
    }

    public static TestCreator testCreator(JFrame parent, String title, boolean modal) {
	return new TestCreator(parent, title, modal);
    }

    
    
    /* *********************************************************************
     * IMPLEMENTATION SPECIFIC METHODS *************************************
     * *********************************************************************/

    public abstract InstructionAdder createInstructionAdder(	    
	    JFrame parent, 
	    String title, 
	    boolean modal) throws Exception;

    public abstract InstructionCreator createInstructionCreator(
	    JFrame parent, 
	    String title, 
	    boolean modal,
	    IInstruction instruction) throws Exception;

    public abstract InstructionPanel instructionPanel(
	    JFrame frame, 
	    ITestPlanManagement testPlanManagement, 
	    AbstractMonitoredTest abstractMonitoredTest);
}
