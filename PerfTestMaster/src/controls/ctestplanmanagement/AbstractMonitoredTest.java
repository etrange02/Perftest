package controls.ctestplanmanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.interfaces.AbstractMonitoredTestListenable;
import gui.interfaces.AbstractMonitoredTestListener;
import controls.ctestplanmanagement.interfaces.IMonitored;
import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractMonitoredTest extends AbstractTest implements IMonitored, AbstractMonitoredTestListenable {

	private static final long serialVersionUID = -305355069068010463L;
	private List<AbstractMonitoredTestListener> abstractMonitoredTestListeners;
	
	public AbstractMonitoredTest(String name) {
		super(name);
		this.abstractMonitoredTestListeners = new ArrayList<AbstractMonitoredTestListener>();
	}

	public void addAbstractMonitoredTestListener(AbstractMonitoredTestListener listener) {
		this.abstractMonitoredTestListeners.add(listener);
	}
	
	public void removeAbstractMonitoredTestListener(AbstractMonitoredTestListener listener) {
		this.abstractMonitoredTestListeners.remove(listener);
	}
	
	public void updateListeners() {
		Iterator<AbstractMonitoredTestListener> iter = this.abstractMonitoredTestListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateData();
		}
	}
}
