package gui.monitoring.example;

import gui.monitoring.capacity.DelaysAveragesGraph;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameCapacityGraph extends JFrame {
	DelaysAveragesGraph graphPanel;
	
	public FrameCapacityGraph(DelaysAveragesGraph graphPanel) throws Exception {
		this.graphPanel = graphPanel;
		initComponents();
	}
	
	public void initComponents() throws Exception {
		
		this.setSize( 800, 600 );
		this.graphPanel.setSize( 500, 500 );
		this.getContentPane().add( this.graphPanel );
		this.setVisible( true );
		
		graphPanel.createGraph();
	}
}
