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
public class ScalabilityTest extends AbstractTest {
	
	private static final long serialVersionUID = -8919264177566199550L;
	private ScalabilityMonitor scalabilityMonitor;

	/** 
	 * @return scalabilityMonitor
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ScalabilityMonitor getScalabilityMonitor() {
		return scalabilityMonitor;
	}

	/** 
	 * @param scalabilityMonitor scalabilityMonitor � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setScalabilityMonitor(ScalabilityMonitor scalabilityMonitor) {
		this.scalabilityMonitor = scalabilityMonitor;
	}

	/** 
	 * (non-Javadoc)
	 * @see ITest#getInstructions()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IInstruction> getInstructions() {
		return null;
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
}