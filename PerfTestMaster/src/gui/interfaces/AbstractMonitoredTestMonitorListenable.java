package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface AbstractMonitoredTestMonitorListenable {

	/**
	 * Adds an AbstractMonitoredTestMonitorListener to the list
	 * @param listener a listener
	 */
	public void addAbstractMonitoredTestMonitorListener(AbstractMonitoredTestMonitorListener listener);
	
	/**
	 * Removes an AbstractMonitoredTestMonitorListener from the list
	 * @param listener a listener
	 */
	public void removeAbstractMonitoredTestMonitorListener(AbstractMonitoredTestMonitorListener listener);
}
