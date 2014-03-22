package monitoring.example;

import javax.swing.JFrame;
import javax.swing.JPanel;

import monitoring.capacity.CapacityGraph;

public class FrameCapacityGraph extends JFrame {
	CapacityGraph graphPanel;
	
	public FrameCapacityGraph(CapacityGraph graphPanel) throws Exception {
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
