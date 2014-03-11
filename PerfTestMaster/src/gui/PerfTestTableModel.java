package gui;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class PerfTestTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -6144226754812127470L;
	private Object[][] data;
	private String[] title;
	
	public PerfTestTableModel(String[] titles, Object[][] data) {
		this.title = titles;
		this.data = new Object[0][0];
		this.initTable(data);
	}
	
	private void initTable(Object[][] data) {
		for (Object[] objects : data) {
			this.addRow(objects);
		}
	}
	
	public String getColumnName(int columnIndex) {
		return this.title[columnIndex];
	}

	public int getColumnCount() {
		return this.title.length;
	}

	public int getRowCount() {
		return this.data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		this.data[rowIndex][columnIndex] = value;
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		if (this.getRowCount() == 0)
			return null;
		return this.data[0][columnIndex].getClass();
	}
	
	public void removeRow(int rowIndex) {
		int rowNb = this.getRowCount() -1;
		int columnNb = this.getColumnCount();
		int index = 0, i = 0;
		
		Object temp[][] = new Object[rowNb][columnNb];
		
		for (Object[] value : this.data) {
			if (rowIndex != index)
			{
				value[0] = "" + (i+1);
				temp[i++] = value;
			}
			++index;
		}
		
		this.data = temp;
		temp = null;
		this.fireTableDataChanged();
	}
	
	public void addRow(Object[] data) {
		int rowNb = this.getRowCount() + 1;
		int columnNb = this.getColumnCount();
		int index = 0;
		int i = 1;
		
		Object temp[][] = new Object[rowNb][columnNb];
		
		for (Object[] value : this.data)
			temp[index++] = value;
		
		temp[index][0] = "" + (this.getRowCount() + 1);
		for (Object d : data) {
			temp[index][i++] = d;
		}
		this.data = temp;
		temp = null;
		this.fireTableDataChanged();
	}
	
	public Object[] getValuesInColumn(int columnIndex) {
		if (columnIndex >= this.getColumnCount())
			return new Object[0];
		
		Object[] temp = new Object[this.getRowCount()];
		int index = 0;
		for (Object[] o : this.data) {
			temp[index] = o[columnIndex];
		}
		return temp;
	}
	
	public void clear() {
		this.data = new Object[0][0];
	}

}
