package br.com.pbd2019_1.view;

public class TelaInfoPessoa extends TelaPessoaContatoCaracteristica {

	private static final long serialVersionUID = 1L;

	public TelaInfoPessoa() {
		getTelaCadastroEdicao().desabilitar();
		getBtBotao1().setVisible(false);
		
	}

}
