package br.com.pbd2019_1.view;

public class TelaInfoPessoaContatoOutremSimples extends TelaPessoaContatoOutremSimples {

	private static final long serialVersionUID = 1L;

	public TelaInfoPessoaContatoOutremSimples() {
		getTelaMiniPessoa1().getTelaCadastroEdicao().desabilitar();
		getTelaContatoCaracteristica().getTelaCadastroEdicao().desabilitar();
	}

}