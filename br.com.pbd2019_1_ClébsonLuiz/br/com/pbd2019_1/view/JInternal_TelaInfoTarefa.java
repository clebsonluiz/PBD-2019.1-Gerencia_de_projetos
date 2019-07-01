package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoTarefa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInfoTarefaColaborador telaInfoTarefaColaborador;

	public JInternal_TelaInfoTarefa() {
		super("Info Tarefa");
		setMaximizable(false);
		setMinimumSize(new Dimension(315, 435));
		setPreferredSize(new Dimension(315, 435));
		setSize(getPreferredSize());
		telaInfoTarefaColaborador = new TelaInfoTarefaColaborador();
		getContentPane().add(telaInfoTarefaColaborador, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoTarefaColaborador getTelaInfoTarefa() {
		return telaInfoTarefaColaborador;
	}
}
