/**
 * 
 */
package controls.ctestplanmanagement;

import gui.IGUIMonitor;

import java.util.List;

import controls.cslavemanagement.interfaces.IDataBuffer;
import controls.ctestplanmanagement.interfaces.IMonitor;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Etrange02
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Monitor implements IMonitor {

	private List<IGUIMonitor> guiMonitors;
	private IDataBuffer dataBuffer;

	/** 
	 * @return guiMonitors
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IGUIMonitor> getGuiMonitors() {
		return guiMonitors;
	}

	/** 
	 * @param guiMonitors guiMonitors � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGuiMonitors(List<IGUIMonitor> guiMonitors) {
		this.guiMonitors = guiMonitors;
	}

	/** 
	 * @return dataBuffer
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IDataBuffer getDataBuffer() {
		return dataBuffer;
	}

	/** 
	 * @param dataBuffer dataBuffer � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDataBuffer(IDataBuffer dataBuffer) {
		this.dataBuffer = dataBuffer;
	}
	
	public void addGUIMonitor(IGUIMonitor guiMonitor) {
	}
}