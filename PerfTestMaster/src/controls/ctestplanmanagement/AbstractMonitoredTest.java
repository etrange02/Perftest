package controls.ctestplanmanagement;

import controls.ctestplanmanagement.interfaces.IMonitored;
import shared.AbstractTest;

public abstract class AbstractMonitoredTest extends AbstractTest implements IMonitored {

	private static final long serialVersionUID = -305355069068010463L;

	public Monitor getMonitor() {
		return null;
	}

}
