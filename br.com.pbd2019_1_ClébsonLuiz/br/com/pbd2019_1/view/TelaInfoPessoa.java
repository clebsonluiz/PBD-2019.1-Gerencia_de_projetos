package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaInfoPessoa extends TelaPessoaContatoCaracteristica {

	private static final long serialVersionUID = 1L;

	public TelaInfoPessoa() {
		getTelaPessoa().getTelaCadastroEdicao().desabilitar();
		getTelaContatoCaracteristica().getTelaCadastroEdicao().desabilitar();
		getTelaPessoa().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		getTelaContatoCaracteristica().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		setMinimumSize(new Dimension(300, 380));
		//setPreferredSize(new Dimension(310, 420));
		setMaximumSize(new Dimension(600, 420));
	}

}
