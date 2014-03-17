package gui.interfaces;

/**+
 * Observer pattern. Updated when data changed in an AbstractMonitoredTestListenable
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface AbstractMonitoredTestListener {

	/**
	 * Informs that the list of instructions changed
	 */
	public void updateData();
}
