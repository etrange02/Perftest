package gui;

import gui.panels.InstructionPanel;
import gui.panels.TestPanel;
import gui.panels.protocols.ldap.LDAPTestPlanPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import tools.GUIConstants;

public class TestPlanTree extends JTree {

	private static final long serialVersionUID = 3049618493902762703L;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JPopupMenu popupMenuEmptyTree;
	private JPopupMenu popupMenuTestPlan;
	private JPopupMenu popupMenuTest;
	private TestPlanManager testPlanManager;
	
	public TestPlanTree(TestPlanManager testPlanPanel) {
		super();
		this.testPlanManager = testPlanPanel;
		this.initPopupMenu();
		this.initTree();
	}
	
	private void initTree() {
		this.setPreferredSize(new Dimension(GUIConstants.TREE_SIZE_X, GUIConstants.TREE_SIZE_Y));
		this.root = new DefaultMutableTreeNode(GUIConstants.TREE_DEFAULT_ROOT);
		this.model = new DefaultTreeModel(root);
		this.setModel(this.model);
		this.setRootVisible(false);
		
		this.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				treeValueChanged(e);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				treeMousePressed(e);
			}
		});
	}
	
	private void initPopupMenu() {
		this.popupMenuEmptyTree = new JPopupMenu();
		JMenuItem createTestPlanMenuItem = new JMenuItem(GUIConstants.TREE_CREATE_TESTPLAN);
		createTestPlanMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTestPlan(e);
			}
		});
		this.popupMenuEmptyTree.add(createTestPlanMenuItem);
		
		this.popupMenuTestPlan = new JPopupMenu();
		JMenuItem renameTestPlanMenuItem = new JMenuItem(GUIConstants.TREE_RENAME_TESTPLAN);
		JMenuItem addScalabilityTestMenuItem = new JMenuItem(GUIConstants.TREE_ADD_SCALABILITY_TEST);
		JMenuItem addWorkloadTestMenuItem = new JMenuItem(GUIConstants.TREE_ADD_WORKLOAD_TEST);
		
		addScalabilityTestMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScalabilityTest(e);
			}
		});
		addWorkloadTestMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWorkloadTest(e);
			}
		});
		renameTestPlanMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renameTestPlan(e);
			}
		});
		
		this.popupMenuTestPlan.add(addScalabilityTestMenuItem);
		this.popupMenuTestPlan.add(addWorkloadTestMenuItem);
		this.popupMenuTestPlan.add(renameTestPlanMenuItem);
		
		this.popupMenuTest = new JPopupMenu();
		JMenuItem renameTestMenuItem = new JMenuItem(GUIConstants.TREE_RENAME_TEST);
		renameTestMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renameTest(e);
			}
		});
		this.popupMenuTest.add(renameTestMenuItem);
	}
	
	private void treeValueChanged(TreeSelectionEvent e) {
		System.out.println("valueChanged");
		int[] selected = this.getSelectionRows();
		if (selected.length > 0/* && this.root != e.getPath().getLastPathComponent()*/ ) {
			this.testPlanManager.showAssociatedPanel((DefaultMutableTreeNode) e.getPath().getLastPathComponent());
		}
	}
	
	private void treeMousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			int row = this.getRowForLocation(e.getX(), e.getY());
			
			TreePath path = this.getPathForLocation(e.getX(), e.getY());			
			
			if (row != -1) {
				this.setSelectionRow(row);
				switch (path.getPathCount()) {
					case 1:
						this.popupMenuTestPlan.show(this, e.getX(), e.getY());
						break;
					case 2:
						this.popupMenuTest.show(this, e.getX(), e.getY());
						break;
					case 3:
						//this.popupMenuInstruction.show(this, e.getX(), e.getY());
						break;
					default:
						break;
				}
			} else if (!this.isRootVisible()) {
				this.popupMenuEmptyTree.show(this, e.getX(), e.getY());
			}
		}
	}
	
	private void createTestPlan(ActionEvent e) {
		this.root.setUserObject("Je suis le noeud");
		this.setRootVisible(true);
		this.testPlanManager.addNode(this.root, new LDAPTestPlanPanel());
	}
	
	private void addScalabilityTest(ActionEvent e) {
		DefaultMutableTreeNode test = new DefaultMutableTreeNode("" + System.currentTimeMillis());
		DefaultMutableTreeNode instruction = new DefaultMutableTreeNode("Instructions");
		DefaultMutableTreeNode monitoring = new DefaultMutableTreeNode("Monitoring");
		
		test.add(instruction);
		test.add(monitoring);
		//this.root.add(test);
		((DefaultTreeModel) this.getModel()).insertNodeInto(test, root, 0);		

		//this.expandPath(this.getPathForRow(0));
		expandAllNodes();
		
		this.testPlanManager.addNode(test, new TestPanel("Scalability test"));
		this.testPlanManager.addNode(instruction, new InstructionPanel());
		this.testPlanManager.addNode(monitoring, new JPanel());
		
		this.testPlanManager.showAssociatedPanel(test);
	}
	
	private void addWorkloadTest(ActionEvent e) {
		DefaultMutableTreeNode test = new DefaultMutableTreeNode("" + System.currentTimeMillis());
		DefaultMutableTreeNode instruction = new DefaultMutableTreeNode("Instructions");
		DefaultMutableTreeNode monitoring = new DefaultMutableTreeNode("Monitoring");
		
		test.add(instruction);
		test.add(monitoring);
		//this.root.add(test);
		((DefaultTreeModel) this.getModel()).insertNodeInto(test, root, 0);
		
		//this.expandPath(this.getPathForRow(0));
		expandAllNodes();
		
		this.testPlanManager.addNode(test, new TestPanel("Workload test"));
		this.testPlanManager.addNode(instruction, new InstructionPanel());
		this.testPlanManager.addNode(monitoring, new JPanel());
		
		this.testPlanManager.showAssociatedPanel(test);
	}
	
	private void renameTestPlan(ActionEvent e) {
		System.out.println("renameTestPlan");
	}
	
	private void renameTest(ActionEvent e) {
		System.out.println("renameTest");
	}
	
	private void expandAllNodes() {
		for (int i = 0; i < this.getRowCount(); ++i) {
	         this.expandRow(i);
		}
	}
	
}
