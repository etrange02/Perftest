/**
 * 
 */
package controls.ctestplanmanagement;

import gui.monitoring.interfaces.IGUIMonitor;
import controls.cslavemanagement.DataBuffer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class ScalabilityMonitor extends Monitor {

	public void addGUIMonitor(IGUIMonitor guiMonitor) {
		this.getGuiMonitors().add(guiMonitor);
	}

	public void process() {
	}

	public void setDataBuffer(DataBuffer dataBuffer) {
	}
}