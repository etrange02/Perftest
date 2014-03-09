package cslave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
		OutputStream outputStream = null;
		TestManager testManager = getTestManager();

		try {
			socket = getServerSocket().accept();
			bufferedReader = 
					new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
			outputStream = socket.getOutputStream();
			
			
			do {
				
				wait(); //Wait testManager to ask for the next command
				testManager.setLastReceivedCommand(bufferedReader.readLine());
				testManager.notify();
				
				wait(); //Wait testManager to give the next answer
				outputStream.write(
						testManager.getNextCommandToSend().getBytes());
			}
			while(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
