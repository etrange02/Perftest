package gui.interfaces;

/**+
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface SlaveListenable {

	/**
	 * Adds a SlaveListener to the list
	 * @param slaveListener a listener
	 */
	public void addSlaveListener(SlaveListener slaveListener);
	
	/**
	 * Removes a SlaveListener from the list
	 * @param slaveListener a listener
	 */
	public void removeSlaveListener(SlaveListener slaveListener);
}
