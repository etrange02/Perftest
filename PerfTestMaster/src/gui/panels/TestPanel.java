package gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tools.GUIConstants;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPanel extends JPanel {

	private static final long serialVersionUID = -288280364414876606L;
	private JTextArea titleArea;
	private JTextArea typeArea;
	private JTextArea lastRunArea;
	private JTextField nameArea;

	public TestPanel() {
		this(GUIConstants.TESTPANEL_DEFAULT_TITLE, "", "");
	}

	public TestPanel(String title, String name, String type) {
		super();
		this.initPanel(title, name, type);
	}
	
	private void initPanel(String title, String name, String type) {
		this.setLayout(new BorderLayout());
		
		this.titleArea = new JTextArea(title);
		this.nameArea = new JTextField(name);
		this.typeArea = new JTextArea(type);
		this.lastRunArea = new JTextArea("Unknown");
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(this.titleArea);
		this.add(titlePanel, BorderLayout.NORTH);
		this.titleArea.setFont(this.titleArea.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel horiz = new JPanel();
		JPanel grid = new JPanel(new GridLayout(3, 2));
		grid.add(new JTextArea("Name"));
		grid.add(this.nameArea);
		grid.add(new JTextArea("Type"));
		grid.add(this.typeArea);
		grid.add(new JTextArea("Last run"));
		grid.add(this.lastRunArea);
		horiz.add(grid);
		this.add(horiz, BorderLayout.CENTER);
	}
	
	public void setTitle(String title) {
		this.titleArea.setText(title);
	}
	
	public void setName(String name) {
		this.nameArea.setText(name);
	}
	
	public void setLastRun(String lastRun) {
		this.lastRunArea.setText(lastRun);
	}
}
