package gui.panels.monitoring;

import javax.swing.JPanel;

public abstract class AbstractGraphPanel extends JPanel {

    public abstract AbstractInfosProvider getInfosProvider();

    public abstract void updatePanel() throws Exception;
}
