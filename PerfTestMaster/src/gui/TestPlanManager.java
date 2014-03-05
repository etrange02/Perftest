package gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPlanManager extends JPanel {

	private static final long serialVersionUID = 4272011867794858445L;
	private JPanel associatedVisiblePanel;
	private Map<DefaultMutableTreeNode, JPanel> mapNodePanel;
	
	public TestPlanManager() {
		super();
		this.mapNodePanel = new HashMap<DefaultMutableTreeNode, JPanel>();
	}

	public void hideVisibleAssociatedPanel() {
		if (this.associatedVisiblePanel == null)
			return;
		this.associatedVisiblePanel.setVisible(false);
		this.associatedVisiblePanel = null;
	}
	
	public void addNode(DefaultMutableTreeNode node) {
		this.addNode(node, null);
	}
	
	public void addNode(DefaultMutableTreeNode node, JPanel panel) {
		if (panel != null) {
			this.add(panel);
			panel.setVisible(false);
		}
		this.mapNodePanel.put(node, panel);
	}
	
	public void showAssociatedPanel(DefaultMutableTreeNode node) {
		hideVisibleAssociatedPanel();		
		if (this.mapNodePanel.containsKey(node)) {
			this.associatedVisiblePanel = this.mapNodePanel.get(node);
			if (this.associatedVisiblePanel != null) {
				this.associatedVisiblePanel.setVisible(true);
				//return;
			}
		}		
	}

}
