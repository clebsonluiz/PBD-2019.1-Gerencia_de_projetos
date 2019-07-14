package br.com.pbd2019_1.tabelas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.pbd2019_1.entidade.View;
import br.com.pbd2019_1.view.Botao;

public abstract class TView<T extends View> extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;
	private Botao btn = new Botao("Ver", Color.green.brighter().brighter());
	
	private List<T> t;
	private String colunas[];
	
	public TView(String colunas[]) {
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
		boolean b = this.t.add(t);
		if(b)
			fireTableRowsInserted(this.t.size() - 1, this.t.size() - 1);
		return b;
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

	public Botao getBtn() {return btn;}
}
