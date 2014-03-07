/**
 * 
 */
package controls.cslavemanagement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TCPConnection {

	private Slave slave;
	private Socket objectConnection;
	private Socket commandConnection;
	
	public TCPConnection () {
		this.commandConnection = new Socket();
		this.objectConnection = new Socket();
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
		this.commandConnection.bind(new InetSocketAddress(address, commandPort));
		this.objectConnection.bind(new InetSocketAddress(address, objectPort));
	}

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
	
	/**
	 * Returns the TCP connection to slave which is used to transfer commands (or orders)
	 * @return a connection
	 */
	public Socket getCommandConnection() {
		return this.commandConnection;
	}
	
	/**
	 * Returns the TCP connection to slave which is used to transfer objects (or tests)
	 * @return a connection
	 */
	public Socket getObjectConnection() {
		return this.objectConnection;
	}
	
	/**
	 * Closes both command and object connections
	 * @return true if both are closed, false otherwise
	 */
	public boolean close() {
		try {
			this.objectConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.commandConnection.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Sends a test to the connected slave
	 * @param test a test to send
	 * @return true on success, false otherwise
	 */
	public boolean send(AbstractTest test) {
		if (this.objectConnection.isClosed() || this.commandConnection.isClosed())
			return false;

		// Change Slave state
		// SEND a command 
		// WAIT for response
		// SEND test
		// WAIT for response (command)
		// Change Slave state
		// return true;
		return false;
	}
}