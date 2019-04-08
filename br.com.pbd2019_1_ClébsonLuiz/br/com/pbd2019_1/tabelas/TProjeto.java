package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.Projeto;
import br.com.pbd2019_1.utils.DateUtil;
import br.com.pbd2019_1.view.Botao;

public class TProjeto extends TGenerica<Projeto> {

	private static final long serialVersionUID = 1L;

	private Botao btn = new Botao("Ver");
	
	public TProjeto() {
		super(new String[] {"Nome", "Data inicio", "Data fim", ""});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return DateUtil.getDateString("dd-MM-yyyy", getList().get(linha).getData_inicio());
		case 2:
			return DateUtil.getDateString("dd-MM-yyyy", getList().get(linha).getData_fim());
		case 3:
			return btn;
		}
		return null;
	}
	
}
