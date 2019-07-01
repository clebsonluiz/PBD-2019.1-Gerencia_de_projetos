package br.com.pbd2019_1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class JInternal_TelaInfoSubTarefa extends JInternalAbstract {

	private static final long serialVersionUID = 1L;

	private TelaInfoSubTarefa telaInfoSubTarefa;

	public JInternal_TelaInfoSubTarefa() {
		super("Info Sub Tarefa");
		setMaximizable(false);
		setMinimumSize(new Dimension(315, 405));
		setPreferredSize(new Dimension(315, 405));
		setSize(getPreferredSize());
		telaInfoSubTarefa = new TelaInfoSubTarefa();
		getContentPane().add(telaInfoSubTarefa, BorderLayout.CENTER);
	}

	@Override
	protected void fechar() {}

	public TelaInfoSubTarefa getTelaInfoSubTarefa() {
		return telaInfoSubTarefa;
	}
}
