/**
 * 
 */
package controls.ctestplanmanagement;

import gui.interfaces.TestListener;
import gui.interfaces.TestPlanListener;
import gui.interfaces.TestPlanPanelListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shared.AbstractInstruction;
import shared.IInstruction;
import tools.Factory;
import controls.cslavemanagement.interfaces.ISlaveManagement;
import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

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
	private List<TestPlanPanelListener> testPlanPanelListeners;
	
	public TestPlanManagementFacade() {
		this.protocolParser = new ArrayList<ProtocolParser>();
		this.planTestListenerList = new ArrayList<TestPlanListener>();
		this.testListenerList = new ArrayList<TestListener>();
		this.testPlanPanelListeners = new ArrayList<TestPlanPanelListener>();
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
		if (null == getTestPlan() || null == testName || null == instructionName || testName.isEmpty() || instructionName.isEmpty())
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
			AbstractInstruction instruction = this.usedProtocolParser.createNewInstruction();
			instruction.setName(instructionName);
			test.getInstructions().add(instruction);
			test.updateListeners();
			return instruction;
		}
		return null;
	}

	@Override
	public IInstruction addNewInstruction(AbstractMonitoredTest test,
			String instructionName) {
		if (null == getTestPlan() || null == test || null == instructionName || instructionName.isEmpty())
			return null;
		
		if (this.getTestPlan().getTests().contains(test)) {
			AbstractInstruction instruction = this.usedProtocolParser.createNewInstruction();
			instruction.setName(instructionName);
			test.getInstructions().add(instruction);
			test.updateListeners();
			return instruction;
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
			addScalabilityTestListenerList(test);
		}
		return test;
	}

	public WorkloadTest addNewWorkloadTest(String testName) {
		WorkloadTest test = null;
		if (canAddNewMonitoredTest(testName)) {
			test = Factory.createWorkloadTest(testName);
			this.testPlan.getTests().add(test);
			addWorkloadTestListener(test);
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
		if (null == target || target.isEmpty())
			return;
		
		Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		//((0|1[0-9]{0,2}|2[0-9]?|2[0-4][0-9]|25[0-5]|[3-9][0-9]?)\.){3}(0|1[0-9]{0,2}|2[0-9]?|2[0-4][0-9]|25[0-5]|[3-9][0-9]?)
	    Matcher m = p.matcher(target);
		
		if (!m.find())
			return;
		
		if (!this.testPlan.getTargets().contains(target)) {
			this.testPlan.getTargets().add(target);
			updatePlanTestTargets();
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
	
	public void editInstruction(AbstractMonitoredTest test, int pos,
			String name, String request) {
		if (null == getTestPlan() || null == test || null == name || name.isEmpty() || null == request || request.isEmpty())
			return;
		if (!this.getTestPlan().getTests().contains(test))
			return;
		if (pos >= test.getInstructions().size())
			return;
		
		IInstruction instruction = test.getInstructions().get(pos);
		if (null == instruction)
			return;
		instruction.setName(name);
		instruction.setReadableRequest(request);
		test.updateListeners();
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
					test.updateListeners();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeInstruction(AbstractMonitoredTest test,
			int instructionPosition) {
		if (null == getTestPlan() || null == test || instructionPosition < 0)
			return false;
		
		if (this.getTestPlan().getTests().contains(test)) {
			if (test.getInstructions().size() > instructionPosition) {
				test.getInstructions().remove(instructionPosition);
				test.updateListeners();
				return true;
			}
		}
		return false;
	}

	public void removeTarget(String target) {
		if (null == this.testPlan)
			return;
		this.testPlan.getTargets().remove(target);
		updatePlanTestTargets();
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
		if (port >= 0)
			this.testPlan.setPort(port);
		
		System.out.println("Emitted " + this.testPlan.getPort());
		
		Iterator<TestPlanPanelListener> iter = this.testPlanPanelListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updatePort("" + this.testPlan.getPort());
		}
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
	
	private void addScalabilityTestListenerList(AbstractMonitoredTest abstractMonitoredTest) {
		Iterator<TestListener> iter = this.testListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().addScalabilityTestListener(abstractMonitoredTest);
		}
	}
	
	private void addWorkloadTestListener(AbstractMonitoredTest abstractMonitoredTest) {
		Iterator<TestListener> iter = this.testListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().addWorkloadTestListener(abstractMonitoredTest);
		}
	}

	@Override
	public void renameTest(String oldName, String newName) {
		if (null == oldName || null == newName || newName.isEmpty())
			return;
		AbstractMonitoredTest test = null, tmp = null;
		Iterator<AbstractMonitoredTest> iter = this.getTestPlan().getTests().iterator();
		while (iter.hasNext()) {
			tmp = iter.next();
			if (newName.equals(tmp.getName()))
				return;
			else if (oldName.equals(tmp.getName()))
				test = tmp;
		}
		
		if (null == test)
			return;
		
		test.setName(newName);
		test.updateTestListeners();
		
		Iterator<TestListener> iter2 = this.testListenerList.iterator();
		while (iter2.hasNext()) {
			iter2.next().renameTest(test.getName());
		}
	}
	
	public void renameTest(AbstractMonitoredTest test, String newName) {
		if (null == getTestPlan() || null == test || null == newName || newName.isEmpty())
			return;
		
		if (!this.getTestPlan().getTests().contains(test))
			return;
		Iterator<AbstractMonitoredTest> iter = this.getTestPlan().getTests().iterator();
		boolean present = false;
		while (iter.hasNext() && !present) {
			if (iter.next().getName().equals(newName))
				return;
		}
		
		test.setName(newName);
		
		test.updateTestListeners();
		
		Iterator<TestListener> iter2 = this.testListenerList.iterator();
		while (iter2.hasNext()) {
			iter2.next().renameTest(test.getName());
		}
	}

	@Override
	public void addTestPlanPanelListener(
			TestPlanPanelListener testPlanPanelListener) {
		this.testPlanPanelListeners.add(testPlanPanelListener);
		if (null != this.testPlan)
			this.testPlan.addTestPlanPanelListener(testPlanPanelListener);
	}

	@Override
	public void removeTestPlanPanelListener(
			TestPlanPanelListener testPlanPanelListener) {
		this.testPlanPanelListeners.remove(testPlanPanelListener);
		if (null != this.testPlan)
			this.testPlanPanelListeners.remove(testPlanPanelListener);
	}
	
	private void updatePlanTestTargets() {
		Iterator<TestPlanPanelListener> iter = this.testPlanPanelListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateNetworkInterfaces();
		}
	}

	@Override
	public void testPlanGenericSet(String key, Object value) {
		if (null == key || key.isEmpty() || null == value)
			return;
		this.testPlan.set(key, value);
	}

	
}