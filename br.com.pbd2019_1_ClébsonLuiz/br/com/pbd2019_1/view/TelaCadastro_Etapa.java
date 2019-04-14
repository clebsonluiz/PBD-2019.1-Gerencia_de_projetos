package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaCadastro_Etapa extends TelaEtapa {

	private static final long serialVersionUID = 1L;

	public TelaCadastro_Etapa() {
		getTelaCadastroEdicao().abilitar();
		getTelaCadastroEdicao().esconderTglbtnBotao();
		setMinimumSize(new Dimension(300, 250));
		setPreferredSize(new Dimension(300, 250));
	}

}
