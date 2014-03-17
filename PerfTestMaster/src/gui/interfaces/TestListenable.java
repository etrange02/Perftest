package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestListenable {
	
	/**
	 * Adds a TestListener to the list
	 * @param testListener a listener
	 */
	public void addTestListener(TestListener testListener);
	
	/**
	 * Removes a TestListener from the list
	 * @param testListener a listener
	 */
	public void removeTestListener(TestListener testListener);

}
