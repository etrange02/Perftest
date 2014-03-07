/**
 * 
 */
package controls.cslavemanagement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
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
	private ITestPlanManagement testPlanManagement;
	
	public SlaveManagementFacade() {
		this.slave = new ArrayList<Slave>();
		this.TCPConnection = new ArrayList<TCPConnection>();
		this.dataBuffer = new ArrayList<DataBuffer>();
	}

	/**
	 * Returns the current associated TestPlanManagement
	 * @return the TestPlanManagement
	 */
	public ITestPlanManagement getTestPlanManagement() {
		return testPlanManagement;
	}

	/**
	 * Modifies the associated TestPlanManagement
	 * @param testPlanManagement a TestPlanManagement
	 */
	public void setTestPlanManagement(ITestPlanManagement testPlanManagement) {
		this.testPlanManagement = testPlanManagement;
		if (testPlanManagement.getSlaveManagement() != this) {
			testPlanManagement.setSlaveManagement(this);
		}
	}


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
		StringTokenizer tokens = new StringTokenizer(ipAddress);
		String[] tab = new String[4];
		int index = 0;
		
		tab[index++] = tokens.nextToken(".");
		while (tokens.hasMoreTokens() && index < 4) {
			tab[index++] = tokens.nextToken();
		}
		
		if (tokens.hasMoreTokens())
			return;
		
		int level = 3;
		while ("0".equals(tab[--index])) {
			--level;
		}
		level++;

		String rootNetwork = "";
		for (int i = 0; i<level; ++i) {
			rootNetwork += tab[i] + ".";
		}
		System.out.println(rootNetwork);
		recursiveAdd(rootNetwork, level, port);		
	}
	
	private void recursiveAdd(final String rootNetwork, final int level, final int port) {
		
		//new Thread(new Runnable() {	
		//public void run() {
			String subNet = "";
			for (int i = 0; i<255; ++i) {
				subNet = rootNetwork + i;
				if (level == 3) {
					System.out.println(subNet);
					addSlave(subNet, port);
				} else {
					subNet += ".";
					int nextLevel = level +1;
					recursiveAdd(subNet, nextLevel, port);
				}
			}
		//}
		//}).start();
	}

	public boolean addSlave(String ipAddress, int port) {
		Iterator<Slave> iter = this.slave.iterator();
		while (iter.hasNext()) {
			if (ipAddress.equals(iter.next().getName())) {
				return false;
			}
		}
		TCPConnection tcpConnection = null;
		
		try {
			tcpConnection = new TCPConnection();
			tcpConnection.bind(new InetSocketAddress(ipAddress, port));
		} catch (SocketException e) {
			System.out.println("Echec : " + ipAddress);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		Slave slave = new Slave();
		slave.setAddress(ipAddress);
		slave.setTCPClientSlave(tcpConnection);
		System.out.println("Ajout: " + ipAddress);
		
		synchronized (this.TCPConnection) {
			this.TCPConnection.add(tcpConnection);			
		}
		synchronized (this.slave) {
			this.slave.add(slave);			
		}
		return true;
	}

	public boolean sendTest(AbstractTest test) {
		return false;
	}

	public int count() {
		return this.slave.size();
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