package controls.ctestplanmanagement.interfaces;

import controls.ctestplanmanagement.Monitor;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface IMonitored {

	/**
	 * Returns the associated monitor
	 * @return the monitor
	 */
	public Monitor getMonitor();
}
