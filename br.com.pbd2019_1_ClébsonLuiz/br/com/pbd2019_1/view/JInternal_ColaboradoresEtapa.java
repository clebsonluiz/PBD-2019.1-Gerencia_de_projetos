package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class JInternal_ColaboradoresEtapa extends JInternal_TabelaPessoasColaboradores {

	private static final long serialVersionUID = 1L;

	public JInternal_ColaboradoresEtapa() {
		setTitle("COLABORADORES DO PROJETO");
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setSize(getPreferredSize());
		getTelaPessoas().getBtAdicionarPessoa().setText("Adicionar Gerente de Etapa");
		getTelaPessoas().getBtAdicionarPessoa().setVisible(true);
	}

	
}
