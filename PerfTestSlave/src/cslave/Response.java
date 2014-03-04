/**
 * 
 */
package cslave;

import cslave.interfaces.IResponse;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Response implements IResponse {

	private int delay;
	private byte[] serverBinaryResponse;
	
	public Response() {
		this.delay = 0;
		this.serverBinaryResponse = new byte[1024];
	}

	/** 
	 * @return delay
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getDelay() {
		return delay;
	}

	/** 
	 * @param delay delay � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/** 
	 * @return serverBinaryResponse
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public byte[] getServerBinaryResponse() {
		return serverBinaryResponse;
	}

	/** 
	 * @param serverBinaryResponse serverBinaryResponse � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setServerBinaryResponse(byte[] serverBinaryResponse) {
		this.serverBinaryResponse = serverBinaryResponse;
	}
}