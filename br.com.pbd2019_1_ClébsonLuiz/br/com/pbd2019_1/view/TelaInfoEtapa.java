package br.com.pbd2019_1.view;

public class TelaInfoEtapa extends TelaEtapa {

	private static final long serialVersionUID = 1L;

	public TelaInfoEtapa() {
		super("Etapa", "Gerente de Etapa");
		getTelaCadastroEdicao().desabilitar();
	}

}
