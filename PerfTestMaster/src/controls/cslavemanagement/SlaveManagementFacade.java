/**
 * 
 */
package controls.cslavemanagement;

import gui.interfaces.SlaveListener;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.AbstractTest;
import shared.Constants;
import tools.Factory;


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
	private List<SlaveListener> slaveListeners;
	
	public SlaveManagementFacade() {
		this.slave = new ArrayList<Slave>();
		this.TCPConnection = new ArrayList<TCPConnection>();
		this.dataBuffer = new ArrayList<DataBuffer>();
		this.slaveListeners = new ArrayList<SlaveListener>();
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

	public void detectSlaves(String ipAddress) {
		if (null == ipAddress || ipAddress.isEmpty())
			return;
		
		Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		//((0|1[0-9]{0,2}|2[0-9]?|2[0-4][0-9]|25[0-5]|[3-9][0-9]?)\.){3}(0|1[0-9]{0,2}|2[0-9]?|2[0-4][0-9]|25[0-5]|[3-9][0-9]?)
	    Matcher m = p.matcher(ipAddress);
		
		if (!m.find())
			return;
		
		String[] tab = ipAddress.split("\\.");		
		if (4 != tab.length)
			return;
		
		int level = 3;
		int index = 4;
		while ("0".equals(tab[--index])) {
			--level;
		}
		level++;

		String rootNetwork = "";
		for (int i = 0; i<level; ++i) {
			rootNetwork += tab[i] + ".";
		}
		System.out.println(rootNetwork);
		recursiveAdd(rootNetwork, level);		
	}
	
	private void recursiveAdd(final String rootNetwork, final int level) {
		
		//new Thread(new Runnable() {	
		//public void run() {
			String subNet = "";
			for (int i = 0; i<255; ++i) {
				subNet = rootNetwork + i;
				if (level == 3) {
					System.out.println(subNet);
					addSlave(subNet);
				} else {
					subNet += ".";
					int nextLevel = level +1;
					recursiveAdd(subNet, nextLevel);
				}
			}
		//}
		//}).start();
	}

	public boolean addSlave(String ipAddress) {
		if (null == ipAddress || ipAddress.isEmpty())
			return false;
		Iterator<Slave> iter = this.slave.iterator();
		while (iter.hasNext()) {
			if (ipAddress.equals(iter.next().getName())) {
				return false;
			}
		}
		TCPConnection tcpConnection = null;
		
		try {
			tcpConnection = Factory.createTCPConnection();
			tcpConnection.connect(ipAddress, Constants.SOCKET_COMMAND_PORT, Constants.SOCKET_OBJECT_PORT);
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
		
		updateAllSlaveListeners();
		
		return true;
	}

	public boolean sendTest(AbstractTest test) {		
		if (null == test)
			return false;
		Iterator<Slave> iter = this.slave.iterator();
		Slave slave = null;
		while (iter.hasNext()) {
			slave = iter.next();
			slave.getTCPClientSlave().send(test);
		}
		
		return true;
	}

	public int count() {
		return this.slave.size();
	}

	public boolean runAnotherSlave(String address, int port) {
		return false;
	}

	public boolean runSlaves(int count, List<String> addresses, int port) {
		if (count > this.slave.size())
			return false;
		
		String[] addressArray = addresses.toArray(new String[0]);
		int index = 0;
		
		Iterator<Slave> iter = this.slave.iterator();
		for (int i = 0; i < count; ++i) {
			iter.next().getTCPClientSlave().run(addressArray[index]);
			index = (index+1) % addressArray.length;
		}
		
		return true;
	}

	public boolean stop() {
		Iterator<Slave> iter = this.slave.iterator();
		while (iter.hasNext()) {
			iter.next().getTCPClientSlave().stop();
		}
		return false;
	}

	/**
	 * Removes the slave named name
	 * @param name a slave name
	 * @return true on success, false otherwise
	 */
	public boolean removeSlave(String name) {
		if (null == name || name.isEmpty())
			return false;
		Iterator<Slave> iter = this.slave.iterator();
		Slave slave = null;
		
		while (iter.hasNext()) {
			slave = iter.next();
			if (name.equals(slave.getAddress())) {
				slave.getTCPClientSlave().close();
				this.slave.remove(slave);
				this.TCPConnection.remove(slave.getTCPClientSlave());
				updateAllSlaveListeners();
				return true;
			}
		}
		updateAllSlaveListeners();
		return false;
	}

	public boolean hasAnotherReadySlave() {
		return false;
	}

	@Override
	public void addSlaveListener(SlaveListener slaveListener) {
		this.slaveListeners.add(slaveListener);
	}

	@Override
	public void removeSlaveListener(SlaveListener slaveListener) {
		this.slaveListeners.remove(slaveListener);
	}
	
	private void updateAllSlaveListeners() {
		Iterator<SlaveListener> iter = this.slaveListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateData();
		}
	}
	
}