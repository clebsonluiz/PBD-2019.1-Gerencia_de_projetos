package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaCadastro_Pessoa extends TelaPessoa{

	private static final long serialVersionUID = 1L;

	public TelaCadastro_Pessoa() {
		
		getTelaCadastroEdicao().abilitar();
		getTelaCadastroEdicao().esconderTglbtnBotao();
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(310, 420));
	}

}
