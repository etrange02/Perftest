package gui.interfaces;


/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestPlanPanelListenable {

	/**
	 * Adds a TestPlanPanelListener to the list
	 * @param testPlanPanelListener a listener
	 */
	public void addTestPlanPanelListener(TestPlanPanelListener testPlanPanelListener);
	
	/**
	 * Removes a TestPlanPanelListener from the list
	 * @param testPlanPanelListener a listener
	 */
	public void removeTestPlanPanelListener(TestPlanPanelListener testPlanPanelListener);
}
