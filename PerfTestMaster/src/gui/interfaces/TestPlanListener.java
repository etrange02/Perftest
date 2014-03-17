package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface TestPlanListener {
	
	/**
	 * Informs that the name of the test plan has changed
	 * @param name a new name
	 */
	public void updatePlanTestName(String name);

}
