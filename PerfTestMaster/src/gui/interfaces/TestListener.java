package gui.interfaces;

import controls.ctestplanmanagement.AbstractMonitoredTest;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestListener {

	/**
	 * Informs that a Scalability test has been added
	 * @param abstractMonitoredTest a test
	 */
	public void addScalabilityTestListener(AbstractMonitoredTest abstractMonitoredTest);
	
	/**
	 * Informs that a Workload test has been added
	 * @param abstractMonitoredTest a test
	 */
	public void addWorkloadTestListener(AbstractMonitoredTest abstractMonitoredTest);
	
	/**
	 * Informs that the name of the test has changed
	 * @param newName a name
	 */
	public void renameTest(String newName);
}
