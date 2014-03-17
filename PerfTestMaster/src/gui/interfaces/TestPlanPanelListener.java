package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestPlanPanelListener {
	
	/**
	 * Informs that the port of the test plan has changed
	 * @param port the port
	 */
	public void updatePort(String port);
	
	/**
	 * Informs the listener that it have to update its network interfaces
	 */
	public void updateNetworkInterfaces();
}
