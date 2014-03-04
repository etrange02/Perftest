/**
 * 
 */
package cslave;

import cslave.interfaces.ITCPConnecitonToTestedServer;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class TCPConnectionToTestedServer implements
		ITCPConnecitonToTestedServer {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Scenario scenario;

	/** 
	 * @return scenario
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Scenario getScenario() {
		// begin-user-code
		return scenario;
		// end-user-code
	}

	/** 
	 * @param scenario scenario � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setScenario(Scenario scenario) {
		// begin-user-code
		this.scenario = scenario;
		// end-user-code
	}
}