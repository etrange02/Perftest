package gui.monitoring.example;

import gui.monitoring.capacity.DelaysAveragesGraph;
import gui.monitoring.capacity.DelaysInfosProvider;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controls.cslavemanagement.SlaveManagementFacade;

public class FrameCapacityGraph extends JFrame {
	DelaysAveragesGraph graphPanel;

	public FrameCapacityGraph(
			int timeInterval, 
			SlaveManagementFacade slaveManagementFacade) throws Exception {
		
		this.graphPanel = 
				new DelaysAveragesGraph(
						timeInterval, 
						slaveManagementFacade);
	}
	
	public  DelaysInfosProvider getDelaysInfosProvider() {
		return graphPanel.getDelaysInfosProvider();
	}

	public void initComponents() throws Exception {

		this.setSize( 800, 600 );
		graphPanel.setSize( 500, 500 );
		this.getContentPane().add( graphPanel );
		this.setVisible( true );
		graphPanel.update();
	}

	@Override
	public void repaint() {

		try {
			super.repaint();
			graphPanel.update();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
