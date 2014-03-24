package cslave.protocols.ldap;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import shared.Constants;
import shared.SendableTest;
import shared.interfaces.IInstruction;
import cslave.TCPConnectionToTestedServer;
import cslave.interfaces.IScenario;



/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPTCPConnectionToServer extends TCPConnectionToTestedServer {

	private InputStream in;
	private DataOutputStream out;



	/* *********************************************************************
	 * CONSTRUCTORS/INITS METHODS ******************************************
	 * *********************************************************************/

	public LDAPTCPConnectionToServer() {
		super();
	}
	
	@Override
	public void init(
			String hostname, 
			int port, 
			SendableTest test, 
			IScenario scenario) throws IOException {

		Socket clientSocket = null;

		super.init(hostname, port, test, scenario);
		clientSocket = super.getClientSocket();

		this.in = clientSocket.getInputStream();
		this.out = new DataOutputStream(clientSocket.getOutputStream());
	}

	

	/* *********************************************************************
	 * IMPORTANTS METHODS **************************************************
	 * *********************************************************************/
	
	@Override
	protected byte[] runInst(IInstruction instruction) throws Exception {
		
		byte[] answer = null;
		
		out.write(instruction.getBinaryRequest());
		answer = read(in);		
		return answer;
	}

	/**
	 * 
	 * @param in
	 * @throws Exception
	 */
	private byte[] read(InputStream in) throws Exception {

		byte[] tmpArray = new byte[Constants.TCP_DATAS_MAXLENGTH];
		byte[] resizedArray = null;//the resized array 
		int datasSize = -1;


		datasSize = in.read(tmpArray, 0, Constants.TCP_DATAS_MAXLENGTH);

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
