package gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JLabel titleLabel;
	private JLabel typeLabel;
	private JLabel lastRunLabel;
	private JTextField nameField;

	public TestPanel() {
		this(GUIConstants.TESTPANEL_DEFAULT_TITLE, "", "");
	}

	public TestPanel(String title, String name, String type) {
		super();
		this.initPanel(title, name, type);
	}
	
	private void initPanel(String title, String name, String type) {
		this.setLayout(new BorderLayout());
		
		this.titleLabel = new JLabel(title);
		this.nameField = new JTextField(name);
		this.typeLabel = new JLabel(type);
		this.lastRunLabel = new JLabel("Unknown");
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(this.titleLabel);
		this.add(titlePanel, BorderLayout.NORTH);
		this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(GUIConstants.FRAME_TITLE_FONT_SIZE));
		
		JPanel horiz = new JPanel();
		JPanel grid = new JPanel(new GridLayout(3, 2));
		grid.add(new JLabel("Name"));
		grid.add(this.nameField);
		grid.add(new JLabel("Type"));
		grid.add(this.typeLabel);
		grid.add(new JLabel("Last run"));
		grid.add(this.lastRunLabel);
		horiz.add(grid);
		this.add(horiz, BorderLayout.CENTER);
	}
	
	public void setTitle(String title) {
		this.titleLabel.setText(title);
	}
	
	public void setName(String name) {
		this.nameField.setText(name);
	}
	
	public void setLastRun(String lastRun) {
		this.lastRunLabel.setText(lastRun);
	}
}
