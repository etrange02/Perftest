package gui;

import gui.panels.SlavesPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.tree.TreePath;

import tools.GUIConstants;
import tools.GUIFactory;
import tools.widgets.TestCreator;
import tools.widgets.TestPlanCreator;
import controls.ctestplanmanagement.interfaces.ITestPlanManagement;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Frame extends JFrame {

	private static final long serialVersionUID = 4743270606172960944L;
	private SlavesPanel slavesPanel;
	private TestPlanManager testPlanManager;
	private TestPlanTree testPlanTree;
	private ITestPlanManagement testPlanManagement;

	public Frame(ITestPlanManagement testPlanManagement) {
		super();
		this.testPlanManagement = testPlanManagement;
		this.initialize();
		this.setVisible(true);
	}
	
	private void initialize() {
		this.setTitle(GUIConstants.FRAME_TITLE);
		this.setSize(GUIConstants.FRAME_SIZE_X, GUIConstants.FRAME_SIZE_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.initMenu();
		this.initToolsBar();
		this.initPanels();
	}
	
	private void initMenu() {
		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
		
		// File menu
		JMenu fileMenu = new JMenu(GUIConstants.MENU_FILE);
		JMenu newMenu = new JMenu(GUIConstants.MENU_FILE_NEW);
		JMenuItem newTestPlanMenuItem = new JMenuItem(GUIConstants.MENU_FILE_NEW_TESTPLAN);
		JMenuItem newTestMenuItem = new JMenuItem(GUIConstants.MENU_FILE_NEW_TEST);
		JMenuItem saveMenuItem = new JMenuItem(GUIConstants.MENU_FILE_SAVE);
		JMenuItem saveAsMenuItem = new JMenuItem(GUIConstants.MENU_FILE_SAVEAS);
		JMenuItem openMenuItem = new JMenuItem(GUIConstants.MENU_FILE_OPEN);
		JMenuItem exitMenuItem = new JMenuItem(GUIConstants.MENU_FILE_EXIT);
		
		newTestPlanMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTestPlanMenuItemAction(e);
			}
		});
		newTestMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTestMenuItemAction(e);
			}
		});
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveMenuItemAction(e);
			}
		});
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsMenuItemAction(e);
			}
		});
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMenuItemAction(e);
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMenuItemAction(e);
			}
		});
		
		newMenu.add(newTestPlanMenuItem);
		newMenu.add(newTestMenuItem);
		fileMenu.add(newMenu);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(exitMenuItem);
		
		// Help menu
		JMenu helpMenu = new JMenu(GUIConstants.MENU_HELP);
		JMenuItem userManualMenuItem = new JMenuItem(GUIConstants.MENU_HELP_USERMANUAL);
		JMenuItem aboutMenuItem = new JMenuItem(GUIConstants.MENU_HELP_ABOUT);
		
		userManualMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userManualMenuItemAction(e);
			}
		});
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutMenuItemAction(e);
			}
		});
		
		helpMenu.add(userManualMenuItem);
		helpMenu.add(aboutMenuItem);
		
		//
		bar.add(fileMenu);
		bar.add(helpMenu);
	}
	
	private void initToolsBar() {
		JToolBar toolBar = new JToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);

		JButton createButton = new JButton("Create");
		JButton runButton = new JButton("Run");
		JButton addButton = new JButton("Add");
		JButton stopButton = new JButton("Stop");
		JButton deployButton = new JButton("Deploy");
		JButton monitorButton = new JButton("Monitor");


		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == testPlanManagement.getTestPlan()) {
					newTestPlanMenuItemAction(e);
				} else {
					newTestMenuItemAction(e);
				}
			}
		});
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath[] paths = testPlanTree.getSelectionPaths();
				if (null == paths)// || paths.length <= 1)
					return;
				if (paths[0].getPathCount() < 2)
					return;
				System.out.println("Run the test '" + paths[0].getPath()[1] + "'");
				System.out.println("Pust all selected slaves in running mode");
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Puts a slave in running mode");
			}
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop the current running test");
			}
		});
		deployButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath path = testPlanTree.getSelectionPath();
				if (null == path)// || paths.length <= 1)
					return;
				if (path.getPathCount() < 2)
					return;
				System.out.println("Deploy the test '" + path.getPath()[1] + "'");
			}
		});
		monitorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testPlanTree.selectNodeByName("test");
			}
		});

		//runButton.setEnabled(false);
		//stopButton.setEnabled(false);
		//deployButton.setEnabled(false);
		//monitorButton.setEnabled(false);

		toolBar.add(createButton);
		toolBar.add(runButton);
		toolBar.add(addButton);
		toolBar.add(stopButton);
		toolBar.add(deployButton);
		toolBar.add(monitorButton);
		toolBar.setVisible(true);
	}
	
	private void initPanels() {
		JPanel container = new JPanel(new BorderLayout());
		
		JButton slavesButton = new JButton(GUIConstants.LEFT_SLAVES);
		slavesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSlavePanel();
			}
		});
		
		this.testPlanManager = new TestPlanManager();
		this.testPlanTree = new TestPlanTree(this, this.testPlanManager, this.testPlanManagement);
		this.testPlanTree.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {				
			}
			
			public void focusGained(FocusEvent e) {
				showTestPlanPanel();
			}
		});
		
		container.add(slavesButton, BorderLayout.NORTH);
		container.add(this.testPlanTree, BorderLayout.CENTER);
		this.add(container, BorderLayout.WEST);
		
		this.slavesPanel = new SlavesPanel(this.testPlanManagement.getSlaveManagement());
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		center.add(this.slavesPanel);
		center.add(this.testPlanManager);
		this.slavesPanel.setVisible(false);
		this.testPlanManager.setVisible(false);
		this.add(center, BorderLayout.CENTER);
	}
	
	private void showSlavePanel() {
		this.slavesPanel.setVisible(true);
		this.testPlanManager.setVisible(false);
	}
	
	private void showTestPlanPanel() {
		this.slavesPanel.setVisible(false);
		this.testPlanManager.setVisible(true);
	}
	
	private void exitMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void newTestPlanMenuItemAction(ActionEvent e) {
		TestPlanCreator creator = GUIFactory.testPlanCreator(this, "Test plan creation", true, this.testPlanManagement.getAvailableProtocols());
		if (creator.showDialog()) {
			if (null != this.testPlanManagement.addNewTestPlan(creator.getSelectedProtocol()));
				System.out.println("newTestPlanMenuItemAction-" + creator.getSelectedProtocol());
		}
	}
	
	private void newTestMenuItemAction(ActionEvent e) {
		TestCreator creator = GUIFactory.testCreator(this, "Test creator", true);
		if (creator.showDialog()) {
			if (creator.isScalabilityTest()) {
				this.testPlanManagement.addNewScalabilityTest(creator.getTestName());
			} else {
				this.testPlanManagement.addNewWorkloadTest(creator.getTestName());
			}
			System.out.println("newTestMenuItemAction");
		}
	}
	
	private void saveMenuItemAction(ActionEvent e) {
		System.out.println("saveMenuItemAction");
	}
	
	private void saveAsMenuItemAction(ActionEvent e) {
		System.out.println("saveAsMenuItemAction");
	}
	
	private void openMenuItemAction(ActionEvent e) {
		System.out.println("openMenuItemAction");
	}
	
	private void userManualMenuItemAction(ActionEvent e) {
		System.out.println("userManualMenuItemAction");
	}
	
	private void aboutMenuItemAction(ActionEvent e) {
		System.out.println("aboutMenuItemAction");
		JOptionPane.showMessageDialog(null, "David & Jean-Luc", "About PerfTest", JOptionPane.PLAIN_MESSAGE);
	}
}
