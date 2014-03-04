package gui;

import gui.panels.SlavesPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tools.GUIConstants;

public class Frame extends JFrame {

	private static final long serialVersionUID = 4743270606172960944L;
	private SlavesPanel slavesPanel;
	private TestPlanManager testPlanManager;
	private TestPlanTree testPlanTree;

	public Frame() {
		super();
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
		this.testPlanTree = new TestPlanTree(this.testPlanManager);
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
		
		this.slavesPanel = new SlavesPanel();
		
		JPanel center = new JPanel();
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
		System.exit(0);
	}
	
	private void newTestMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void saveMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void saveAsMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void openMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void userManualMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
	
	private void aboutMenuItemAction(ActionEvent e) {
		System.exit(0);
	}
}
