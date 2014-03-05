/**
 * 
 */
package controls.ctestplanmanagement.interfaces;

import java.util.List;

import controls.ctestplanmanagement.AbstractMonitoredTest;


/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ITestPlan {
	
	/**
	 * Returns the name of the plan test. Useful to store the file name
	 * @return the name
	 */
	public String getName();

	/**
	 * Returns a list of tests created by the user
	 * @return a list
	 */
	public List<AbstractMonitoredTest> getTests();

	/**
	 * Returns the tested port
	 * @return the port
	 */
	public int getPort();
	
	/**
	 * Turns the test plan into a JSON string
	 * @return a JSON string
	 */
	public String writeJSONString();

	/**
	 * Returns the list of targets (which is the tested server)
	 * @return a list
	 */
	public List<String> getTargets();
}