package controls.protocols.ldap;

import gui.panels.AbstractTestPlanPanel;
import gui.panels.protocols.ldap.LDAPTestPlanPanel;

import java.io.IOException;

import shared.AbstractInstruction;
import shared.interfaces.ITest;
import controls.ctestplanmanagement.AbstractTestPlan;
import controls.ctestplanmanagement.ProtocolParser;
import controls.ctestplanmanagement.TCPProxy;
import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import controls.protocols.AbstractClientForBlankTest;
import controls.protocols.ldap.instructions.LDAPInstructionCreate;
import controls.protocols.ldap.instructions.LDAPInstructionDelete;
import controls.protocols.ldap.instructions.LDAPInstructionRead;
import controls.protocols.ldap.instructions.LDAPInstructionUpdate;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPProtocolParser extends ProtocolParser {

    public static final String CREATE_TYPE = "Create";
    public static final String READ_TYPE = "Read";
    public static final String UPDATE_TYPE = "Update";
    public static final String DELETE_TYPE = "Delete";

    
    
    @Override
    public String getProtocolName() {
	return shared.protocols.ldap.LDAPConstants.PROTOCOL_NAME;
    }

    @Override
    public AbstractInstruction createNewInstruction(String instructionType) {
	
	
	if(CREATE_TYPE.compareTo(instructionType)==0) {
	    return new LDAPInstructionCreate();
	}
	else if(READ_TYPE.compareTo(instructionType)==0){
	    return new LDAPInstructionRead();
	}
	else if(UPDATE_TYPE.compareTo(instructionType)==0) {
	    return new LDAPInstructionUpdate();
	}
	else if(DELETE_TYPE.compareTo(instructionType)==0) {
	    return new LDAPInstructionDelete();
	}
	
	
	return null;
    }

    @Override
    public AbstractTestPlan createNewPlanTest() {
	return new LDAPPlanTest();
    }

    @Override
    public TCPProxy createNewTCPProxy(
	    String hostname, int port,
	    ITest test) throws IOException {

	return new LDAP_TCPProxy(hostname,port,test);
    }

    @Override
    public AbstractClientForBlankTest createNewClientForBlankTest(
	    ITestPlan testPlan, String hostname, ITest test) {

	if(testPlan instanceof LDAPPlanTest) {
	    return new LDAPClientForBlankTest(
		    (LDAPPlanTest)testPlan, hostname, test);
	}

	return null;
    }

    @Override
    public AbstractTestPlan readJSONFileTestPlan(Object values) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractInstruction readJSONStringInstruction(Object values) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getDefaultProtocolPort() {
	return LDAPConstants.LDAP_DEFAULT_PORT;
    }

    @Override
    public AbstractTestPlanPanel createNewTestPlanPanel(ITestPlanManagement testPlanManagement) {
	return new LDAPTestPlanPanel(testPlanManagement);
    }
}
