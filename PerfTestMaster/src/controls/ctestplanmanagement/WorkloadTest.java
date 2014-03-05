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

}