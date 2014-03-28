package tools.widgets;

import javax.swing.JDialog;
import javax.swing.JFrame;

import shared.interfaces.IInstruction;

/**
 * Dialog to create an instruction
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public abstract class InstructionCreator extends JDialog {

    private boolean sendData;
    
    
    
    /* *********************************************************************
     * CONSTRUCTOR *********************************************************
     * *********************************************************************/
    
    /**
     * Constructor
     * @param parent the parent
     * @param title a title
     * @param modal true if it is modal
     */
    public InstructionCreator(JFrame parent, String title, boolean modal) {
	super(parent, title, modal);
	this.setSize(300, 160);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
    }
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    /**
     * Return the created instruction
     * @return the created instruction
     */
    public abstract IInstruction getCreatedInstruction();

    public void setSendData(boolean b) {
	sendData = b;
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
