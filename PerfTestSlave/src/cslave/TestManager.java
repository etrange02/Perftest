/**
 * 
 */
package cslave;

import java.util.List;

import cslave.interfaces.IScenario;
import cslave.interfaces.ITestManager;
import shared.AbstractTest;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TestManager implements ITestManager {
	
	private TestParameter testRunner;
	private TCPConnectionToMaster[] TCPConnectionToMaster = null;
	private List<IScenario> scenario;
	private AbstractTest abstractTest;
	private List<TCPConnectionToTestedServer> TCPConnectionToTestedServer;
	private List<Comparator> comparator;

	/** 
	 * @return testRunner
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TestParameter getTestRunner() {
		return testRunner;
	}

	/** 
	 * @param testRunner testRunner � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTestRunner(TestParameter testRunner) {
		this.testRunner = testRunner;
	}
	
	/** 
	 * @return TCPConnectionToMaster
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TCPConnectionToMaster[] getTCPConnectionToMaster() {
		return TCPConnectionToMaster;
	}

	/** 
	 * @param TCPConnectionToMaster TCPConnectionToMaster � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTCPConnectionToMaster( TCPConnectionToMaster[] TCPConnectionToMaster) {
		this.TCPConnectionToMaster = TCPConnectionToMaster;
	}
	
	/** 
	 * @return scenario
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IScenario> getScenario() {
		return scenario;
	}

	/** 
	 * @param scenario scenario � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setScenario(List<IScenario> scenario) {
		this.scenario = scenario;
	}

	/** 
	 * @return abstractTest
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public AbstractTest getAbstractTest() {
		return abstractTest;
	}

	/** 
	 * @param abstractTest abstractTest � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAbstractTest(AbstractTest abstractTest) {
		this.abstractTest = abstractTest;
	}

	/** 
	 * @return TCPConnectionToTestedServer
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<TCPConnectionToTestedServer> getTCPConnectionToTestedServer() {
		return TCPConnectionToTestedServer;
	}

	/** 
	 * @param TCPConnectionToTestedServer TCPConnectionToTestedServer � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTCPConnectionToTestedServer(List<TCPConnectionToTestedServer> TCPConnectionToTestedServer) {
		this.TCPConnectionToTestedServer = TCPConnectionToTestedServer;
	}

	/** 
	 * @return comparator
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<Comparator> getComparator() {
		return comparator;
	}

	/** 
	 * @param comparator comparator � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setComparator(List<Comparator> comparator) {
		this.comparator = comparator;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void waitCMD() {
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void readTest() {
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void reset() {
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestManager#addComparator(Comparator comparator)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addComparator(Comparator comparator) {
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestManager#start()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void start() {
	}
}