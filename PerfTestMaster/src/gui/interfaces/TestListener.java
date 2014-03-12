package gui.interfaces;

import controls.ctestplanmanagement.AbstractMonitoredTest;

public interface TestListener {

	public void addScalabilityTestListener(AbstractMonitoredTest abstractMonitoredTest);
	
	public void addWorkloadTestListener(AbstractMonitoredTest abstractMonitoredTest);
	
	public void renameTest(String newName);
}
