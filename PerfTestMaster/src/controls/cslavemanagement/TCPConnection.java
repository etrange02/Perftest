/**
 * 
 */
package controls.cslavemanagement;

import java.io.IOException;
import java.net.UnknownHostException;

import shared.AbstractTest;
import shared.Constants;
import shared.DataBuffer;
import shared.SendableTest;
import shared.tcp.TCPObjectClient;
import shared.tcp.TCPStringClient;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 * 
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.1
 */
public class TCPConnection {

	private Slave slave;
	private TCPStringClient commandTCPConnectionToSlave;
	private TCPObjectClient objectTCPConnectionToSlave;
	private DataBuffer lastResponsePack;





	/* *********************************************************************
	 * CONSTRUCTORS/CLEANS *************************************************
	 * *********************************************************************/

	public TCPConnection () throws IOException {

		this.slave = new Slave();
		this.commandTCPConnectionToSlave = new TCPStringClient();
		this.objectTCPConnectionToSlave = new TCPObjectClient();
		this.lastResponsePack = null;
	}

	public TCPConnection (String address, int commandPort, int objectPort) throws UnknownHostException, IOException {
		this();
		this.connect(address, commandPort, objectPort);
	}

	public void finalize() {
		this.close();
	}

	/**
	 * Opens connections (command and object) to a slave
	 * @param address network address of the slave
	 * @param commandPort the port for sending commands
	 * @param objectPort the port for sending objects
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect(String address, int commandPort, int objectPort) throws UnknownHostException, IOException {

		this.commandTCPConnectionToSlave
		.startStringConnection(address, commandPort);
		this.objectTCPConnectionToSlave
		.startObjectConnection(address, objectPort);
	}


	/**
	 * Closes both command and object connections
	 * @return true if both are closed, false otherwise
	 */
	public boolean close() {

		System.out.println("TCPConnection.close(): BEGIN");
		
		try {

			commandTCPConnectionToSlave.close();
			objectTCPConnectionToSlave.close();

		} catch (IOException e) {

			return false;
		}


		return true;
	}




	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	/**
	 * Returns the associated slave
	 * @return a slave
	 */
	public Slave getSlave() {
		return slave;
	}

	/**
	 * Modifies the associated slave. If the concerned slave has no reference to
	 * this instance, reference is made.
	 * @param slave a slave
	 */
	public void setSlave(Slave slave) {
		this.slave = slave;
		if (slave.getTCPClientSlave() != this) {
			slave.setTCPClientSlave(this);
		}		
	}

	public DataBuffer getLastResponsePack() {
		return lastResponsePack;
	}





	/* *********************************************************************
	 * IMPORTANTS **********************************************************
	 * *********************************************************************/

	/**
	 * Sends a test to the connected slave
	 * @param test a test to send
	 * @return true on success, false otherwise
	 */
	public boolean send(AbstractTest test, String protocolName) {

		System.out.println("TCPConnection.send(...)");

		try {

			if (this.objectTCPConnectionToSlave.isClosed() ||
					this.commandTCPConnectionToSlave.isClosed()) {
				return false;
			}

			// Change Slave state
			slave.setDeployed(false);
			slave.setRunning(false);

			// SEND a command
			commandTCPConnectionToSlave.write(
					Constants.DEPLOY_CMD+"/"+protocolName+"/\n");

			//WAIT for response
			if(isAnswer(
					commandTCPConnectionToSlave.read(), 
					Constants.OK_RESP+"/")) {

				System.out.println("TCPConnection.deployTest(): sending test "+test.getClass());

				//SEND test
				objectTCPConnectionToSlave.write(
						new SendableTest(
								test.getInstructionDelay(), 
								test.getInstructions())
						);


				if(isAnswer(
						commandTCPConnectionToSlave.read(), 
						Constants.OK_RESP+"/")) {

					//CHANGE slave status
					slave.setDeployed(true);
				}
				else {

					stop();
					return false;
				}
			}
			else {

				stop();
				return false;
			}


			return true;
		}
		catch(Exception e) {
			stop();
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Sends a Stop message to stop the test processing into the slave
	 * @return true on success, false otherwise
	 */
	public boolean stop() {

		if (this.objectTCPConnectionToSlave.isClosed() ||
				this.commandTCPConnectionToSlave.isClosed()) {
			return false;
		}

		// SEND a command
		try {

			commandTCPConnectionToSlave.write(Constants.STOP_CMD+"/\n");

			//WAIT for response
			if(isAnswer(
					commandTCPConnectionToSlave.read(), 
					Constants.OK_RESP+"/")){

				System.out.println("TCPConnectionstop(): OK/ received");
				
				slave.setRunning(false);
				slave.setDeployed(false);

				return true;
			}
			else {
				System.out.println("TCPConnectionstop(): OK/ received not received");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


		// Change Slave state
		// return true;
		return false;
	}

	/**
	 * Sends a run command to start the test scenario
	 * @param address the address of the tested server
	 * @param port the port of the tested protocol
	 * @return true on success, false otherwise
	 */
	public boolean run(String address, int port) {

		if (this.objectTCPConnectionToSlave.isClosed() ||
				this.commandTCPConnectionToSlave.isClosed()) {
			return false;
		}


		try {

			// SEND a command
			commandTCPConnectionToSlave.write(
					Constants.RUN_CMD+"/"+address+"/"+port+"/\n");

			// WAIT for a response
			if(isAnswer(
					commandTCPConnectionToSlave.read(), 
					Constants.OK_RESP+"/")) {

				// Change Slave state
				slave.setRunning(true);

//				try {
//					System.out.println("TCPConnection.run(): ERASEME");
//					DelaysAveragesGraph cg = new DelaysAveragesGraph(1);
//					DelaysInfosProvider cdp = cg.getDelaysInfosProvider();
//					
//					for(int i = 0; i < 3; i++) {
//						Thread.sleep(3000);
//						result();
//						cdp.addInfos(lastResponsePack);
//					}
//					System.out.println("TCPConnection.run(): now display");
//					new FrameCapacityGraph(cg);
//					Thread.sleep(5000);
//				}
//				catch(Exception e) {
//
//				}

				return true;
			}
			else {
				stop();
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Get test responses from server. Use getLastPackageResponse();
	 * @return true on success, false otherwise
	 */
	public boolean result() {

		try {

			lastResponsePack = null;

			// SEND a command
			commandTCPConnectionToSlave.write(Constants.RESULT_CMD+"/\n");

			// WAIT for a response
			if(isAnswer(
					commandTCPConnectionToSlave.read(), 
					Constants.OK_RESP+"/")) {

				lastResponsePack = (DataBuffer) 
						objectTCPConnectionToSlave.read();

				return true;

			} else {
				stop();
				return false;
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean isAnswer(String actualAnswer, String wantedAnswer) {

		if(actualAnswer==null&&wantedAnswer==null) {
			return true;
		}
		else if (actualAnswer==null||wantedAnswer==null) {
			return false;
		}
		else {
			return actualAnswer.compareTo(wantedAnswer)==0;
		}
	}
}