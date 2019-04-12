package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaInfoTarefa extends TelaTarefa{

	private static final long serialVersionUID = 1L;

	public TelaInfoTarefa() {
		setMinimumSize(new Dimension(300, 380));
		setPreferredSize(new Dimension(300, 380));
		getTelaCadastroEdicao().desabilitar();
	}

}
