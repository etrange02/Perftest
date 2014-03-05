/**
 * 
 */
package controls.ctestplanmanagement;

import gui.IGUIMonitor;

import java.util.ArrayList;
import java.util.List;

import controls.cslavemanagement.interfaces.IDataBuffer;
import controls.ctestplanmanagement.interfaces.IMonitor;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class Monitor implements IMonitor {

	private List<IGUIMonitor> guiMonitors;
	private IDataBuffer dataBuffer;
	
	public Monitor() {
		this.guiMonitors = new ArrayList<>();
	}

	/**
	 * Returns the list of GUIMonitors
	 * @return a list
	 */
	public List<IGUIMonitor> getGuiMonitors() {
		return guiMonitors;
	}

	/**
	 * Modifies the list of GUIMonitor
	 * @param guiMonitors a list
	 */
	public void setGuiMonitors(List<IGUIMonitor> guiMonitors) {
		this.guiMonitors = guiMonitors;
	}

	/**
	 * Returns the associated DataBuffer
	 * @return the dataBuffer
	 */
	public IDataBuffer getDataBuffer() {
		return dataBuffer;
	}

	public void setDataBuffer(IDataBuffer dataBuffer) {
		this.dataBuffer = dataBuffer;
	}
	
	public void addGUIMonitor(IGUIMonitor guiMonitor) {
	}
}