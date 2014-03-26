package gui.panels.monitoring.delays;

import gui.panels.monitoring.AbstractDisplayer;
import controls.cslavemanagement.SlaveManagementFacade;

public class DelaysAverageDisplayer extends AbstractDisplayer {

    public DelaysAverageDisplayer(SlaveManagementFacade slaveManagementFacade) {
	
	super(slaveManagementFacade, 
		new DelaysAveragesGraph(slaveManagementFacade, 30));
    }
}
