package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class TelaInfoPessoa extends TelaPessoaContatoCaracteristica {

	private static final long serialVersionUID = 1L;

	public TelaInfoPessoa() {
		getTelaMiniPessoa1().getTelaCadastroEdicao().desabilitar();
		getTelaMiniPessoa2().getTelaCadastroEdicao().desabilitar();
		
		getTelaMiniPessoa1().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		getTelaMiniPessoa2().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		
		getTelaContatoCaracteristica().getTelaCadastroEdicao().desabilitar();
		getTelaContatoCaracteristica().getTelaCadastroEdicao().getBtBotao1().setVisible(false);
		setMinimumSize(new Dimension(320, 420));
		setPreferredSize(new Dimension(320, 420));
		setMaximumSize(new Dimension(600, 420));
	}

}
