/**
 * 
 */
package controls.cslavemanagement;

import controls.cslavemanagement.interfaces.ISlave;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Slave implements ISlave {

	private String name;
	private String address;
	private boolean deployed;
	private TCPConnection TCPClientSlave;
	
	public Slave() {
		this.deployed = false;
	}

	public String getName() {
		return getAddress();//name;
	}

	/**
	 * Modifies the name of the slave
	 * @param name a name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	/**
	 * Modifies the address
	 * @param address the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isDeployed() {
		return this.deployed;
	}

	/**
	 * Modifies the deployment state
	 * @param deployed a deployment state
	 */
	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}

	/**
	 * Returns the TCPConnection associated to the slave application
	 * @return the TCPConnection
	 */
	public TCPConnection getTCPClientSlave() {
		return TCPClientSlave;
	}

	/**
	 * Modifies the associated TCPConnection to slave application
	 * @param TCPClientSlave
	 */
	public void setTCPClientSlave(TCPConnection TCPClientSlave) {
		this.TCPClientSlave = TCPClientSlave;
		if (this.TCPClientSlave.getSlave() != this)
			this.TCPClientSlave.setSlave(this);
	}
}