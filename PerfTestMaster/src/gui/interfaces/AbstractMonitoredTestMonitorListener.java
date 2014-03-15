package gui.interfaces;

import java.util.List;

public interface AbstractMonitoredTestMonitorListener {
	
	public void updateTargets(List<String> targets, List<String> selectedTargets);
	
	public void updateDelay(int delay);
	
	public void updateMaxSlaveCount(int count);
	
	public void updateSlaveCount(int count);

}
