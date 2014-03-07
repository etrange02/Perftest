package controls.protocols.ldap;

import java.io.IOException;
import java.util.List;

import shared.AbstractInstruction;
import controls.ctestplanmanagement.TCPProxy;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAP_TCPProxy extends TCPProxy {

	/**
	 * Contains instructions that we have to complete
	 */
	private List<AbstractInstruction> instructions;
	
	/**
	 * The index of the current completed instruction
	 */
	int currentIndex;
	
	/**
	 * false => we are reading the request part of an instruction.
	 * true  => we are reading the response part of an instruction.
	 * Switched each time we handle tcpData.
	 */
	private boolean isAnswer;
	
	
	
	
	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	/**
	 * @param hostname the hostname where find the tested server.
	 * @param port the port to use to discuss with the tested server.
	 * @param instructions the list of isntructions to complete.
	 * @throws IOException
	 */
	LDAP_TCPProxy(
			String hostname, int port,
			List<AbstractInstruction>instructions) throws IOException {
		
		super(hostname, port);
		
		this.instructions = instructions;
		currentIndex = 0;
		isAnswer = false; //1rst time we logically going to read a request part.
	}

	
	
	
	
	
	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/
	
	/**
	 * @param tcpData the readed tcp data
	 */
	@Override
	public void handleTCPData(byte[] tcpData) {
		
		if(isAnswer == false) {
			
			instructions.get(currentIndex).setBinaryRequest(tcpData);
		}
		else {
			instructions.get(currentIndex).setBinaryResponse(tcpData);
			currentIndex++;
		}
		
		if(currentIndex >= instructions.size()) {
			//Send event that we have finish
		}
	}
}
