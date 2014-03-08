package cslave;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import shared.AbstractTest;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class ObjectTCPConnectionToMaster extends TCPConnectionToMaster {

	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	public ObjectTCPConnectionToMaster(int port) throws IOException {
		super(port);
	}

	


	
	
	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/
	
	@Override
	public void run() {
		Socket socket = null;
		ObjectInputStream objectInputStream = null;
		TestManager testManager = getTestManager();

		try {

			socket = getServerSocket().accept();
			objectInputStream = 
					new ObjectInputStream(socket.getInputStream());


					wait(); //Wait for testManager request

					testManager.setLastReceivedTest(
							(AbstractTest)objectInputStream.readObject());
					testManager.notify();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
