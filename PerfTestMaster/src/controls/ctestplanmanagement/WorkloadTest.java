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
public class WorkloadTest extends AbstractMonitoredTest {
	
	private static final long serialVersionUID = 4922590281574582110L;
	private WorkloadMonitor workloadMonitor;
	private int nextAddress;
	
	public WorkloadTest (String name) {
		super(name);
		this.nextAddress = 0;
	}

	/**
	 * Returns the associated WorkloadMonitor
	 * @return a WorkloadMonitor
	 */
	public WorkloadMonitor getWorkloadMonitor() {
		return workloadMonitor;
	}

	/**
	 * Modifies the associated WorkloadMonitor
	 * @param workloadMonitor a WorkloadMonitor
	 */
	public void setWorkloadMonitor(WorkloadMonitor workloadMonitor) {
		this.workloadMonitor = workloadMonitor;
	}

	public String writeJSONString() {
		return null;
	}

	public Monitor getMonitor() {
		return getWorkloadMonitor();
	}
	
	/**
	 * Returns a target address. If there are many addresses, each address can be used
	 * by calling the method many times
	 * @return an address
	 */
	public String getNextAddress() {
		String res = "";
		if (!this.getSelectedTargets().isEmpty()) {
			// We have no guarantee about selected targets variations
			this.nextAddress = this.nextAddress % this.getSelectedTargets().size();
			res = this.getSelectedTargets().get(nextAddress);
			this.nextAddress = (this.nextAddress++) % this.getSelectedTargets().size();
		}
		return res;
	}

}