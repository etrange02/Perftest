package gui.panels.monitoring;

import javax.swing.JFrame;

import shared.Constants;
import controls.cslavemanagement.SlaveManagementFacade;

public abstract class Displayer extends JFrame implements Runnable {

    private SlaveManagementFacade slaveManagement;
    private AbstractGraphPanel graph;



    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZER ********************************************
     * *********************************************************************/

    public Displayer(
	    SlaveManagementFacade slaveManagementFacade,
	    AbstractGraphPanel graph) {

	this.slaveManagement = slaveManagementFacade;
	this.graph = graph;

	initComponents();
    }

    public void initComponents() {

	this.setSize( 800, 600 );
	graph.setSize( 500, 500 );
	this.getContentPane().add( graph );
	this.setVisible( true );
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
    }


    @Override
    public void run() {

	try {

	    slaveManagement.addSlaveListener(graph.getInfosProvider());

	    while(true) {
		Thread.sleep(
			(Constants.SECS_IN_INTERVAL_FOREACH_RESPPACK+1)*1000);
		graph.updatePanel();
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
