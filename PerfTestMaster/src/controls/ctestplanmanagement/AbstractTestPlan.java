/**
 * 
 */
package controls.ctestplanmanagement;

import gui.interfaces.TestPlanListenable;
import gui.interfaces.TestPlanListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controls.ctestplanmanagement.interfaces.ITestPlan;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTestPlan implements ITestPlan, TestPlanListenable {

	private String name;
	private List<AbstractMonitoredTest> tests;
	private List<String> targets;
	private int port;
	private List<TestPlanListener> planTestListenerList;
	
	public AbstractTestPlan() {
		this.tests = new ArrayList<AbstractMonitoredTest>();
		this.targets = new ArrayList<String>();
		this.planTestListenerList = new ArrayList<TestPlanListener>();
		this.port = 0;
		this.name = "";
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Modifies the plan test name
	 * @param name a name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractMonitoredTest> getTests() {
		return this.tests;
	}

	/**
	 * Modifies the list of created tests
	 * @param tests a list
	 */
	public void setTests(List<AbstractMonitoredTest> tests) {
		this.tests = tests;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	/**
	 * Modifies the list of targets, which are the tested server
	 * @param targets a list
	 */
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	public int getPort() {
		return port;
	}

	/**
	 * Modifies the tested port
	 * @param port the port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	public void updatePlanTestNameList(String name) {
		Iterator<TestPlanListener> iter = this.planTestListenerList.iterator();
		while (iter.hasNext()) {
			iter.next().updatePlanTestName(name);
		}
	}
	
	@Override
	public void addTestPlanListener(TestPlanListener testPlanListener) {
		this.planTestListenerList.add(testPlanListener);
	}

	@Override
	public void removeTestPlanListener(TestPlanListener testPlanListener) {
		this.planTestListenerList.remove(testPlanListener);
	}

}