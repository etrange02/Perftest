package gui;

import gui.interfaces.TestListener;
import gui.interfaces.TestPlanListener;
import gui.panels.AbstractTestPlanPanel;
import gui.panels.InstructionPanel;
import gui.panels.TestPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import controls.ctestplanmanagement.AbstractMonitoredTest;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;
import tools.GUIConstants;
import tools.GUIFactory;
import tools.widgets.TestPlanCreator;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestPlanTree extends JTree implements TestPlanListener, TestListener {

	private static final long serialVersionUID = 3049618493902762703L;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JPopupMenu popupMenuEmptyTree;
	private JPopupMenu popupMenuTestPlan;
	private JPopupMenu popupMenuTest;
	private TestPlanManager testPlanManager;
	private ITestPlanManagement testPlanManagement;
	private JFrame frame;
	
	public TestPlanTree(JFrame frame, TestPlanManager testPlanPanel, ITestPlanManagement testPlanManagement) {
		super();
		this.testPlanManager = testPlanPanel;
		this.testPlanManagement = testPlanManagement;
		this.testPlanManagement.addPlanTestListener(this);
		this.testPlanManagement.addTestListener(this);
		this.frame = frame;
		
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
		TestPlanCreator creator = GUIFactory.testPlanCreator(this.frame, "Test plan creation", true, this.testPlanManagement.getAvailableProtocols());
		if (creator.showDialog()) {
			if (null != this.testPlanManagement.addNewTestPlan(creator.getSelectedProtocol()));
				System.out.println("newTestPlanMenuItemAction");
		}
	}
	
	private void addScalabilityTest(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "A test name", "Test creation", JOptionPane.PLAIN_MESSAGE);
		if (null == input || input.isEmpty())
			return;
		this.testPlanManagement.addNewScalabilityTest(input);
	}
	
	private void addWorkloadTest(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "A test name", "Test creation", JOptionPane.PLAIN_MESSAGE);
		if (null == input || input.isEmpty())
			return;
		this.testPlanManagement.addNewWorkloadTest(input);
	}
	
	private void renameTestPlan(ActionEvent e) {
		System.out.println("renameTestPlan");
		
		String input = JOptionPane.showInputDialog(null, "A new name", "Rename", JOptionPane.PLAIN_MESSAGE);
		if (null == input || input.isEmpty())
			return;
		this.testPlanManagement.renameTestPlan(input);
		this.updateUI();
	}
	
	private void renameTest(ActionEvent e) {
		System.out.println("renameTest");
		String input = (String) JOptionPane.showInputDialog(null, "A new name", "Rename", JOptionPane.PLAIN_MESSAGE, null, null, this.getSelectionPath().getLastPathComponent().toString());
		if (null == input || input.isEmpty())
			return;
		this.testPlanManagement.renameTest(this.getSelectionPath().getLastPathComponent().toString(), input, false);
	}

	@Override
	public void updatePlanTestName(String name) {
		this.root.setUserObject(name);
		
		if (!this.isRootVisible()) {
			this.setRootVisible(true);
			AbstractTestPlanPanel testPlanPanel = this.testPlanManagement.getUsedProtocolParser().createNewTestPlanPanel(this.testPlanManagement);
			this.testPlanManager.addNode(this.root, testPlanPanel);
		}
		this.updateUI();
	}

	@Override
	public void addScalabilityTestListener(AbstractMonitoredTest abstractMonitoredTest) {
		DefaultMutableTreeNode test = new DefaultMutableTreeNode(abstractMonitoredTest.getName());
		DefaultMutableTreeNode instruction = new DefaultMutableTreeNode("Instructions");
		DefaultMutableTreeNode monitoring = new DefaultMutableTreeNode("Monitoring");
		
		test.add(instruction);
		test.add(monitoring);
		
		((DefaultTreeModel) this.getModel()).insertNodeInto(test, root, root.getChildCount());
		
		this.testPlanManager.addNode(test, new TestPanel("Scalability test", abstractMonitoredTest.getName(), "Scalability"));
		this.testPlanManager.addNode(instruction, new InstructionPanel(this.frame, this.testPlanManagement, abstractMonitoredTest));
		this.testPlanManager.addNode(monitoring, new JPanel());
		
		TreePath testPath = new TreePath(test.getPath());
		this.expandPath(testPath);
		this.setSelectionPath(testPath);
		this.updateUI();
	}

	@Override
	public void addWorkloadTestListener(AbstractMonitoredTest abstractMonitoredTest) {
		DefaultMutableTreeNode test = new DefaultMutableTreeNode(abstractMonitoredTest.getName());
		DefaultMutableTreeNode instruction = new DefaultMutableTreeNode("Instructions");
		DefaultMutableTreeNode monitoring = new DefaultMutableTreeNode("Monitoring");
		
		test.add(instruction);
		test.add(monitoring);
		((DefaultTreeModel) this.getModel()).insertNodeInto(test, root, root.getChildCount());
		
		this.testPlanManager.addNode(test, new TestPanel("Workload test", abstractMonitoredTest.getName(), "Workload"));
		this.testPlanManager.addNode(instruction, new InstructionPanel(this.frame, this.testPlanManagement, abstractMonitoredTest));
		this.testPlanManager.addNode(monitoring, new JPanel());
		
		TreePath testPath = new TreePath(test.getPath());
		this.expandPath(testPath);
		this.setSelectionPath(testPath);
		this.updateUI();
	}

	@Override
	public void renameTest(String oldName, String newName, boolean cascade) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();
		node.setUserObject(newName);
		this.updateUI();
	}
	
}
