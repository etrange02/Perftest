package gui.panels.monitoring.delays;

import controls.cslavemanagement.SlaveManagementFacade;
import gui.panels.monitoring.AbstractGraphPanel;
import gui.panels.monitoring.Displayer;

public class DelaysAverageDisplayer extends Displayer {

    public DelaysAverageDisplayer(SlaveManagementFacade slaveManagementFacade) {
	
	super(slaveManagementFacade, 
		new DelaysAveragesGraph(slaveManagementFacade, 30));
    }
}
