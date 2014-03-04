package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tools.GUIConstants;

public abstract class AbstractTestPlanPanel extends JPanel {

	private static final long serialVersionUID = 6858271818355416805L;
	private JTextArea titleArea;
	private JPanel core;
	
	public AbstractTestPlanPanel() {
		super();
		this.initPanel();
	}
	
	public void setTitle(String title) {
		this.titleArea.setText(title);
	}
	
	private void initPanel() {
		this.titleArea = new JTextArea(GUIConstants.ABSTRACTTESTPLANPANEL_DEFAULT_TITLE);
		this.core = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(this.titleArea, BorderLayout.NORTH);
		this.add(this.core, BorderLayout.CENTER);
	}
	
	public JPanel getCore() {
		return this.core;
	}

}
