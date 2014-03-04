/**
 * 
 */
package controls.ctestplanmanagement.interfaces;

import java.util.List;

import controls.cslavemanagement.interfaces.ISlave;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import shared.ITest;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface ITestPlan {
	
	public String getName();

	public List<AbstractMonitoredTest> getTests();

	public int getPort();
	
	public String writeJSONString();
	
	public List<ITest> getTest();

	public List<ISlave> getTargets();
}