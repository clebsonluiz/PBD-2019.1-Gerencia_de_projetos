package br.com.pbd2019_1.tabelas;

public class TColaboracoes extends TColaborador{

	private static final long serialVersionUID = 1L;

	
	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getProjeto().getNome();
		case 1:
			return getList().get(linha).getData_ingresso();
		case 2:
			return getBtn();
		}
		return null;
	}
}
