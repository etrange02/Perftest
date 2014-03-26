package gui.panels.monitoring.variance;

import gui.panels.monitoring.AbstractDisplayer;
import controls.cslavemanagement.SlaveManagementFacade;

public class VarianceDisplayer extends AbstractDisplayer {

    public VarianceDisplayer(SlaveManagementFacade slaveManagementFacade) {
	
	super(slaveManagementFacade, new VarianceGraph(slaveManagementFacade));
    }

}
