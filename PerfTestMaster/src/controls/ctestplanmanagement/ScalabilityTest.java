/**
 * 
 */
package controls.ctestplanmanagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class ScalabilityTest extends AbstractMonitoredTest {
	
	private static final long serialVersionUID = -8919264177566199550L;
	private ScalabilityMonitor scalabilityMonitor;

	/**
	 * Returns the associated ScalabilityMonitor
	 * @return a ScalabilityMonitor
	 */
	public ScalabilityMonitor getScalabilityMonitor() {
		return scalabilityMonitor;
	}

	/**
	 * Modifies the associated ScalabilityMonitor
	 * @param scalabilityMonitor a ScalabilityMonitor
	 */
	public void setScalabilityMonitor(ScalabilityMonitor scalabilityMonitor) {
		this.scalabilityMonitor = scalabilityMonitor;
	}

	public String writeJSONString() {
		return null;
	}

	public Monitor getMonitor() {
		return getScalabilityMonitor();
	}
}