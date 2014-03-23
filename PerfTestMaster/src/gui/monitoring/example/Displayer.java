package gui.monitoring.example;

import gui.monitoring.capacity.DelaysAveragesGraph;
import controls.cslavemanagement.SlaveManagementFacade;

public class Displayer implements Runnable {

	private SlaveManagementFacade slaveManagementFacade;

	public Displayer(SlaveManagementFacade slaveManagementFacade) {
		this.slaveManagementFacade = slaveManagementFacade;
	}

	@Override
	public void run() {

		try {
			FrameCapacityGraph frame = new FrameCapacityGraph(
					1, slaveManagementFacade);
			slaveManagementFacade.addSlaveListener(frame.getDelaysInfosProvider());
			Thread.sleep(6000);

			frame.initComponents();
			
			while(true) {
				Thread.sleep(6000);
				frame.repaint();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
