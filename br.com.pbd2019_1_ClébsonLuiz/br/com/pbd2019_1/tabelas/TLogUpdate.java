package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.LogUpdate;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.Botao;

public class TLogUpdate extends TGenerica<LogUpdate>{

	private static final long serialVersionUID = 1L;

	private Botao btn = new Botao("Ver");
	
	public TLogUpdate() {
		super(new String[] {"Responsavel", "Data Log", ""});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getResponsavel_alteracao();
		case 1:
			return DateUtil.getDateString("dd-MM-yyyy", getList().get(linha).getData_log());
		case 2:
			return btn;
		}
		return null;
	}

}
