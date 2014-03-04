package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPanel extends JPanel {

	private static final long serialVersionUID = -288280364414876606L;
	private JTextArea titleArea;

	public TestPanel() {
		this(GUIConstants.TESTPANEL_DEFAULT_TITLE);
	}

	public TestPanel(String title) {
		super();
		this.initPanel(title);
	}
	
	private void initPanel(String title) {
		this.setLayout(new BorderLayout());
		
		this.titleArea = new JTextArea(title);
		
		this.add(this.titleArea, BorderLayout.NORTH);
		
	}
	
	public void setTitle(String title) {
		this.titleArea.setText(title);
	}
}
