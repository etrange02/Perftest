package gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonTableRenderer extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = 7480573914113345938L;

	public ButtonTableRenderer() {
		super();
        //setOpaque(true);
    }
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }

}
