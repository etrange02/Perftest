package gui.panels.monitoring.example;

import gui.panels.monitoring.delays.DelaysAveragesGraph;
import gui.panels.monitoring.delays.DelaysInfosProvider;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controls.cslavemanagement.SlaveManagementFacade;

public class FrameCapacityGraph extends JFrame {
	DelaysAveragesGraph graphPanel;

	public FrameCapacityGraph(SlaveManagementFacade slaveManagementFacade) {
		
		this.graphPanel = new DelaysAveragesGraph(slaveManagementFacade);

	}
	
	public  DelaysInfosProvider getDelaysInfosProvider() {
		return graphPanel.getDelaysInfosProvider();
	}

	public void initComponents() throws Exception {

		this.setSize( 800, 600 );
		graphPanel.setSize( 500, 500 );
		this.getContentPane().add( graphPanel );
		this.setVisible( true );
		
		addWindowListener( new java.awt.event.WindowAdapter()
		{
			public void windowClosing( WindowEvent windowEvent )
			{
				exitForm( windowEvent );
			}
		});
		
		graphPanel.update();		
	}

	private void exitForm( WindowEvent windowEvent )
	{
		System.exit( 0 );
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
