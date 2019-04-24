package br.com.pbd2019_1.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TObject extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private List<Object[]> t;
	private List<String> colunas;
	
	public TObject() {
		super();
		this.colunas = new ArrayList<>();
		this.t = new ArrayList<>();
	}
	
	public void addAll(List<Object[]> t) {
		
		if(t != null)
		{
			this.colunas.clear();
			for(int i = 0; i < t.size(); i++)
				colunas.add("VALUE COLUMN");
			
			this.t.clear();
			this.t.addAll(t);
			fireTableStructureChanged();
			fireTableDataChanged();
		}
	}
	
	public void remover(int index) {
		this.t.remove(index);
		fireTableDataChanged();
	}
	
	public Object[] getValor(int linha) {
		return t.get(linha);
	}
	
	public boolean addValor(Object[] t) {
		boolean b = this.t.add(t);
		if(b)
			fireTableRowsInserted(this.t.size() - 1, this.t.size() - 1);
		return b;
	}
	
	public List<Object[]> getList() {
		return t;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return colunas.get(column);
	}
	
	@Override
	public int getColumnCount() {return colunas.size();}
	
	@Override
	public int getRowCount() {return t.size();}
	
	public Object getValueAt(int linha, int coluna) 
	{
		Object o = t.get(linha);
		
		if(o instanceof Object[])
		{
			Object[] obj = (Object[]) o; 
			return ((Object[])obj)[coluna].toString();
		}
		else
			return o.toString();
	};
	
}
