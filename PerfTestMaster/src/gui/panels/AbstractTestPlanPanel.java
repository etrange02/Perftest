package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
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
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		this.titleArea = new JTextArea(GUIConstants.ABSTRACTTESTPLANPANEL_DEFAULT_TITLE);
		titlePanel.add(this.titleArea);
		this.add(titlePanel, BorderLayout.NORTH);
		this.titleArea.setFont(this.titleArea.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		this.core = new JPanel();		
		this.add(this.core, BorderLayout.CENTER);
	}
	
	public JPanel getCore() {
		return this.core;
	}

}
