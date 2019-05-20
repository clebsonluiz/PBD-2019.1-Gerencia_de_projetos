package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.Botao;

public class TLogUpdate extends TGenerica<LogUpdate>{

	private static final long serialVersionUID = 1L;

	private Botao btn = new Botao("Ver");
	
	public TLogUpdate() {
		super(new String[] {"Cod. T >", "Tabela","Tipo","Responsavel", "Data Log", ""});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getId_tabela();
		case 1:
			return getList().get(linha).getTabela();
		case 2:
			return getList().get(linha).getTipo();
		case 3:
			return getList().get(linha).getResponsavel();
		case 4:
			return DateUtil.getDateString("dd-MM-yyyy - HH:mm:ss ", getList().get(linha).getData_log());
		case 5:
			return btn;
		}
		return null;
	}

}
