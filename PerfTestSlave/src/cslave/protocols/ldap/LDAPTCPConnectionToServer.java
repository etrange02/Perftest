package cslave.protocols.ldap;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import shared.interfaces.IInstruction;
import shared.interfaces.ITest;
import cslave.Response;
import cslave.TCPConnectionToTestedServer;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITCPConnectionToTestedServer;



/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPTCPConnectionToServer 
extends TCPConnectionToTestedServer
implements ITCPConnectionToTestedServer {

    private static final int BERDATAS_MAXLENGTH = 9998;
    
    private InputStream in;
    private DataOutputStream out;
    
    private boolean isRunning;
    private ITest test;
    private IScenario scenario;
    private boolean initialized;

    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS/CLEANS METHODS *****************************************
     * *********************************************************************/

    public LDAPTCPConnectionToServer() {
	isRunning = false;
	
	test = null;
	scenario = null;
	initialized = false;
    }

    public 
    void init(String hostname, int port, ITest test, IScenario scenario) 
	    throws IOException {

	Socket clientSocket = null;
	
	super.init(hostname, port);

	clientSocket = getClientSocket();
	
	this.in = clientSocket.getInputStream();
	this.out = new DataOutputStream(clientSocket.getOutputStream());
	this.test = test;
	this.scenario = scenario;
	this.initialized = true;
    }


    
    
    
    
    /* *********************************************************************
     * IMPORTANT METHODS ***************************************************
     * *********************************************************************/

    @Override
    public void run() {

	if(initialized) {
	    
	    List<IInstruction> instructions = test.getInstructions();
	    int nbInstructions = instructions.size();
	    int nextInstructionPos = 0;
	    
	    try {

		while(true) {
		    
		    IInstruction nextInstruction = 
			    instructions.get(nextInstructionPos);
		    Response response = new Response();



		    response.setExpectedBinaryResponse(
			    nextInstruction.getBinaryResponse());
		        
		    
		    wait();
		    isRunning = true;

		    
		    response.setSendTimeMillis(System.currentTimeMillis());
		    out.write(nextInstruction.getBinaryRequest());
		    response.setServerBinaryResponse(read(in));
		    response.setReceptionTimeMillis(System.currentTimeMillis());
		    scenario.addResponse(response);	    
		    
	
		    nextInstructionPos = nextInstructionPos%nbInstructions;
		    isRunning = false;
		}
	    }
	    catch(Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public boolean isRunning() {
	return isRunning;
    }
    
    /**
     * 
     * @param in
     * @throws Exception
     */
    private byte[] read(InputStream in) throws Exception {

	byte[] tmpArray = new byte[BERDATAS_MAXLENGTH];
	byte[] resizedArray = null;//the resized array 
	int datasSize = -1;


	datasSize = in.read(tmpArray, 0, BERDATAS_MAXLENGTH);

	if(datasSize > 0) {
	    resizedArray = new byte[datasSize];
	    System.arraycopy(
		    tmpArray, 0, 
		    resizedArray, 0, 
		    datasSize);	
	}
	else if(datasSize != -1){ //-1 means EOF: end of connection
	    throw new Exception("Error while reading BER datas");
	}


	return resizedArray;
    }
}
