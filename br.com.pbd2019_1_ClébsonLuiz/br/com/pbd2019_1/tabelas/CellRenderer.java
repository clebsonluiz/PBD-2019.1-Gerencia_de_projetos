package br.com.pbd2019_1.tabelas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
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
				{
					btn.setBackground(Botao.VERDE);
					btn.setForeground(Color.white);
					btn.setFont(Botao.FONT_CLICK);
				}
				else if(isSelected)
				{
					btn.setBackground(Botao.MARROM);
					btn.setForeground(Color.white);
					btn.setFont(Botao.FONT_PADRAO);
				}
				else
				{
					btn.setBackground(Botao.VERDE_ESCURO);
					btn.setForeground(Color.white);
					btn.setFont(Botao.FONT_PADRAO);
				}
				return btn;
			}
			
			else if(value instanceof JComboBox) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cmbx = (JComboBox<String>) value;
				if(hasFocus) 
					cmbx.setBackground(Color.GRAY.darker());
				else if(isSelected) 
					cmbx.setBackground(Color.GRAY);
				else
					cmbx.setBackground(Color.lightGray);
				return cmbx;
			}
			
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
