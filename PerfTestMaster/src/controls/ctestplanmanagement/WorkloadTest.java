/**
 * 
 */
package controls.ctestplanmanagement;


import java.util.List;

import shared.AbstractTest;
import shared.IInstruction;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class WorkloadTest extends AbstractTest {
	
	private static final long serialVersionUID = 4922590281574582110L;
	private WorkloadMonitor workloadMonitor;

	/** 
	 * @return workloadMonitor
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public WorkloadMonitor getWorkloadMonitor() {
		return workloadMonitor;
	}

	/** 
	 * @param workloadMonitor workloadMonitor � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setWorkloadMonitor(WorkloadMonitor workloadMonitor) {
		this.workloadMonitor = workloadMonitor;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITest#writeJSONString()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String writeJSONString() {
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITest#getMonitor()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Monitor getMonitor() {
		return null;
	}

	public List<IInstruction> getInstructions() {
		return null;
	}

}