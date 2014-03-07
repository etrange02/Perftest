/**
 * 
 */
package controls.cslavemanagement;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TCPConnection extends Socket {

	private Slave slave;
	
	public TCPConnection () {
		super();
	}
	
	public TCPConnection (String address, int port) throws UnknownHostException, IOException {
		super(address, port);
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
}