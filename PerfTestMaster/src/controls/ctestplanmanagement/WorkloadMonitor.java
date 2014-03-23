/**
 * 
 */
package controls.ctestplanmanagement;

import controls.cslavemanagement.DataBuffer;
import gui.monitoring.interfaces.IGUIMonitor;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class WorkloadMonitor extends Monitor {

	public void addGUIMonitor(IGUIMonitor guiMonitor) {
		this.getGuiMonitors().add(guiMonitor);
	}

	public void process() {
	}

	public void setDataBuffer(DataBuffer dataBuffer) {
	}

}