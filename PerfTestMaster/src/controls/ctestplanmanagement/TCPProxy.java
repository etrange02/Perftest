/**
 * 
 */
package controls.ctestplanmanagement;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class TCPProxy {

	private TestPlanManagementFacade testPlanManagementFacade;

	/** 
	 * @return testPlanManagementFacade
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TestPlanManagementFacade getTestPlanManagementFacade() {
		return testPlanManagementFacade;
	}

	/** 
	 * @param testPlanManagementFacade testPlanManagementFacade � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTestPlanManagementFacade( TestPlanManagementFacade testPlanManagementFacade) {
		this.testPlanManagementFacade = testPlanManagementFacade;
	}
}