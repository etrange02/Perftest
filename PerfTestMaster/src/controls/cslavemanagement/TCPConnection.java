/**
 * 
 */
package controls.cslavemanagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TCPConnection {

	private Slave slave;

	/**
	 * Returns the associated slave
	 * @return a slave
	 */
	public Slave getSlave() {
		return slave;
	}

	/**
	 * Modifies the associated slave
	 * @param slave a slave
	 */
	public void setSlave(Slave slave) {
		this.slave = slave;
	}
}