package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaCadastro_Projeto extends TelaProjeto {

	private static final long serialVersionUID = 1L;

	public TelaCadastro_Projeto() {
		getTelaCadastroEdicao().abilitar();
		getTelaCadastroEdicao().esconderTglbtnBotao();
		adicionarSouth();
		setMinimumSize(new Dimension(510, 210));
		setPreferredSize(new Dimension(510, 210));
	}

}
