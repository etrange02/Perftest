/**
 * 
 */
package controls.ctestplanmanagement;

import java.util.ArrayList;
import java.util.List;

import shared.ITest;
import controls.cslavemanagement.interfaces.ISlave;
import controls.ctestplanmanagement.interfaces.ITestPlan;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class AbstractTestPlan implements ITestPlan {

	private String name;
	private List<ISlave> iSlave;
	private List<ITest> tests;
	private List<ISlave> targets = null;
	private int port;
	
	public AbstractTestPlan() {
		this.iSlave = new ArrayList<>();
		this.tests = new ArrayList<>();
		this.targets = new ArrayList<>();
		this.port = 0;
	}

	/** 
	 * @return name
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getName() {
		return name;
	}

	/** 
	 * @param name name � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * @return iSlave
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<ISlave> getiSlave() {
		return this.iSlave;
	}

	/** 
	 * @param iSlave iSlave � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setiSlave(List<ISlave> iSlave) {
		this.iSlave = iSlave;
	}

	/** 
	 * @return test
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<ITest> getTest() {
		return this.tests;
	}

	/** 
	 * @param test test � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTest(List<ITest> test) {
		this.tests = test;
	}

	/** 
	 * @return targets
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<ISlave> getTargets() {
		return this.targets;
	}

	/** 
	 * @param targets targets � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTargets(List<ISlave> targets) {
		this.targets = targets;
	}

	/** 
	 * @return port
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getPort() {
		return port;
	}

	/** 
	 * @param port port � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPort(int port) {
		this.port = port;
	}

}