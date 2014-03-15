package controls.ctestplanmanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.interfaces.AbstractMonitoredTestListenable;
import gui.interfaces.AbstractMonitoredTestListener;
import gui.interfaces.AbstractMonitoredTestMonitorListenable;
import gui.interfaces.AbstractMonitoredTestMonitorListener;
import gui.interfaces.TestListenable;
import gui.interfaces.TestListener;
import controls.ctestplanmanagement.interfaces.IMonitored;
import shared.AbstractTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractMonitoredTest extends AbstractTest implements IMonitored, AbstractMonitoredTestListenable, TestListenable, AbstractMonitoredTestMonitorListenable {

	private static final long serialVersionUID = -305355069068010463L;
	private List<AbstractMonitoredTestListener> abstractMonitoredTestListeners;
	private List<TestListener> testListeners;
	private List<String> selectedTargets;
	private List<AbstractMonitoredTestMonitorListener> abstractMonitoredTestMonitorListeners;
	
	public AbstractMonitoredTest(String name) {
		super(name);
		this.abstractMonitoredTestListeners = new ArrayList<AbstractMonitoredTestListener>();
		this.testListeners = new ArrayList<TestListener>();
		this.selectedTargets = new ArrayList<String>();
		this.abstractMonitoredTestMonitorListeners = new ArrayList<AbstractMonitoredTestMonitorListener>();
	}

	public void addAbstractMonitoredTestListener(AbstractMonitoredTestListener listener) {
		this.abstractMonitoredTestListeners.add(listener);
	}
	
	public void removeAbstractMonitoredTestListener(AbstractMonitoredTestListener listener) {
		this.abstractMonitoredTestListeners.remove(listener);
	}
	
	public void addTestListener(TestListener listener) {
		this.testListeners.add(listener);
	}
	
	public void removeTestListener(TestListener listener) {
		this.testListeners.remove(listener);
	}
	
	public void updateDataListeners() {
		Iterator<AbstractMonitoredTestListener> iter = this.abstractMonitoredTestListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateData();
		}
	}
	
	public void updateTestListeners() {
		Iterator<TestListener> iter = this.testListeners.iterator();
		while (iter.hasNext()) {
			iter.next().renameTest(this.getName());
		}
	}
	
	public List<String> getSelectedTargets() {
		return this.selectedTargets;
	}

	@Override
	public void addAbstractMonitoredTestMonitorListener(
			AbstractMonitoredTestMonitorListener listener) {
		this.abstractMonitoredTestMonitorListeners.add(listener);
	}

	@Override
	public void removeAbstractMonitoredTestMonitorListener(
			AbstractMonitoredTestMonitorListener listener) {
		this.abstractMonitoredTestMonitorListeners.remove(listener);
	}
	
	public void updateDelayListeners(int delay) {
		Iterator<AbstractMonitoredTestMonitorListener> iter = this.abstractMonitoredTestMonitorListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateDelay(delay);
		}
	}
	
	public void updateSlaveCountListeners(int count) {
		Iterator<AbstractMonitoredTestMonitorListener> iter = this.abstractMonitoredTestMonitorListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateSlaveCount(count);
			System.out.print(" " + 1);
		}
		System.out.println("update slave count listeners");
	}
	
	public void updateSelectedTargets(List<String> targets, List<String> selectedTargets) {
		Iterator<AbstractMonitoredTestMonitorListener> iter = this.abstractMonitoredTestMonitorListeners.iterator();
		while (iter.hasNext()) {
			iter.next().updateTargets(targets, selectedTargets);
		}
	}
}
