/**
 * 
 */
package controls.ctestplanmanagement;

import java.util.List;

import controls.ctestplanmanagement.interfaces.ITestPlan;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import shared.IInstruction;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPlanManagementFacade implements ITestPlanManagement {

	private AbstractTestPlan testPlan;
	private List<ProtocolParser> protocolParser;
	private ProtocolParser usedProtocolParser;
	private TCPProxy TCPProxy;

	/** 
	 * @return testPlan
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public AbstractTestPlan getTestPlan() {
		return testPlan;
	}

	/** 
	 * @param testPlan testPlan � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTestPlan(AbstractTestPlan testPlan) {
		this.testPlan = testPlan;
	}
	
	/** 
	 * @return protocolParser
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<ProtocolParser> getProtocolParser() {
		return this.protocolParser;
	}

	/** 
	 * @param protocolParser protocolParser � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setProtocolParser(List<ProtocolParser> protocolParser) {
		this.protocolParser = protocolParser;
	}

	/** 
	 * @return usedProtocolParser
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ProtocolParser getUsedProtocolParser() {
		return usedProtocolParser;
	}

	/** 
	 * @param usedProtocolParser usedProtocolParser � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setUsedProtocolParser(ProtocolParser usedProtocolParser) {
		this.usedProtocolParser = usedProtocolParser;
	}

	/** 
	 * @return TCPProxy
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TCPProxy getTCPProxy() {
		return TCPProxy;
	}

	/** 
	 * @param TCPProxy TCPProxy � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTCPProxy(TCPProxy TCPProxy) {
		this.TCPProxy = TCPProxy;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addNewInstruction(String testName, String instructionName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IInstruction addNewInstruction(String testName, String instructionName) {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addNewTestPlan(String protocolName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ITestPlan addNewTestPlan(String protocolName) {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addNewScalabilityTest(String testName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ScalabilityTest addNewScalabilityTest(String testName) {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addNewWorkloadTest(String testName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public WorkloadTest addNewWorkloadTest(String testName) {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addProtocolParser(ProtocolParser protocolParser)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addProtocolParser(ProtocolParser protocolParser) {
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#addTarget(String target)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addTarget(String target) {
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#deployTest(String name)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean deployTest(String name) {
		return false;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#getAvailableProtocols()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<String> getAvailableProtocols() {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#openPlanTest(String path)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean openPlanTest(String path) {
		return false;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#removeInstruction(String testName, String instructionName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean removeInstruction(String testName, String instructionName) {
		return false;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#removeTarget(String target)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeTarget(String target) {
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#removeTest(String testName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean removeTest(String testName) {
		return false;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#savePlanTest(String planTestName)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean savePlanTest(String planTestName) {
		return false;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITestPlanManagement#setPort(Integer port)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPort(int port) {
	}
}