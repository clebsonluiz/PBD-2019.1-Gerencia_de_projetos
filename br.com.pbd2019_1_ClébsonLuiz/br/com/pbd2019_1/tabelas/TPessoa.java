package br.com.pbd2019_1.tabelas;

import br.com.pbd2019_1.entidade.Pessoa;
import br.com.pbd2019_1.view.Botao;

public class TPessoa extends TGenerica<Pessoa>{
	
	private static final long serialVersionUID = 1L;

	private Botao btn = new Botao("Ver");
	
	public TPessoa() {
		super(new String[] {"Nome", "CPF", "Sexo", ""});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return getList().get(linha).getCpf();
		case 2:
			return getList().get(linha).getSexo();
		case 3:
			return btn;
		}
		return null;
	}

}
