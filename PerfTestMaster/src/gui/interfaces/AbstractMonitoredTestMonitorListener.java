package gui.interfaces;

import java.util.List;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface AbstractMonitoredTestMonitorListener {
	
	/**
	 * Informs that the list of targets changed
	 * @param targets the list of all targets
	 * @param selectedTargets the list of selected targets affected to the test
	 */
	public void updateTargets(List<String> targets, List<String> selectedTargets);
	
	/**
	 * Informs that the delay between two instructions changed
	 * @param delay a delay
	 */
	public void updateDelay(int delay);
	
	/**
	 * Informs that the maximum number of slave changed
	 * @param count a number
	 */
	public void updateMaxSlaveCount(int count);
	
	/**
	 * Informs that the number of slave affected to the test changed
	 * @param count a number
	 */
	public void updateSlaveCount(int count);

}
