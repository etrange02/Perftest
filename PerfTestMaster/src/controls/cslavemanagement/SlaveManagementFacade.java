/**
 * 
 */
package controls.cslavemanagement;

import java.util.List;

import controls.cslavemanagement.interfaces.ISlaveManagement;
import shared.AbstractTest;


/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class SlaveManagementFacade implements ISlaveManagement {

	private List<Slave> slave;
	private List<TCPConnection> TCPConnection;
	private List<DataBuffer> dataBuffer;

	/** 
	 * @return slave
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<Slave> getSlave() {
		return this.slave;
	}

	/** 
	 * @param slave slave � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setSlave(List<Slave> slave) {
		this.slave = slave;
	}

	/** 
	 * @return TCPConnection
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<TCPConnection> getTCPConnection() {
		return TCPConnection;
	}

	/** 
	 * @param TCPConnection TCPConnection � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTCPConnection(List<TCPConnection> TCPConnection) {
		this.TCPConnection = TCPConnection;
	}

	/** 
	 * @return dataBuffer
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<DataBuffer> getDataBuffer() {
		return dataBuffer;
	}

	/** 
	 * @param dataBuffer dataBuffer � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDataBuffer(List<DataBuffer> dataBuffer) {
		this.dataBuffer = dataBuffer;
	}

	public void detectSlaves(String ipAddress, int port) {
	}

	public boolean addSlave(String ipAddress, int port) {
		return false;
	}

	public boolean sendTest(AbstractTest test) {
		return false;
	}

	public int count() {
		return 0;
	}

	public boolean runAnotherSlave(String address, int port) {
		return false;
	}

	public boolean runSlaves(int count, String address, int port) {
		return false;
	}

	public boolean stop() {
		return false;
	}

	/**
	 * Removes the slave named name
	 * @param name a slave name
	 * @return true on success, false otherwise
	 */
	public boolean removeSlave(String name) {
		return false;
	}

	public boolean hasAnotherReadySlave() {
		return false;
	}
	
}