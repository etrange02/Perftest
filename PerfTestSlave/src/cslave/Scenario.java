/**
 * 
 */
package cslave;

import java.util.List;
import java.util.Set;

import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Scenario implements IScenario {

	private List<IResponse> response;

	/** 
	 * @return response
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IResponse> getResponse() {
		return response;
	}

	/** 
	 * @param response response � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setResponse(List<IResponse> response) {
		this.response = response;
	}

	/** 
	 * (non-Javadoc)
	 * @see IScenario#addResponse(byte[] binaryResponse, Set delay)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addResponse(byte[] binaryResponse, List<Integer> delay) {
	}

	/** 
	 * (non-Javadoc)
	 * @see IScenario#getResponses()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IResponse> getResponses() {
		return null;
	}
}