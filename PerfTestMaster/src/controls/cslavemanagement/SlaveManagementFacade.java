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

import shared.Constants;
import shared.Status;
import tools.Factory;
import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.ScalabilityTest;
import controls.ctestplanmanagement.WorkloadTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;


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
	private AbstractMonitoredTest monitoredTest;
	
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
	 * Returns the list of slaves
	 * @return a list
	 */
	public List<Slave> getSlave() {
		return this.slave;
	}

	/**
	 * Modifies the list of slaves
	 * @param slave a list
	 */
	public void setSlave(List<Slave> slave) {
		this.slave = slave;
	}

	/**
	 * Returns the list of TCP connections to slaves
	 * @return a list of TCP connections
	 */
	public List<TCPConnection> getTCPConnection() {
		return TCPConnection;
	}

	/**
	 * Modifies the list of TCP connections
	 * @param TCPConnection a list
	 */
	public void setTCPConnection(List<TCPConnection> TCPConnection) {
		this.TCPConnection = TCPConnection;
	}

	/**
	 * Returns the list of buffered data
	 * @return a list
	 */
	public List<DataBuffer> getDataBuffer() {
		return dataBuffer;
	}

	/**
	 * Modifies the list of DataBuffer
	 * @param dataBuffer a list
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
		this.updateMonitoringPanelWithMaxSlaveCount();
		
		return true;
	}

	@Override
	public boolean sendTest(AbstractMonitoredTest test) {
		if (null == test)
			return false;
		
		if (null != this.monitoredTest) {
			this.stop();
			this.monitoredTest.setStatus(Status.WAITING);
		}
		
		this.monitoredTest = test;
		Iterator<Slave> iter = this.slave.iterator();
		Slave slave = null;
		while (iter.hasNext()) {
			slave = iter.next();
			slave.getTCPClientSlave().send(test);
		}
		this.monitoredTest.setStatus(Status.DEPLOYED);		
		return true;
	}

	@Override
	public String getDeployedTestName() {
		if (null == this.monitoredTest)
			return "";
		return this.monitoredTest.getName();
	}

	public int count() {
		return this.slave.size();
	}

	public boolean runAnotherSlave(String address, int port) {
		if (null == this.monitoredTest || this.monitoredTest.getStatus() == Status.WAITING)
			return false;
		
		if (this.monitoredTest instanceof WorkloadTest) {
			Iterator<Slave> iter = this.slave.iterator();
			Slave slave = null;
			while (iter.hasNext()) {
				slave = iter.next();
				if (!slave.isRunning() && slave.isDeployed()) {
					slave.getTCPClientSlave().run(address, port);
					return true;
				}
			}
		}
		return false;
	}

	public boolean runSlaves(int count, List<String> addresses, int port) {
		if (null == this.monitoredTest || this.monitoredTest.getStatus() != Status.DEPLOYED)
			return false;
		
		if (count > this.slave.size())
			return false;
		
		if (this.monitoredTest instanceof ScalabilityTest) {
			String[] addressArray = addresses.toArray(new String[0]);
			int index = 0;
			
			Iterator<Slave> iter = this.slave.iterator();
			for (int i = 0; i < count && iter.hasNext(); ++i) {
				iter.next().getTCPClientSlave().run(addressArray[index], port);
				index = (index+1) % addressArray.length;
			}
		}		
		return true;
	}

	public boolean stop() {
		if (null == this.monitoredTest && this.monitoredTest.getStatus() == Status.RUNNING)
			return false;
		Iterator<Slave> iter = this.slave.iterator();
		while (iter.hasNext()) {
			iter.next().getTCPClientSlave().stop();
		}
		this.monitoredTest.setStatus(Status.WAITING);
		return true;
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
		boolean result = false;
		
		while (iter.hasNext() && !result) {
			slave = iter.next();
			if (name.equals(slave.getAddress())) {
				slave.getTCPClientSlave().close();
				this.slave.remove(slave);
				this.TCPConnection.remove(slave.getTCPClientSlave());
				result = true;
			}
		}
		updateAllSlaveListeners();
		this.updateMonitoringPanelWithMaxSlaveCount();
		return result;
	}

	public boolean hasAnotherReadySlave() {
		Iterator<Slave> iter = this.slave.iterator();
		while (iter.hasNext()) {
			if (!iter.next().isRunning())
				return true;
		}
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
	
	public void updateMonitoringPanelWithMaxSlaveCount() {
		Iterator<AbstractMonitoredTest> iter = this.getTestPlanManagement().getTestPlan().getTests().iterator();
		AbstractMonitoredTest test = null;
		while (iter.hasNext()) {
			test = iter.next();
			System.out.println("updateMonitoringPanelWithMaxSlaveCount");
			if (test instanceof ScalabilityTest) {
				this.getTestPlanManagement().setAffectedSlaves(test, ((ScalabilityTest) test).getAffectedSlaveCount());
			}
		}
	}
	
}