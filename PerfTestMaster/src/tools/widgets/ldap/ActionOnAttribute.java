package tools.widgets.ldap;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author jean-luc.amitousa-mankoy@hotmail.fr
 * @version 1.0
 */
public class ActionOnAttribute extends JDialog {
    
    private JTextField attributeName;
    private JTextField attributeValue;
    private boolean sendData;



    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZER ********************************************
     * *********************************************************************/

    /**
     * 
     * @param parent the parent frame
     * @param title the title of the dialog
     * @param modal ???
     * @param initAttributeName the init name for this attribut. It can be null.
     * @param initAttributeValue  the init value for this attribut. It can be null.
     */
    public ActionOnAttribute(
	    JFrame parent, 
	    String title, 
	    boolean modal,
	    String initAttributeName,
	    String initAttributeValue) {

	super(parent,title,modal);

	this.setSize(300, 100);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	initPanel(
		initAttributeName == null ? "" : initAttributeName, 
			initAttributeValue == null ? "" : initAttributeValue);
    }

    private void initPanel(
	    String initAttributeName,
	    String initAttributeValue) {

	JPanel attributePanel = new JPanel(new BorderLayout());

	JPanel attributeNameGrid = new JPanel(new GridLayout(1, 1));
	attributeNameGrid.add(new JLabel("Attribute name"));
	this.attributeName = new JTextField(initAttributeName);
	attributeNameGrid.add(attributeName);

	JPanel attributeValueGrid = new JPanel(new GridLayout(1, 1));
	attributeValueGrid.add(new JLabel("Attribute value"));
	this.attributeValue = new JTextField(initAttributeValue);
	attributeValueGrid.add(attributeValue);

	JPanel control = new JPanel();
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");

	okButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		sendData = true;
		setVisible(false);
	    }
	});
	cancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
	    }
	});

	attributePanel.add(attributeNameGrid, BorderLayout.NORTH);
	attributePanel.add(attributeValueGrid, BorderLayout.SOUTH);

	control.add(okButton);
	control.add(cancelButton);
	
	this.add(attributePanel, BorderLayout.NORTH);
	this.add(control, BorderLayout.SOUTH);
    }



    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    public String getAttributeName() {
	return attributeName.getText();
    }

    public String getAtributeValue() {
	return attributeValue.getText();
    }



    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/

    /**
     * Show the dialog
     * @return true if the user clicks on OK
     */
    public boolean showDialog() {
	this.sendData = false;
	this.setVisible(true);
	return sendData;
    }
}
