package br.com.pbd2019_1.tabelas;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.utils.DateUtil;

public class TColaborador extends TGenerica<Colaborador>{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> combo = new JComboBox<>(new String[] {
			"Visitante", "Participante", "Administrador"
	});
	
	public TColaborador() {
		super(new String[]{"Nome","Data ingressão","Privilegio"});
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if(columnIndex == 2) {
			getList().get(rowIndex).setPrivilegio((String)value);
			fireTableCellUpdated(rowIndex, columnIndex);
			fireTableDataChanged();
		}
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getPessoa().getNome();
		case 1:
			return DateUtil.getDateString("dd-MM-yyyy",
					getList().get(linha).getData_ingresso());
		case 2:
			return getList().get(linha).getPrivilegio();
		}
		return null;
	}
	
	public DefaultCellEditor getCellEditor() {
		return new DefaultCellEditor(combo);
	}

}
