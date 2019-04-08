package br.com.pbd2019_1.tabelas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		if(value instanceof JButton) {
			JButton btn = (JButton) value;
			if(hasFocus)
				btn.setBackground(Color.red);
			else if(isSelected)
				btn.setBackground(Color.red.brighter());
			else
				btn.setBackground(Color.red.brighter().brighter());
			return btn;
		}
		return super.getTableCellRendererComponent(
				table,
				value,
				isSelected,
				hasFocus,
				row,
				column);
		
	}
	
}
