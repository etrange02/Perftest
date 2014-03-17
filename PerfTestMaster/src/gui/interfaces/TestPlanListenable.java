package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestPlanListenable {

	/**
	 * Adds a TestPlanListener to the list
	 * @param testPlanListener a listener
	 */
	public void addTestPlanListener(TestPlanListener testPlanListener);
	
	/**
	 * Removes a TestPlanListener from the list
	 * @param testPlanListener a listener
	 */
	public void removeTestPlanListener(TestPlanListener testPlanListener);
}
