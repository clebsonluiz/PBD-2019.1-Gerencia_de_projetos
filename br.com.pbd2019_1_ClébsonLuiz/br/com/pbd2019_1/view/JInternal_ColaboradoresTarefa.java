package br.com.pbd2019_1.view;

import java.awt.Dimension;

public class JInternal_ColaboradoresTarefa extends JInternal_TabelaPessoasColaboradores {

	private static final long serialVersionUID = 1L;

	public JInternal_ColaboradoresTarefa() {
		setTitle("COLABORADORES DO PROJETO");
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setSize(getPreferredSize());
		getTelaPessoas().getBtAdicionarPessoa().setText("Adicionar Colaborador de Tarefa");
		getTelaPessoas().getBtAdicionarPessoa().setVisible(true);
	}

	
}
