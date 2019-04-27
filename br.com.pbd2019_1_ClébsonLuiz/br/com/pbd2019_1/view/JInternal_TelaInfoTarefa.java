package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoTarefa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInfoTarefa telaInfoTarefa;

	public JInternal_TelaInfoTarefa() {
		super("Info Tarefa");
		setMaximizable(false);
		setMinimumSize(new Dimension(315, 405));
		setPreferredSize(new Dimension(315, 405));
		setSize(getPreferredSize());
		telaInfoTarefa = new TelaInfoTarefa();
		getContentPane().add(telaInfoTarefa, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoTarefa getTelaInfoTarefa() {
		return telaInfoTarefa;
	}
}
