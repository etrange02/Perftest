/**
 * 
 */
package cslave;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TCPConnectionToMaster {

	private TestManager testManager;
	
	public TCPConnectionToMaster() {
		this.testManager = null;
	}

	/**
	 * returns the TestManager associated
	 * @return the TestManager
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * Modifies the associated testManager
	 * @param testManager a TestManager
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
}