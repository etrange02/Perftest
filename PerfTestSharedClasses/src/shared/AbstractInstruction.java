/**
 * 
 */
package shared;

import java.io.Serializable;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class AbstractInstruction implements IInstruction, Serializable {
	
	private static final long serialVersionUID = 5531059771292171658L;
	private String readableRequest;
	private byte[] binaryRequest;
	private byte[] binaryResponse;
	private String name;
	
	public AbstractInstruction() {
		this.binaryRequest = new byte[1024];
		this.binaryResponse = new byte[1024];
	}

	public String getReadableRequest() {
		return readableRequest;
	}

	public void setReadableRequest(String readableRequest) {
		this.readableRequest = readableRequest;
	}

	public byte[] getBinaryRequest() {
		return binaryRequest;
	}

	public void setBinaryRequest(byte[] binaryRequest) {
		this.binaryRequest = binaryRequest;
	}

	public byte[] getBinaryResponse() {
		return binaryResponse;
	}

	public void setBinaryResponse(byte[] binaryResponse) {
		this.binaryResponse = binaryResponse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}