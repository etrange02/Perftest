package controls.ctestplanmanagement;

import controls.ctestplanmanagement.interfaces.IMonitored;

import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractMonitoredTest extends AbstractTest implements IMonitored {

	private static final long serialVersionUID = -305355069068010463L;
	
	public AbstractMonitoredTest(String name) {
		super(name);
	}

	public Monitor getMonitor() {
		return null;
	}

}
