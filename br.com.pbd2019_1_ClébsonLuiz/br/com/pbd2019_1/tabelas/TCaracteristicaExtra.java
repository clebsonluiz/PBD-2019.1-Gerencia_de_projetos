package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.CaracteristicaExtra;
import br.com.pbd2019_1.view.Botao;

public class TCaracteristicaExtra extends TGenerica<CaracteristicaExtra>{

	private static final long serialVersionUID = 1L;
	
	private Botao btn = new Botao("X");
	
	public TCaracteristicaExtra() {
		super(new String[]{"Nome"});
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			getList().get(rowIndex).setNome((String)value);
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
			return btn;
		}
		return null;
	}

}
