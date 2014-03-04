/**
 * 
 */
package controls.cslavemanagement;

import java.util.ArrayList;
import java.util.List;

import controls.cslavemanagement.interfaces.IDataBuffer;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DataBuffer implements IDataBuffer {

	private List<TCPDataBuffer> tCPDataBuffer;
	
	public DataBuffer() {
		this.tCPDataBuffer = new ArrayList<>();
	}

	/** 
	 * @return tCPDataBuffer
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<TCPDataBuffer> gettCPDataBuffer() {
		return tCPDataBuffer;
	}

	/** 
	 * @param tCPDataBuffer tCPDataBuffer � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void settCPDataBuffer(List<TCPDataBuffer> tCPDataBuffer) {
		this.tCPDataBuffer = tCPDataBuffer;
	}

	/** 
	 * (non-Javadoc)
	 * @see IDataBuffer#getTCPDataBuffer()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<TCPDataBuffer> getTCPDataBuffer() {
		return this.tCPDataBuffer;
	}

	/** 
	 * (non-Javadoc)
	 * @see IDataBuffer#countReceivedInstructions()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int countReceivedInstructions() {
		return 0;
	}
}