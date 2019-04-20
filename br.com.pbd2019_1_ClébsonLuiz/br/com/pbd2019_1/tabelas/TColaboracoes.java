package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.utils.DateUtil;

public class TColaboracoes extends TColaborador{

	private static final long serialVersionUID = 1L;

	
	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getProjeto().getNome();
		case 1:
			return DateUtil.getDateString("dd-MM-yyyy",
					getList().get(linha).getData_ingresso());
		case 2:
			return getList().get(linha).getPrivilegio();
		case 3:
			return getBtn();
		}
		return null;
	}
}
