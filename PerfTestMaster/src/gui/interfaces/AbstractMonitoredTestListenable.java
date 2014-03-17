package gui.interfaces;

/**+
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface AbstractMonitoredTestListenable {

	/**
	 * Adds an AbstractMonitoredTestListener to the list
	 * @param listener a listener
	 */
	public void addAbstractMonitoredTestListener(AbstractMonitoredTestListener listener);
	
	/**
	 * Removes an AbstractMonitoredTestListener from the list
	 * @param listener a listener
	 */
	public void removeAbstractMonitoredTestListener(AbstractMonitoredTestListener listener);
}
