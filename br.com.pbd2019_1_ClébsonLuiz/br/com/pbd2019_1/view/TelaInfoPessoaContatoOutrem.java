package br.com.pbd2019_1.view;

public class TelaInfoPessoaContatoOutrem extends TelaPessoaContatoOutrem {

	private static final long serialVersionUID = 1L;

	public TelaInfoPessoaContatoOutrem() {
		getTelaMiniPessoa1().getTelaCadastroEdicao().desabilitar();
		getTelaContatoCaracteristica().getTelaCadastroEdicao().desabilitar();
		
	}

}
