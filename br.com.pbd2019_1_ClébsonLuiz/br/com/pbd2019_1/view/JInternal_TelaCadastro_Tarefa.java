package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaCadastro_Tarefa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaCadastro_Tarefa telaCadastro_Tarefa;
	
	public JInternal_TelaCadastro_Tarefa() {
		super("Cadastro Tarefa");
		setMaximizable(false);
		telaCadastro_Tarefa = new TelaCadastro_Tarefa();
		add(telaCadastro_Tarefa, BorderLayout.CENTER);

		setMinimumSize(new Dimension(315, 405));
		setPreferredSize(new Dimension(315, 405));
		setSize(getPreferredSize());
	}

	@Override
	protected void fechar() {
		telaCadastro_Tarefa.limparCampos();
	}

	public TelaCadastro_Tarefa getTelaCadastro_Tarefa() {
		return telaCadastro_Tarefa;
	}

}
