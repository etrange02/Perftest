package gui.panels.monitoring.example;

import shared.Constants;
import controls.cslavemanagement.SlaveManagementFacade;

public class Displayer implements Runnable {

	private SlaveManagementFacade slaveManagement;

	public Displayer(SlaveManagementFacade slaveManagementFacade) {
		this.slaveManagement = slaveManagementFacade;
	}

	@Override
	public void run() {

		boolean isInit = false;

		try {
			FrameCapacityGraph frame = new FrameCapacityGraph(slaveManagement);
			slaveManagement.addSlaveListener(frame.getDelaysInfosProvider());

			while(true) {
				
				Thread.sleep((Constants.SECS_IN_INTERVAL_FOREACH_RESPPACK+1)*1000);

				if(isInit) {
					frame.repaint();
				}
				else {
					frame.initComponents();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
