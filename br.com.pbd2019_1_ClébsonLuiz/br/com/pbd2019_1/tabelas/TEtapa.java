package br.com.pbd2019_1.tabelas;

import java.awt.Color;

import br.com.pbd2019_1.entidade.Etapa;
import br.com.pbd2019_1.view.Botao;

public class TEtapa extends TGenerica<Etapa>{

	private static final long serialVersionUID = 1L;

	private Botao btn = new Botao("Ver", Color.green.brighter().brighter());
	
	public TEtapa() {
		super(new String[] {"Nome", "Porcentagem", ""});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getList().get(linha).getNome();
		case 1:
			return getList().get(linha).getPorcentagem_andamento();
		case 2:
			return btn;
		}
		return null;
	}
	
}
