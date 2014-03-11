package gui.interfaces;

public interface TestListener {

	public void addScalabilityTestListener(String name);
	
	public void addWorkloadTestListener(String name);
	
	public void renameTest(String oldName, String newName, boolean cascade);
}
