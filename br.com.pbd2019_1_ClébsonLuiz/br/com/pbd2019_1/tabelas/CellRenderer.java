package br.com.pbd2019_1.tabelas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.pbd2019_1.view.Botao;

public class CellRenderer extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		if(value instanceof Botao) {
			Botao btn = (Botao) value;
			if(btn.getText().equalsIgnoreCase("ver"))
			{
				if(hasFocus)
					btn.setBackground(Color.GRAY.darker());
				else if(isSelected)
					btn.setBackground(Color.GRAY);
				else
					btn.setBackground(Color.lightGray);
				return btn;
			}
			/*else if(btn.getText().equalsIgnoreCase("x"))
			{
				if(hasFocus)
					btn.setBackground(Color.red);
				else if(isSelected)
					btn.setBackground(Color.red.brighter());
				else
					btn.setBackground(Color.red.brighter().brighter());
				return btn;
			}*/
			
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
