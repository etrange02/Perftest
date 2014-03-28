package tools.widgets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class InstructionAdder extends JDialog {

    private JTextField instructionName;
    private JComboBox<Object> instructionTypes;
    private boolean sendData;



    /* *********************************************************************
     * CONSTRUCTORS/INITIALIZER ********************************************
     * *********************************************************************/

    public InstructionAdder(JFrame parent, String title, boolean modal) {

	super(parent,title,modal);
	this.setSize(300, 110);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	initPanel();
    }

    private void initPanel() {

	JPanel selectInstructionTypeGrid = new JPanel(new GridLayout(1, 1));
	
	selectInstructionTypeGrid.add(new JLabel("Select an instruction type"));
	this.instructionTypes = new JComboBox<Object>(getInstructionsType());
	selectInstructionTypeGrid.add(this.instructionTypes);


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

	
	JPanel enterInstructionNameGrid = new JPanel(new GridLayout(1, 1));
	enterInstructionNameGrid.add(new JLabel("Instruction name"));
	instructionName = new JTextField("");
	enterInstructionNameGrid.add(instructionName);
	
	control.add(okButton);
	control.add(cancelButton);
	
	this.add(enterInstructionNameGrid, BorderLayout.NORTH);
	this.add(selectInstructionTypeGrid, BorderLayout.CENTER);
	this.add(control, BorderLayout.SOUTH);
    }



    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    protected abstract String[] getInstructionsType();

    public String getInstructionName() {
	return this.instructionName.getText();
    }
    
    /**
     * Returns the name of the selected instruction type
     * @return a String representation of the selected instruction type.
     */
    public String getSelectedInstructionType() {
	return this.instructionTypes.getSelectedItem().toString();
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
	return this.sendData;
    }
}
