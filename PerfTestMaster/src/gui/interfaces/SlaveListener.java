package gui.interfaces;

/**
 * Observer pattern.
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public interface SlaveListener {

	/**
	 * Informs that a slave has been added or deleted
	 */
	public void updateData();
}
