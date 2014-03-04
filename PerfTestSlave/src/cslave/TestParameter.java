/**
 * 
 */
package cslave;

import cslave.interfaces.ITestParameter;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestParameter implements ITestParameter {

	private int port;
	private String IPAddress;
	private String protocolName;

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

	/** 
	 * @return IPAddress
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getIPAddress() {
		return IPAddress;
	}

	/** 
	 * @param IPAddress IPAddress � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIPAddress(String IPAddress) {
		this.IPAddress = IPAddress;
	}

	/** 
	 * @return protocolName
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getProtocolName() {
		return protocolName;
	}

	/** 
	 * @param protocolName protocolName � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
}