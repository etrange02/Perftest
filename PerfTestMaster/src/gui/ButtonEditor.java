package gui;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class ButtonEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -1144050381900470234L;
	protected JButton button;
	private int lastRow;
	private String text;

	public ButtonEditor(JCheckBox checkBox, String text) {
		super(checkBox);
		this.button = new JButton();
		this.button.setOpaque(true);
		this.lastRow = -1;
		this.text = text;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
		//this.button.setText( (value == null) ? "" : value.toString() );
		this.button.setText(text);
		this.lastRow = row;
		return this.button;
	}
	
	public JButton getJButton() {
		return this.button;
	}
	
	public int getLastRow() {
		return this.lastRow;
	}

}