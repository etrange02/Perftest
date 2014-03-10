/**
 * 
 */
package controls.ctestplanmanagement;

import gui.interfaces.TestListener;
import gui.interfaces.TestPlanListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.IInstruction;
import tools.Factory;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPlanManagementFacade implements ITestPlanManagement {

	private AbstractTestPlan testPlan;
	private List<ProtocolParser> protocolParser;
	private ProtocolParser usedProtocolParser;
	private TCPProxy TCPProxy;
	private ISlaveManagement slaveManagement;
	private List<TestPlanListener> planTestListenerList;
	private List<TestListener> testListenerList;
	
	public TestPlanManagementFacade() {
		this.protocolParser = new ArrayList<ProtocolParser>();
		this.planTestListenerList = new ArrayList<TestPlanListener>();
		this.testListenerList = new ArrayList<TestListener>();
	}
	
	/**
	 * Modifies the associated SlaveManagement
	 * @param slaveManagement a SlaveManagement
	 */
	public void setSlaveManagement(ISlaveManagement slaveManagement) {
		this.slaveManagement = slaveManagement;
		if (slaveManagement.getTestPlanManagement() != this) {
			slaveManagement.setTestPlanManagement(this);
		}
	}
	
	/**
	 * Returns the current SlaveManagement
	 * @return the associated SlaveManagement
	 */
	public ISlaveManagement getSlaveManagement() {
		return this.slaveManagement;
	}

	/**
	 * Returns the associated test plan
	 * @return a test plan or null
	 */
	public AbstractTestPlan getTestPlan() {
		return testPlan;
	}

	/**
	 * Modifies the associated test plan
	 * @param testPlan a test plan
	 */
	public void setTestPlan(AbstractTestPlan testPlan) {
		this.testPlan = testPlan;
	}
	
	/**
	 * Returns the list of protocols parsers
	 * @return a list
	 */
	public List<ProtocolParser> getProtocolParser() {
		return this.protocolParser;
	}

	/**
	 * Modifies the list of protocols parsers
	 * @param protocolParser a list
	 */
	public void setProtocolParser(List<ProtocolParser> protocolParser) {
		this.protocolParser = protocolParser;
	}

	/**
	 * returns the current used protocol parser
	 * @return a protocol parser or null
	 */
	public ProtocolParser getUsedProtocolParser() {
		return usedProtocolParser;
	}

	/**
	 * Modifies the used protocol parser
	 * @param usedProtocolParser an existing protocol parser. Do nothing if null
	 */
	public void setUsedProtocolParser(ProtocolParser usedProtocolParser) {
		this.usedProtocolParser = usedProtocolParser;
	}

	/**
	 * Useless ?? Returns the TCPProxy implemented by the selected protocol
	 * @return a TCPProxy or null
	 */
	public TCPProxy getTCPProxy() {
		return TCPProxy;
	}

	/**
	 * Useless ?? Modifies the TCPProxy
	 * @param TCPProxy a TCPProxy
	 */
	public void setTCPProxy(TCPProxy TCPProxy) {
		this.TCPProxy = TCPProxy;
	}

	public IInstruction addNewInstruction(String testName, String instructionName) {
		if (null == getTestPlan())
			return null;
		Iterator<AbstractMonitoredTest> iter = this.getTestPlan().getTests().iterator();
		boolean continu = true;
		AbstractMonitoredTest test = null;
		while (iter.hasNext() && continu) {
			test = iter.next();
			if (testName.equals(test.getName())) {
				continu = false;
			}
		}
		if (false == continu && null != this.usedProtocolParser) {
			return this.usedProtocolParser.createNewInstruction();
		}
		return null;
	}

	public ITestPlan addNewTestPlan(String protocolName) {
		if (null != getTestPlan())
			return null;
		Iterator<ProtocolParser> iter = this.protocolParser.iterator();
		ProtocolParser parser = null;
		boolean continu = true;
		while (iter.hasNext() && continu) {
			parser = iter.next();
			if (protocolName.equals(parser.getProtocolName())) {
				this.usedProtocolParser = parser;
				continu = false;
			}
		}
		if (null != this.usedProtocolParser) {
			setTestPlan(this.usedProtocolParser.createNewPlanTest());
			updatePlanTestNameList(this.usedProtocolParser.getProtocolName());
		}
		return getTestPlan();
	}

	public ScalabilityTest addNewScalabilityTest(String testName) {
		ScalabilityTest test = null;
		if (canAddNewMonitoredTest(testName)) {
			test = Factory.createScalabilityTest(testName);
			this.testPlan.getTests().add(test);
			addScalabilityTestListenerList(testName);
		}
		return test;
	}

	public WorkloadTest addNewWorkloadTest(String testName) {
		WorkloadTest test = null;
		if (canAddNewMonitoredTest(testName)) {
			test = Factory.createWorkloadTest(testName);
			this.testPlan.getTests().add(test);
			addWorkloadTestListener(testName);
		}
		return test;
	}
	
	/**
	 * Indicates if it is possible to add a monitored test
	 * @param testName a test name
	 * @return true if the name is available and a test plan exist, false otherwise
	 */
	private boolean canAddNewMonitoredTest(String testName) {
		if (null == this.testPlan || null == this.usedProtocolParser)
			return false;
		Iterator<AbstractMonitoredTest> iter = this.testPlan.getTests().iterator();
		while (iter.hasNext()) {
			if (testName.equals(iter.next().getName()))
				return false;
		}
		return true;
	}

	public void addProtocolParser(ProtocolParser protocolParser) {
		Iterator<ProtocolParser> iter = this.protocolParser.iterator();
		while (iter.hasNext()) {
			if (iter.next().isImplementedProtocol(protocolParser.getProtocolName()))
				return;
		}
		this.protocolParser.add(protocolParser);
	}

	public void addTarget(String target) {
		if (null == this.testPlan)
			return;
		if (this.testPlan.getTargets().contains(target)) {
			this.testPlan.getTargets().add(target);
		}
	}

	public boolean deployTest(String name) {
		if (null == getSlaveManagement())
			return false;
		Iterator<AbstractMonitoredTest> iter = this.getTestPlan().getTests().iterator();
		boolean continu = true;
		AbstractMonitoredTest test = null;
		while (iter.hasNext() && continu) {
			test = iter.next();
			if (name.equals(test.getName())) {
				continu = false;
			}
		}
		if (false == continu && null != this.usedProtocolParser) {
			return getSlaveManagement().sendTest(test);
		}
		return false;
	}

	public List<String> getAvailableProtocols() {
		List<String> list = new ArrayList<String>();
		Iterator<ProtocolParser> iter = this.protocolParser.iterator();
		while (iter.hasNext()) {
			list.add(iter.next().getProtocolName());
		}
		return list;
	}

	public boolean openPlanTest(String path) {
		System.out.println(path);
		return false;
	}

	public boolean removeInstruction(String testName, int instructionPosition) {
		if (null == this.testPlan)
			return false;
		if (instructionPosition < 0)
			return false;
		Iterator<AbstractMonitoredTest> iter = this.testPlan.getTests().iterator();
		AbstractMonitoredTest test = null;
		while (iter.hasNext()) {
			test = iter.next();
			if (testName.equals(test.getName())) {
				if (instructionPosition >= test.getInstructions().size()) {
					return false;
				} else {
					test.getInstructions().remove(instructionPosition);
					return true;
				}
			}
		}
		return false;
	}

	public void removeTarget(String target) {
		if (null == this.testPlan)
			return;
		this.testPlan.getTargets().remove(target);
	}
	
	public boolean removeTest(String testName) {
		if (null == this.testPlan)
			return false;
		Iterator<AbstractMonitoredTest> iter = this.testPlan.getTests().iterator();
		AbstractMonitoredTest test = null;
		while (iter.hasNext()) {
			test = iter.next();
			if (testName.equals(test.getName())) {
				this.testPlan.getTests().remove(test);
				return true;
			}
		}
		return false;
	}

	public boolean savePlanTest(String planTestName) {
		if (null == this.testPlan)
			return false;
		String res = this.testPlan.writeJSONString();
		
		updatePlanTestNameList(planTestName);
		
		System.out.println(res);
		return false;
	}

	public void setPort(int port) {
		this.testPlan.setPort(port);
	}

	@Override
	public void addPlanTestListener(TestPlanListener planTestListener) {
		this.planTestListenerList.add(planTestListener);
	}

	@Override
	public void removePlanTestListener(TestPlanListener planTestListener) { 
		this.planTestListenerList.remove(planTestListener);
	}

	private void updatePlanTestNameList(String name) {
		Iterator<TestPlanListener> iter = this.planTestListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().updatePlanTestName(name);
		}
	}

	@Override
	public void renameTestPlan(String name) {
		if (null == this.testPlan)
			return;
		this.testPlan.setName(name);
		updatePlanTestNameList(name);
	}

	@Override
	public void addTestListener(TestListener testListener) {
		this.testListenerList.add(testListener);
	}

	@Override
	public void removeTestListener(TestListener testListener) {
		this.testListenerList.remove(testListener);
	}
	
	private void addScalabilityTestListenerList(String name) {
		Iterator<TestListener> iter = this.testListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().addScalabilityTestListener(name);
		}
	}
	
	private void addWorkloadTestListener(String name) {
		Iterator<TestListener> iter = this.testListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().addWorkloadTestListener(name);
		}
	}
}