/**
 * 
 */
package controls.ctestplanmanagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class TCPProxy {

	private TestPlanManagementFacade testPlanManagementFacade;

	/**
	 * Returns the associated TestPlan manager
	 * @return a testPlan manager
	 */
	public TestPlanManagementFacade getTestPlanManagementFacade() {
		return testPlanManagementFacade;
	}

	/**
	 * Modifies the associated TestPlan manager
	 * @param testPlanManagementFacade a testPlan manager
	 */
	public void setTestPlanManagementFacade( TestPlanManagementFacade testPlanManagementFacade) {
		this.testPlanManagementFacade = testPlanManagementFacade;
	}
}