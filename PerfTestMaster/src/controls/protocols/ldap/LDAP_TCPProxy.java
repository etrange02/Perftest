package controls.protocols.ldap;

import java.io.IOException;

import shared.interfaces.ITest;
import controls.ctestplanmanagement.TCPProxy;
import controls.protocols.AbstractClientForBlankTest;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAP_TCPProxy extends TCPProxy {

    /**
     * Contains instructions that we have to complete
     */
    private ITest test;



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param hostname the hostname where find the tested server.
     * @param port the port to use to discuss with the tested server.
     * @param instructions the list of instructions to complete.
     * @throws IOException
     */
    LDAP_TCPProxy(String hostname, int port, ITest test) throws IOException {

	super(hostname, port);
	this.test = test;
    }



    /* *********************************************************************
     * IMPORTANT METHODS ***************************************************
     * *********************************************************************/

    @Override
    public void handleTCPData(byte[] tcpData) {

	AbstractClientForBlankTest clientForBlankTest = 
		getTestPlanManagementFacade()
		.getClientForBlankTest();
	int currentInstructionIndex = 
		clientForBlankTest.getCurrentInstructionIndex();

	if(clientForBlankTest.hasNext()) {
	    if(clientForBlankTest.toHandle()) {

		if(tcpData==null) {
		    System.out.println("LDAP_TCPProxy.handleTCPData(): null arg");
		}
		else {
		    System.out.print("LDAP_TCPProxy.handleTCPData(): [");
		    for(int i = 0; i <  tcpData.length; i++) {
			System.out.print(tcpData[i]);
		    }
		    System.out.println("]");
		}

		byte[] request = test
			.getInstructions()
			.get(currentInstructionIndex)
			.getBinaryRequest();

		if(request==null||request.length==0){ //TODO ensure that this test is the one to do

		    System.out.println("LDAP_TCPProxy.handleTCPData(): handled as request");

		    test
		    .getInstructions()
		    .get(currentInstructionIndex)
		    .setBinaryRequest(tcpData);
		}
		else {

		    System.out.println("LDAP_TCPProxy.handleTCPData(): handled as response");

		    test
		    .getInstructions()
		    .get(currentInstructionIndex)
		    .setBinaryResponse(tcpData);
		}
	    }
	}
    }
}
