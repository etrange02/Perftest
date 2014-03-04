/**
 * 
 */
package controls.cslavemanagement;

import controls.cslavemanagement.interfaces.ISlave;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Slave implements ISlave {

	private String name;
	private String address;
	private boolean deployed;
	private TCPConnection TCPClientSlave;

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
	 * @return address
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * @param address address � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/** 
	 * @return deployed
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean isDeployed() {
		return this.deployed;
	}

	/** 
	 * @param deployed deployed � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}

	/** 
	 * @return TCPClientSlave
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TCPConnection getTCPClientSlave() {
		return TCPClientSlave;
	}

	/** 
	 * @param TCPClientSlave TCPClientSlave � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTCPClientSlave(TCPConnection TCPClientSlave) {
		this.TCPClientSlave = TCPClientSlave;
	}
}