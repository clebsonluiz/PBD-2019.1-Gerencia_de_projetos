package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class JInternal_TabelaPessoasColaboradores extends JInternal_TabelaPessoas {

	private static final long serialVersionUID = 1L;

	public JInternal_TabelaPessoasColaboradores() {
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setSize(getPreferredSize());
		getTelaPessoas().getBtAdicionarPessoa().setText("Adicionar Colaborador");
		getTelaPessoas().getBtAdicionarPessoa().setVisible(true);
	}

}
