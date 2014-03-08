package cslave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class CommandTCPConnectionToMaster extends TCPConnectionToMaster {

	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	public CommandTCPConnectionToMaster(int port) throws IOException {
		super(port);
	}



	
	
	
	/* *********************************************************************
	 * IMPORTANTS METHODS **************************************************
	 * *********************************************************************/
	
	@Override
	public void run() {
		Socket socket = null;
		BufferedReader bufferedReader = null;
		TestManager testManager = getTestManager();
		
		try {

			socket = getServerSocket().accept();
			bufferedReader = 
					new BufferedReader(
							new InputStreamReader(socket.getInputStream()));


			wait(); //Wait for testManager request
			
			testManager.setLastReceivedCommand(bufferedReader.readLine());
			testManager.notify();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
