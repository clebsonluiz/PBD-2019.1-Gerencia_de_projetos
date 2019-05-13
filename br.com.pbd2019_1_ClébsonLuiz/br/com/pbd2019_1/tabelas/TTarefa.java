package br.com.pbd2019_1.tabelas;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import br.com.pbd2019_1.entidade.Tarefa;
import br.com.pbd2019_1.view.Botao;

public class TTarefa extends TGenerica<Tarefa>{

	private static final long serialVersionUID = 1L;

	private JComboBox<String> combo = new JComboBox<>(new String[] {
			"Baixa", "Media", "Alta"
	});
	
	private Botao btn = new Botao("Ver");
	
	public TTarefa() {
		super(new String[] {"Nome", "Horario", "Prioridade", "Finalizada", ""});
	}

	public Class<?> getColumnClass(int columnIndex){
		switch (columnIndex) {
		case 0:
			return Object.class;
		case 1:
			return Object.class;
		case 2:
			return Object.class;
		case 3:
			return Boolean.class;
		case 4:
			return Object.class;
		}
		return null;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 2 || columnIndex == 3);
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if(columnIndex == 2) {
			getList().get(rowIndex).setPrioridade((String)value);
			fireTableCellUpdated(rowIndex, columnIndex);
			fireTableDataChanged();
		}
		else if(columnIndex == 3) {
			getList().get(rowIndex).setConcluida(((Boolean)value).booleanValue());
			fireTableCellUpdated(rowIndex, columnIndex);
			fireTableDataChanged();
		}
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return getList().get(linha).getHorario_tarefa();
		case 2:
			return getList().get(linha).getPrioridade();
		case 3:
			return getList().get(linha).isConcluida();
		case 4:
			return btn;
		}
		return null;
	}
	
	public DefaultCellEditor getCellEditor() {
		return new DefaultCellEditor(combo);
	}

	public JComboBox<String> getCombo() {
		return combo;
	}
	
}
