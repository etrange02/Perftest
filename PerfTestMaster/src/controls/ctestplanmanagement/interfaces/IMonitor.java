/**
 * 
 */
package controls.ctestplanmanagement.interfaces;

import gui.monitoring.interfaces.IGUIMonitor;
import controls.cslavemanagement.DataBuffer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface IMonitor {
	
	/**
	 * Adds a new GUIMonitor to the list of observer GUIMonitor
	 * @param guiMonitor
	 */
	public void addGUIMonitor(IGUIMonitor guiMonitor);

	/**
	 * Launches a thread in charge of treating data
	 */
	public void process();

	/**
	 * Modifies the associated DataBuffer
	 * @param dataBuffer a dataBuffer
	 */
	public void setDataBuffer(DataBuffer dataBuffer);
}