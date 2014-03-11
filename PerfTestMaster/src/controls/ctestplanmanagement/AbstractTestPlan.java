/**
 * 
 */
package controls.ctestplanmanagement;

import gui.interfaces.TestPlanPanelListenable;
import java.util.ArrayList;
import java.util.List;

import controls.ctestplanmanagement.interfaces.ITestPlan;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTestPlan implements ITestPlan, TestPlanPanelListenable {

	private String name;
	private List<AbstractMonitoredTest> tests;
	private List<String> targets;
	private int port;
	
	public AbstractTestPlan() {
		this.tests = new ArrayList<>();
		this.targets = new ArrayList<>();
		this.port = 0;
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
	 * @param test a list
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

}