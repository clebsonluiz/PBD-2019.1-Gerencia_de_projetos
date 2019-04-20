package br.com.pbd2019_1.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.pbd2019_1.entidade.Entidade;

public abstract class TGenerica<T extends Entidade> extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private List<T> t;
	private String colunas[];
	
	public TGenerica(String colunas[]) {
		super();
		this.t = new ArrayList<T>();
		this.colunas = colunas;
	}
	
	public void addAll(List<T> t) {
		if(t != null)
		{
			this.t.clear();
			this.t.addAll(t);
			fireTableDataChanged();
		}
	}
	
	public void remover(int index) {
		this.t.remove(index);
		fireTableDataChanged();
	}
	
	public T getValor(int linha) {
		return t.get(linha);
	}
	
	public boolean addValor(T t) {
		return this.t.add(t);
	}
	
	public List<T> getList() {
		return t;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getColumnCount() {return colunas.length;}
	
	@Override
	public int getRowCount() {return t.size();}
	
	abstract public Object getValueAt(int linha, int coluna);
	
}
