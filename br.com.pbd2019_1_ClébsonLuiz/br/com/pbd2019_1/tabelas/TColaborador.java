package br.com.pbd2019_1.tabelas;

import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import br.com.pbd2019_1.entidade.Colaborador;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.Botao;

public class TColaborador extends TGenerica<Colaborador>{

	private static final long serialVersionUID = 1L;
	
	private Botao btn = new Botao("Ver", Color.green.brighter().brighter());
	
	private JComboBox<String> combo = new JComboBox<>(new String[] {
			"Visitante", "Participante", "Administrador"
	});
	
	public TColaborador() {
		super(new String[]{"Nome","Data ingressão","Privilegio", ""});
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
		case 3:
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

	public Botao getBtn() {
		return btn;
	}

}
